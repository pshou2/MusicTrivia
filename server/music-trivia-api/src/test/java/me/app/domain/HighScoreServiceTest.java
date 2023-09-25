package me.app.domain;

import me.app.data.HighScoreRepository;
import me.app.models.HighScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HighScoreServiceTest {
    @Autowired
    HighScoreService service;

    @MockBean
    HighScoreRepository repository;

    @Test
    void shouldAdd(){
        HighScore highScore = new HighScore();
        highScore.setScore(10);
        highScore.setDate(LocalDate.of(2023,9,1));
        highScore.setTime(LocalTime.of(13,15,23));
        highScore.setPlayerId(1);
        Result<HighScore> result = service.add(highScore);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotAddNull(){
        Result<HighScore> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddNegativeScore(){
        HighScore highScore = new HighScore();
        highScore.setScore(-10);
        highScore.setDate(LocalDate.of(2023,9,1));
        highScore.setTime(LocalTime.of(13,15,23));
        highScore.setPlayerId(1);
        Result<HighScore> result = service.add(highScore);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddFutureDate(){
        HighScore highScore = new HighScore();
        highScore.setScore(10);
        highScore.setDate(LocalDate.of(2024,9,1));
        highScore.setTime(LocalTime.of(13,15,23));
        highScore.setPlayerId(1);
        Result<HighScore> result = service.add(highScore);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddFutureTime(){
        HighScore highScore = new HighScore();
        highScore.setScore(-10);
        highScore.setDate(LocalDate.now());
        highScore.setTime(LocalTime.of(23,59,59));
        highScore.setPlayerId(1);
        Result<HighScore> result = service.add(highScore);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithoutPlayerId(){
        HighScore highScore = new HighScore();
        highScore.setScore(10);
        highScore.setDate(LocalDate.of(2023,9,1));
        highScore.setTime(LocalTime.of(13,15,23));
        Result<HighScore> result = service.add(highScore);
        assertEquals(ResultType.INVALID, result.getType());
    }
}