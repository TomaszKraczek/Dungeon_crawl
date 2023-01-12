package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;

    public Actor(Cell cell, int health) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null && canGoThrough(nextCell)) {
            if (nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else {
                Actor skeleton = nextCell.getActor();
                this.fight(skeleton);
            }
        }
    }

    protected boolean canGoThrough(Cell cell) {
        return cell.getType() != CellType.WALL && cell.getType() != CellType.OPENED_DOOR && cell.getType() != CellType.CLOSED_DOOR
                && cell.getType() != CellType.OPENED_EXIT && cell.getType() != CellType.CLOSED_EXIT;
    }

    public abstract void fight(Actor actor);

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public abstract int getAttackStrength();


    public void setHealth(int health) {
        this.health = health;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public boolean checkNeighborForItem(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        return nextCell.getItem() != null;
    }

}
