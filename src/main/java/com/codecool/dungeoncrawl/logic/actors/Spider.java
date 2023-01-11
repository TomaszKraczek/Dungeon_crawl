package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Actor{

    public Spider(Cell cell) {
        super(cell);
    }

    @Override
    public void fight(Actor actor) {

    }

    @Override
    public int getAttackStrength() {
        return 0;
    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
