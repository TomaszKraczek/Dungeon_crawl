package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    @Test
    void constructorTest(){
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Cell cell = gameMap.getCell(1,1);
        Skeleton monster = new Skeleton(cell, 10,MonstersStats.SKELETON.getAttackStrength());
    }
    @Test
    void getAttackStrength_ShouldEquals0(){
        //given
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Cell cell = gameMap.getCell(1,1);
        Skeleton monster = new Skeleton(cell, 10,MonstersStats.SKELETON.getAttackStrength());
        //when
        int strength = monster.getAttackStrength();
        //then
        assertEquals(1,strength);
    }

    @Test
    void getTileName_ShouldReturnSkeletonString() {
        //given
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Cell cell = gameMap.getCell(1,1);
        Skeleton monster = new Skeleton(cell, 10,MonstersStats.SKELETON.getAttackStrength());
        //when
        String name = monster.getTileName();
        //then
        assertEquals("skeleton", name);
    }
    @Test
    void move_SkeletonCellDoesntChange() {
        //given
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Cell cell = gameMap.getCell(1,1);
        Skeleton monster = new Skeleton(cell, 10, MonstersStats.SKELETON.getAttackStrength());
        //when
        monster.move();
        //then
        assertEquals(1, monster.getCell().getX());
        assertEquals(1, monster.getCell().getY());
    }
}