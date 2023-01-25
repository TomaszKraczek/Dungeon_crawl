package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.key.Key;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarriorTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);

    Cell cell = gameMap.getCell(2,2);

    Warrior warrior=new Warrior(cell, MonstersStats.WARRIOR.getHealthPoints(), MonstersStats.WARRIOR.getAttackStrength());

    @Test
    public void getTitle_shouldReturnWarrior(){
        String expected = "warrior";
        String actual = warrior.getTileName();
        assertEquals(expected, actual);
    }

    @Test
    public void getAttackStrength_shouldReturn3(){
        int expected = 3;
        int actual = warrior.getAttackStrength();
        assertEquals(expected, actual);
    }

    @Test
    public void move_shouldPassIfMovesAroundItem(){
        cell.setItem(new Key(gameMap.getCell(3, 2), "key"));

        warrior.move();

        assertEquals(3, warrior.getX());
        assertEquals(1, warrior.getY());

        warrior.move();

        assertEquals(4, warrior.getX());
        assertEquals(2, warrior.getY());

        warrior.move();

        assertEquals(3, warrior.getX());
        assertEquals(3, warrior.getY());

        warrior.move();

        assertEquals(2, warrior.getX());
        assertEquals(2, warrior.getY());
    }

    @Test
    public void move_warriorPositionDoesNotChangeIfNoItemAround(){
        warrior.move();
        assertEquals(2, warrior.getX());
        assertEquals(2, warrior.getY());
    }
}
