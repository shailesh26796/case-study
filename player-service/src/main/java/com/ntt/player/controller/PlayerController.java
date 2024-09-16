package com.ntt.player.controller;

import com.ntt.player.entity.Player;
import com.ntt.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Player>> getPlayerByName(@PathVariable String name) {
        return ResponseEntity.ok(playerService.getPlayersByName(name));
    }

    @PostMapping
    public ResponseEntity<Player> createRecord(@RequestBody Player player) {
        return playerService.createRecord(player);
    }

    @DeleteMapping("/player/{id}")
    public void deleteRecord(@PathVariable Long id) {
        playerService.deleteRecord(id);
    }
}

