package me.app.data;

import me.app.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerJdbcTemplateRepositoryTest {
    @Autowired
    PlayerJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setUp(){
        knownGoodState.set();
    }

    @Test
    void shouldFindAll(){
        List<Player> all = repository.findAll();
        for (Player p : all){
            System.out.println(p.getPlayerId());
        }
    }

    @Test
    void shouldFindById(){
        Player player = repository.findById(1);
        assertNotNull(player);
    }

    @Test
    void shouldAddPlayer(){
        Player player = new Player();
        player.setGamerTag("whooda");
        player.setTagLine("the best never rest");
        Player actual = repository.add(player);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdatePlayer(){
        Player playerToUpdate = new Player();
        playerToUpdate.setGamerTag("updated");
        playerToUpdate.setTagLine("updated tagline");
        playerToUpdate.setPlayerId(1);
        assertTrue(repository.update(playerToUpdate));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(1));
    }
}