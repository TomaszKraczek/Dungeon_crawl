package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    int add(PlayerModel player);
    void update(PlayerModel player);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
