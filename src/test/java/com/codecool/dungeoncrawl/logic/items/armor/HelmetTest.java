package com.codecool.dungeoncrawl.logic.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelmetTest {
    GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
    Cell testCell = testGameMap.getCell(0,0);
    Helmet testHelmet = new Helmet(testCell, "test helmet", 1);
    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        assertEquals("helmet", testHelmet.getTileName());
    }
    @Test
    public void getAttackPower_ifReturnedValueIsCorrect(){
        assertEquals(1, testHelmet.getArmorPoint());
    }
    @Test
    public void getName_ifReturnedNameIsCorrect(){
        assertEquals("test helmet", testHelmet.getName());
    }
}