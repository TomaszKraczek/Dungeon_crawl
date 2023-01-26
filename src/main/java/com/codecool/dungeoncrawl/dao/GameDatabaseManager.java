package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public int savePlayerAndReturnHisId(Player player) {
        PlayerModel model = new PlayerModel(player);
        return playerDao.add(model);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeon_crawl";
        String user = System.getenv("USER_NAME");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");
        return dataSource;
    }

    public void saveGame(String saveName, GameMap map, String filename) {
         PlayerModel model = new PlayerModel(map.getPlayer());
         int playerId = playerDao.add(model);

         //TODO jak wybierać planszę
         GameState gameState = new GameState(filename, model, playerId);
         gameStateDao.add(gameState, saveName);
         System.out.println(gameState.toString());

    }
}
