package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Spider extends Actor{

    public Spider(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "spider";
    }
}
