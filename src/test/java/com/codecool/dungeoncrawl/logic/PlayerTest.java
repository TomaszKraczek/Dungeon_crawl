package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.key.Key;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    GameMap map = new GameMap(4, 4, CellType.FLOOR);
    Cell cell = map.getCell(3,2);
    Player player = new Player(1, cell, 5, "Mariusz");


    @Test
    public void movesRightWay() {
        map.setPlayer(player);
        player.move(0,1);
        assertEquals(3, map.getPlayer().getX());
        assertEquals(3, map.getPlayer().getY());
    }

    @Test
    public void staysIfNeighbourCellIsNull() {
        map.setPlayer(player);
        map.getPlayer().move(1,0);
        assertEquals(3, map.getPlayer().getX());
        assertEquals(2, map.getPlayer().getY());
    }

    @Test
    public void staysIfNeighbourCellIsWall() {
        map.setPlayer(player);
        Cell cell1 = map.getCell(2, 2);
        cell1.setType(CellType.WALL);
        map.getPlayer().move(-1,0);
        assertEquals(3, map.getPlayer().getX());
        assertEquals(2, map.getPlayer().getY());
    }

    @Test
    public void staysIfNeighbourCellIsClosedDoor() {
        map.setPlayer(player);
        Cell cell1 = map.getCell(2, 2);
        cell1.setType(CellType.CLOSED_DOOR);
        map.getPlayer().move(-1,0);
        assertEquals(3, map.getPlayer().getX());
        assertEquals(2, map.getPlayer().getY());
    }

    @Test
    public void movesIfNeighbourCellIsClosedDoorWithKey() {
        map.setPlayer(player);
        Cell cell1 = map.getCell(2, 2);
        cell1.setType(CellType.CLOSED_DOOR);
        player.addItemToEq(new Key(cell, "key"));
        map.getPlayer().move(-1,0);
        assertEquals(2, map.getPlayer().getX());
        assertEquals(2, map.getPlayer().getY());
    }

    @Test
    public void fightsIfNeighbourCellIsAnotherActor() {
        map.setPlayer(player);
        Cell cell1 = map.getCell(2, 2);
        cell1.setType(CellType.FLOOR);
        /* attack strength from constructor is ignored */
        Monster skeleton = new Skeleton(cell1, 3, 2);
        map.addMonster(skeleton);
        player.updatePlayerStats();
        map.getPlayer().move(-1,0);
        assertEquals(4, player.getHealth());
        assertEquals(2, map.getPlayer().getX());
        assertEquals(2, map.getPlayer().getY());
    }



}

