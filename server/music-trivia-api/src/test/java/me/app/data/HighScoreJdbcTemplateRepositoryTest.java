package me.app.data;

import me.app.models.HighScore;
import me.app.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    
}