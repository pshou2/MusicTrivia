package me.app.data.mappers;

import me.app.models.Player;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setGamerTag(rs.getString("gamer_tag"));
        player.setTagLine(rs.getString("tagline"));
        return player;
    }
}
