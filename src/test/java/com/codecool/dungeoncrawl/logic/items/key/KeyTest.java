package com.codecool.dungeoncrawl.logic.items.key;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
        Cell testCell = testGameMap.getCell(0,0);
        Key testKey = new Key(testCell, "key");
        assertEquals("key", testKey.getTileName());
    }

}