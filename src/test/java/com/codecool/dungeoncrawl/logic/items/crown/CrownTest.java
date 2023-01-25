package com.codecool.dungeoncrawl.logic.items.crown;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.armor.Helmet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrownTest {

    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
        Cell testCell = testGameMap.getCell(0,0);
        Crown testCrown = new Crown(testCell, "crown");
        assertEquals("crown", testCrown.getTileName());
    }

}