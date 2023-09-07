package me.app.data;

import me.app.models.HighScore;
import me.app.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HighScoreJdbcTemplateRepositoryTest {
    @Autowired
    HighScoreJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setUp(){
        knownGoodState.set();
    }

    @Test
    void shouldFindAll(){
        List<HighScore> all = repository.findAll();
        for (HighScore h: all){
            System.out.println(h.getDate());
        }
    }
    @Test
    void shouldFindById(){
        HighScore highScore = repository.findById(1);
        System.out.println(highScore.getScore());
        assertNotNull(repository.findById(1));
    }

    @Test
    void shouldAdd(){
        HighScore highScore = new HighScore();
        highScore.setScore(6);
        LocalDate date = LocalDate.of(2023, 9, 7);
        highScore.setDate(date);
        LocalTime time = LocalTime.of(2, 15, 32);
        highScore.setTime(time);
        highScore.setPlayerId(1);
        HighScore actual = repository.add((highScore));
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate(){
        //test is failing when all tests run because delete test runs faster than update
        HighScore highScoreToUpdate = repository.findById(1);
        highScoreToUpdate.setScore(25);
        assertTrue(repository.update(highScoreToUpdate));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(1));
    }

    @Test
    void shouldNotDeleteNonExistingId(){
        assertFalse(repository.deleteById(999999999));
    }
}