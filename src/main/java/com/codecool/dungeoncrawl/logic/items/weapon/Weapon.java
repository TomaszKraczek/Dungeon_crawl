package com.codecool.dungeoncrawl.logic.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Weapon extends Item {
    private int attackPower;
    public Weapon(Cell cell, String name, int attackPower) {
        super(cell, name);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
