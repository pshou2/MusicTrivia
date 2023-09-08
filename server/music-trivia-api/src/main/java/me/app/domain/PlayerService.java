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

    
}
