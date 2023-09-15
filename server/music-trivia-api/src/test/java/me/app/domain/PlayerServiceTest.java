package me.app.domain;

import me.app.data.PlayerRepository;
import me.app.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerServiceTest {
    @Autowired
    PlayerService service;

    @MockBean
    PlayerRepository repository;

    //Happy Add
    @Test
    void shouldAdd(){
        Player player = new Player();
        player.setGamerTag("Montaigneer");
        player.setTagLine("King of the SHOUs");
        Result<Player> result = service.add(player);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    //Unhappy Add
    @Test
    void shouldNotAddNull(){
        Result<Player> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
    }
}