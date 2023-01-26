package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.logic.actors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class GameMapTest {

    GameMap gameMap;
    Cell cell;
    Spider spider;
    List<Monster> monsterList;

    @BeforeEach
    public void initialization() {
        gameMap = new GameMap(10, 10, CellType.FLOOR);
        cell = gameMap.getCell(2, 2);
        spider = new Spider(cell, MonstersStats.SPIDER.getHealthPoints(), MonstersStats.SPIDER.getAttackStrength());
        monsterList= new ArrayList<>();
    }

    @Test
    public void addMonsters_addsMonsterToTheList(){
        monsterList.add(spider);
        gameMap.addMonster(spider);
        assertEquals(monsterList, gameMap.getMonsters());
    }

    @Test
    public void removeMonster_removesMonsterFromTheList(){
        monsterList.add(spider);
        gameMap.addMonster(spider);

        monsterList.remove(spider);
        gameMap.removeMonster(spider);

        assertEquals(monsterList, gameMap.getMonsters());
    }


    @Test
    public void getMonsters_shouldReturnListOfAllMonsters(){
        assertEquals(monsterList, gameMap.getMonsters());
    }

    @Test
    public void getCell_shouldReturnCorrectCell(){
        assertEquals(cell, gameMap.getCell(2,2));
    }

    @Test
    public void getPlayer_returnsCreatedPlayer(){
        Player player = new Player(1, cell, PlayerDefaultStats.HEALTH.getDefaultValue(), "Player");
        gameMap.setPlayer(player);
        assertEquals(player, gameMap.getPlayer());
    }

    @Test
    public void getHeight_shouldReturn10(){
        int expected = 10;
        int actual = gameMap.getHeight();
        assertEquals(expected, actual);
    }

    @Test
    public void getWidth_shouldReturn10(){
        int expected = 10;
        int actual = gameMap.getWidth();
        assertEquals(expected, actual);
    }
}
