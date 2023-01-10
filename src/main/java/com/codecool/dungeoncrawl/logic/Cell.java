package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;
    private Item item;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

//    public void setTilename(Tiles tile){
//        this.tile = tile;
//    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }
    public Item getItem(){ return item; }

    public Cell getNeighbor(int dx, int dy) {
        if (y + dy < 0 || x + dx < 0 ||
                y + dy >= gameMap.getHeight() || x + dx >= gameMap.getWidth()) {
            return null;
        }
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
            if (actor != null) {
                return actor.getTileName();
            } else if (item != null) {
                return item.getTileName();
            } else {
                return type.getTileName();
            }
        }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
