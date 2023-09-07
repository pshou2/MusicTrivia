package me.app.data;

import me.app.data.mappers.HighScoreMapper;
import me.app.models.HighScore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

@Repository
public class HighScoreJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public HighScoreJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HighScore> findAll(){
        final String sql = "select high_scores_id, score, date, time, player_id from high_scores;";
        return jdbcTemplate.query(sql, new HighScoreMapper());
    }

    public HighScore findById(int id){
        final String sql = "select high_scores_id, score, date, time, player_id from high_scores"
        + " where high_scores_id = ?;";

        return jdbcTemplate.queryForObject(sql, new HighScoreMapper(), id);
    }

    public HighScore add(HighScore highScore){
        final String sql = "insert into high_scores (score, date, time, player_id) "
                + "values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,highScore.getScore());
            ps.setDate(2, Date.valueOf(highScore.getDate()));
            ps.setTime(3, Time.valueOf(highScore.getTime()));
            ps.setInt(4, highScore.getPlayerId());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        highScore.setHighScoresId(keyHolder.getKey().intValue());
        return highScore;
    }

    public boolean update(HighScore highScore){
        final String sql = "update high_scores set "
                + "score = ?, "
                + "date = ?, "
                + "time = ?, "
                + "player_id = ? "
                + "where high_scores_id = ?;";

        return jdbcTemplate.update(sql,
                highScore.getScore(),
                highScore.getDate(),
                highScore.getTime(),
                highScore.getPlayerId(),
                highScore.getHighScoresId()) > 0;
    }

    public boolean deleteById(int id){
        return jdbcTemplate.update("delete from high_scores where high_scores_id = ?;", id) > 0;
    }
}
