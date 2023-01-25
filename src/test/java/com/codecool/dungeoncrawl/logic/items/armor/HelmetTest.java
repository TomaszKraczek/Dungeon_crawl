package com.codecool.dungeoncrawl.logic.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelmetTest {
    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
        Cell testCell = testGameMap.getCell(0,0);
        Helmet testHelmet = new Helmet(testCell, "helmet", 1);
        assertEquals("helmet", testHelmet.getTileName());
    }
}