package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Warrior extends Monster {

    public Warrior(Cell cell, int health, int attackStrength) {
        super(cell, health, attackStrength);

    }

    @Override
    public void move() {
        if (checkNeighborForItem(0,-1)){
            move(-1,-1);
        } else if (checkNeighborForItem(1,0)){
            move(1,-1);
        } else if (checkNeighborForItem(0,1)){
            move(1,1);
        } else if (checkNeighborForItem(-1,0)){
            move(-1,1);
        }
    }

    @Override
    public String getTileName() {
        return "warrior";
    }

    @Override
    public int getAttackStrength(){
        return MonstersStats.WARRIOR.getAttackStrength();
    }
}
