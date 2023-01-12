package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {
     private int attackStrength = 2;

    public Skeleton(Cell cell) {
        super(cell);

    }

    @Override
    public void move() {}

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public int getAttackStrength() {
        return attackStrength;
    }

}
