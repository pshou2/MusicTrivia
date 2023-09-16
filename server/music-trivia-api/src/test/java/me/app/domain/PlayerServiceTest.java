package me.app.domain;

import me.app.data.PlayerRepository;
import me.app.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

    @Test
    void shouldNotAddNullGamerTag(){
        Player player = new Player();
        player.setTagLine("blah");
        Result<Player> result = service.add(player);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddGamerTagLengthGreaterThan50(){
        Player player = new Player();
        player.setGamerTag("*".repeat(100));
        player.setTagLine("hello");
        Result<Player> result = service.add(player);
        assertEquals(ResultType.INVALID, result.getType());
    }

    //Happy Update
    @Test
    void shouldUpdate(){
        Player player = new Player();
        player.setPlayerId(1);
        player.setGamerTag("Whooda updated");
        player.setTagLine("Wooooooooo");

        when(repository.update(player)).thenReturn(true);
        Result<Player> result = service.update(player);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    //Unhappy Update
    @Test
    void shouldNotUpdateZeroId(){
        Player player = new Player();
        player.setGamerTag("Whooda updated");
        player.setTagLine("Wooooooooo");

        when(repository.update(player)).thenReturn(false);
        Result<Player> result = service.update(player);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateNonExistingId(){
        Player player = new Player();
        player.setPlayerId(9999);
        player.setGamerTag("Hello");

        when(repository.update(player)).thenReturn(false);
        Result<Player> result = service.update(player);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }

    @Test
    void shouldDelete(){
        when(repository.deleteById(1)).thenReturn(true);
        Result<Player> result = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteNonExistingId(){
        when(repository.deleteById(9999)).thenReturn(false);
        Result<Player> result = service.deleteById(9999);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }
}