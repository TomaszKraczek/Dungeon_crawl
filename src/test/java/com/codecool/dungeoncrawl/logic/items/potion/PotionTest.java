package com.codecool.dungeoncrawl.logic.items.potion;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.weapon.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {
    GameMap testGameMap = new GameMap(5,5, CellType.EMPTY);
    Cell testCell = testGameMap.getCell(0,0);
    Potion testPotion = new Potion(testCell, "potion", 1);
    @Test
    public void getTileName_ifReturnedNameIsCorrect(){
        assertEquals("potion", testPotion.getTileName());
    }
    @Test
    public void getImproveHealth_ifReturnedValueIsCorrect(){
        assertEquals(1, testPotion.getImproveHealth());
    }

}