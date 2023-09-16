package me.app.domain;

import me.app.data.PlayerRepository;
import me.app.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAll(){
        return repository.findAll();
    }

    public Player findById(int id){
        return repository.findById(id);
    }

    public Result<Player> add(Player player){
        Result<Player> result = validate(player);
        if (!result.isSuccess()){
            return result;
        }

        player = repository.add(player);
        result.setPayload(player);
        return result;
    }

    public Result<Player> update(Player player){
        Result<Player> result = validate(player);
        if (!result.isSuccess()){
            return result;
        }

        if (!repository.update(player)){
            String msg = String.format("player_id: %s was not found", player.getPlayerId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Player> validate(Player player){
        Result<Player> result = new Result<Player>();

        if (player == null){
            result.addMessage("Player cannot be null.", ResultType.INVALID);
            return result;
        }
        if (player.getGamerTag() == null){
            result.addMessage("Player gamer tage must not be null.", ResultType.INVALID);
            return result;
        }
        if (player.getGamerTag().length() > 50){
            result.addMessage("Player gamer tag must not be longer than 50 characters.", ResultType.INVALID);
        }
        if (player.getTagLine() != null && player.getTagLine().length() > 50){
            result.addMessage("Player tag line must not be longer than 50 characters.", ResultType.INVALID);
        }

        return result;
    }

}
