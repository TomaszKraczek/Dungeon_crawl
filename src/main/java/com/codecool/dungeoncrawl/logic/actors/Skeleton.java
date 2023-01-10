package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    private int attackStrength = 2;

    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public int getAttackStrength() {
        return attackStrength;
    }
}
