package me.app.controller;

import me.app.domain.HighScoreService;
import me.app.domain.Result;
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
    public ResponseEntity<Object> add(@RequestBody HighScore highScore){
        Result<HighScore> result = service.add(highScore);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody HighScore highScore){
        if (id != highScore.getHighScoresId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<HighScore> result = service.update(highScore);
        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteById(@PathVariable int id){
        Result<HighScore> result = service.deleteById(id);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
}
