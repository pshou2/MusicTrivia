package me.app.data;

import me.app.models.HighScore;

import java.util.List;

public interface HighScoreRepository {
    List<HighScore> findAll();

    HighScore findById(int id);

    HighScore add(HighScore highScore);

    boolean update(HighScore highScore);

    boolean deleteById(int id);
}
