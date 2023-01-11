package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Monster extends Actor{
    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }

    @Override
    public void fight(Actor actor) {

    }

    @Override
    public int getAttackStrength() {
        return 0;
    }
}
