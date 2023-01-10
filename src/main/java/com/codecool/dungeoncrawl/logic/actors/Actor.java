package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract int getAttackStrength();


    public void setHealth(int health) {
        this.health = health;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null && !(nextCell.getType() == CellType.WALL)) {
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

    public abstract void fight(Actor actor);


        // do walidacji na czym player stoi
        //        System.out.println(cell.getNeighbor(dx, dy).getActor());
        //        System.out.println(cell.getItem());


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
}
