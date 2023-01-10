package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Actor{

    public Spider(Cell cell) {
        super(cell);
    }


    @Override
    public void move(int dx, int dy) {

    }


    public String getTileName() {
        return "spider";
    }
}
