package me.app.controller;

import me.app.domain.PlayerService;
import me.app.models.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable int id){
        Player player = service.findById(id);
        if (player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
