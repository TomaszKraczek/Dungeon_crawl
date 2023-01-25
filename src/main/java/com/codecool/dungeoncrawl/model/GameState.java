package com.codecool.dungeoncrawl.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameState extends BaseModel {
    private LocalDateTime savedAt;
    private String currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel player;
    private int playerId;

    public GameState(String currentMap, PlayerModel player, int playerId) {
        this.currentMap = currentMap;
        this.savedAt = LocalDateTime.now();
        this.player = player;
        this.playerId = playerId;
    }

    public Timestamp getSavedAt() {
        System.out.println(savedAt.toLocalDate());
        return Timestamp.valueOf(savedAt);
    }
    public int getPlayerId(){
        return playerId;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
    @Override
    public String toString(){
        StringBuilder build = new StringBuilder()
                .append("Player Id: " + playerId)
                .append(" Date of save: " + savedAt)
                .append(" Player name: " + player.getPlayerName());
        return build.toString();
    }
}
