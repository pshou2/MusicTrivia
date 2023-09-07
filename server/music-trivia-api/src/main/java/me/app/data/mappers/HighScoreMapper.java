package me.app.data.mappers;

import me.app.models.HighScore;
import me.app.models.Player;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScoreMapper implements RowMapper<HighScore> {
    @Override
    public HighScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        HighScore highScore = new HighScore();
        highScore.setHighScoresId(rs.getInt("high_scores_id"));
        highScore.setScore(rs.getInt("score"));
        highScore.setDate(rs.getDate("date").toLocalDate());
        highScore.setTime(rs.getTime("time").toLocalTime());
        highScore.setPlayerId(rs.getInt("player_id"));
        return highScore;
    }
}
