package me.app.data;

import me.app.data.mappers.PlayerMapper;
import me.app.models.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlayerJdbcTemplateRepository implements PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Player> findAll(){
        final String sql = "select player_id, gamer_tag, tagline from player;";
        return jdbcTemplate.query(sql, new PlayerMapper());
    }

    @Override
    public Player findById(int id){
        final String sql = "select player_id, gamer_tag, tagline from player "
                + "where player_id = ?;";

        return jdbcTemplate.queryForObject(sql, new PlayerMapper(), id);
    }

    @Override
    public Player add(Player player){
        final String sql = "insert into player (gamer_tag, tagline) "
                + "values (?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,player.getGamerTag());
            ps.setString(2,player.getTagLine());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        player.setPlayerId(keyHolder.getKey().intValue());
        return player;

    }

    @Override
    public boolean update(Player player){
        final String sql = "update player set "
                + "gamer_tag = ?, "
                + "tagline = ? "
                + "where player_id = ?;";

        return jdbcTemplate.update(sql,
                player.getGamerTag(),
                player.getTagLine()) > 0;
    }

    @Override
    public boolean deleteById(int id){
        return false;
    }
}
