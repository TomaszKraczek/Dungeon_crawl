package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {

    public Skeleton(Cell cell, int health, int attackStrength) {
        super(cell, health, attackStrength);

    }

    @Override
    public void move() {}

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public int getAttackStrength(){
        return MonstersStats.SKELETON.getAttackStrength();
    }

}
