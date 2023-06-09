package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void add(GameState state, String saveName) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO game_state (save_name, current_map, saved_at, player_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, saveName);
            statement.setString(2, state.getCurrentMap());
            statement.setTimestamp(3, state.getSavedAt());
            statement.setInt(4, state.getPlayerId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    @Override
    public void update(GameState state) {

    }

    @Override
    public GameState get(int id) {
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
