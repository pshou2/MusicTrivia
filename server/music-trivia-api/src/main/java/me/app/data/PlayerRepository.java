package me.app.data;

import me.app.models.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> findAll();

    Player findById(int id);

    Player add(Player player);

    boolean update(Player player);

    boolean deleteById(int id);
}
