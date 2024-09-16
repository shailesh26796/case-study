package com.ntt.player.service;

import com.ntt.player.entity.Player;
import com.ntt.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getPlayersByName(String name) {
        return playerRepository.findByFirstName(name);
    }

    public ResponseEntity<Player> createRecord(Player player) {
        return ResponseEntity.of(Optional.of(playerRepository.save(player)));
    }

    public void deleteRecord(Long id) {
        playerRepository.deleteById(id);
    }
}
