package com.codecool.dungeoncrawl.logic.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.key.Key;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
        Cell testCell = testGameMap.getCell(0,0);
        Sword testSword = new Sword(testCell, "sword", 1);
        assertEquals("sword", testSword.getTileName());
    }
}