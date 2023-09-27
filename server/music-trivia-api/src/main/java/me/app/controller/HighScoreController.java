package me.app.controller;

import me.app.domain.HighScoreService;
import me.app.models.HighScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/high_scores")
public class HighScoreController {
    private final HighScoreService service;

    public HighScoreController(HighScoreService service) {
        this.service = service;
    }

    @GetMapping
    public List<HighScore> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HighScore> findById(@PathVariable int id) {
        HighScore highScore = service.findById(id);
        if(highScore == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(highScore, HttpStatus.OK);
    }

    @PostMapping
    
}
