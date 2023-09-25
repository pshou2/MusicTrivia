package me.app.domain;

import me.app.data.HighScoreRepository;
import me.app.models.HighScore;
import me.app.models.Player;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class HighScoreService {
    private final HighScoreRepository repository;

    public HighScoreService(HighScoreRepository repository) {
        this.repository = repository;
    }

    public List<HighScore> findAll(){
        return repository.findAll();
    }

    public HighScore findById(int id){
        return repository.findById(id);
    }

    public Result<HighScore> add(HighScore highScore){
        Result<HighScore> result = validate(highScore);

        if (!result.isSuccess()){
            return result;
        }

        if (highScore.getHighScoresId() != 0){
            result.addMessage("high_scores_id must not be set for 'add' operation.", ResultType.INVALID);
            return result;
        }

        highScore = repository.add(highScore);
        result.setPayload(highScore);
        return result;
    }

    public Result<HighScore> update(HighScore highScore){
        Result<HighScore> result = validate(highScore);
        if (!result.isSuccess()){
            return result;
        }

        if (highScore.getHighScoresId() <= 0){
            result.addMessage("high_scores_id must be set for 'update' operation.", ResultType.INVALID);
            return result;
        }

        if (!repository.update(highScore)){
            String msg = String.format("high_scores_id: %s was not found", highScore.getHighScoresId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<HighScore> deleteById(int id){
        Result<HighScore> result = new Result<HighScore>();
        if(!repository.deleteById(id)){
            String msg = String.format("high_scores_id: %s was not found", id);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<HighScore> validate(HighScore highScore){
        Result<HighScore> result = new Result<HighScore>();

        if (highScore == null){
            result.addMessage("High Score cannot be null.", ResultType.INVALID);
            return result;
        }

        //validate it has a score, date, time, player_id
        /*
        * Score is valid, its number
        * date is in the past
        * player_id isn't 0 or null
        * */

        if (highScore.getScore() < 0){
            result.addMessage("Score must be above 0.", ResultType.INVALID);
        }
        if (highScore.getDate().isAfter(LocalDate.now())) {
            result.addMessage("Date must be in the past", ResultType.INVALID);
        }
        if (highScore.getTime().isAfter(LocalTime.now())){
            result.addMessage("Time must be in the past", ResultType.INVALID);
        }
        if (highScore.getPlayerId() <= 0){
            result.addMessage("Player id must be set", ResultType.INVALID);
        }
        return result;
    }


}
