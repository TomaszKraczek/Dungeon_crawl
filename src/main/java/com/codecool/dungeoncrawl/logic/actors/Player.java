package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null && !(nextCell.getType() == CellType.WALL) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // do walidacji na czym player stoi
        //        System.out.println(cell.getNeighbor(dx, dy).getActor());
        //        System.out.println(cell.getItem());
    }


    public String getTileName() {
        return "player";
    }
}
