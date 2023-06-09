package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.MonstersStats;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.PlayerDefaultStats;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(0 ,gameMap.getCell(1, 1), PlayerDefaultStats.HEALTH.getDefaultValue(), "Mariusz");
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(0, gameMap.getCell(1, 1), PlayerDefaultStats.HEALTH.getDefaultValue(), "Mariusz");
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(0, gameMap.getCell(2, 1), PlayerDefaultStats.HEALTH.getDefaultValue(), "Mariusz");
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void canMoveIntoAnotherActor() {
        Player player = new Player(0, gameMap.getCell(1, 1), PlayerDefaultStats.HEALTH.getDefaultValue(), "Mariusz");
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1), MonstersStats.SKELETON.getHealthPoints(), MonstersStats.SKELETON.getAttackStrength());
        player.updatePlayerStats();
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }
}