package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpiderTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);

    Cell cell = gameMap.getCell(2,2);

    Spider spider=new Spider(cell, MonstersStats.SPIDER.getHealthPoints(), MonstersStats.SPIDER.getAttackStrength());

    @Test
    public void getTitle_shouldReturnSpider(){
        String expected = "spider";
        String actual = spider.getTileName();
        assertEquals(expected, actual);
    }

    @Test
    public void getAttackStrength_shouldReturn3(){
        int expected = 2;
        int actual = spider.getAttackStrength();
        assertEquals(expected, actual);
    }

    @Test
    public void getRandomDirection_shouldReturnValidDirection(){
        String direction = spider.getRandomDirection();
        List<String> expectedDirectionList = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
        assertTrue(expectedDirectionList.contains(direction));
    }

    @Test
    public void move_spiderMovesInRandomDirection(){
        ArrayList<ArrayList<Integer>> possiblePositionList= new ArrayList<>();
        ArrayList<Integer> movedDownPosition = new ArrayList<>(Arrays.asList(2,3));
        ArrayList<Integer> movedRightPosition = new ArrayList<>(Arrays.asList(3,2));
        ArrayList<Integer> movedLeftPosition = new ArrayList<>(Arrays.asList(1,2));
        ArrayList<Integer> movedUpPosition = new ArrayList<>(Arrays.asList(2, 1));

        possiblePositionList.add(movedUpPosition);
        possiblePositionList.add(movedDownPosition);
        possiblePositionList.add(movedLeftPosition);
        possiblePositionList.add(movedRightPosition);

        spider.move();

        ArrayList<Integer> spiderPosition = new ArrayList<>(Arrays.asList(spider.getX(), spider.getY()));

        assertTrue(possiblePositionList.contains(spiderPosition));
    }

}
