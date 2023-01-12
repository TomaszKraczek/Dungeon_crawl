package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Monster extends Actor{
    public Monster(Cell cell, int health, int attackStrength) {
        super(cell, health);
    }

    public abstract void move();

    @Override
    public void fight(Actor actor) {}

    @Override
    public int getAttackStrength() {
        return 0;
    }
}
