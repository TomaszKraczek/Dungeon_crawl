package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Spider;
import com.codecool.dungeoncrawl.logic.actors.Warrior;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.armor.Helmet;
import com.codecool.dungeoncrawl.logic.items.weapon.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap map = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void getNeighbor() {
        Cell cell = map.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell = map.getCell(1, 0);
        assertEquals(null, cell.getNeighbor(0, -1));

        cell = map.getCell(1, 2);
        assertEquals(null, cell.getNeighbor(0, 1));
    }

    @Test
    void getActorNameFromCell() {
        Cell cell = map.getCell(2,1);
        cell.setActor(new Spider(cell, 5, 5));
        assertEquals("spider", cell.getTileName());
    }

    @Test
    void getItemNameFromCell() {
        Cell cell = map.getCell(1,2);
        cell.setItem(new Sword(cell, "sword", 5));
        assertEquals("sword", cell.getTileName());
    }

    @Test
    void getEmptyCellNameFromCell() {
        Cell cell = map.getCell(2,1);
        cell.setType(CellType.FLOOR);
        assertEquals("floor", cell.getTileName());
    }

    @Test
    void checkIfSetActorProperly() {
        Cell cell = map.getCell(2,1);
        Actor actor = new Warrior(cell, 5, 5);
        cell.setActor(actor);
        assertEquals(actor, cell.getActor());
    }

    @Test
    void checkIfSetItemProperly() {
        Cell cell = map.getCell(2,2);
        Item item = new Helmet(cell, "helmet", 5);
        cell.setItem(item);
        assertEquals(item, cell.getItem());
    }

    @Test
    void checkIfSetCellTypeProperly() {
        Cell cell = map.getCell(1,1);
        cell.setType(CellType.OPENED_DOOR);
        assertEquals(CellType.OPENED_DOOR, cell.getType());
    }

}