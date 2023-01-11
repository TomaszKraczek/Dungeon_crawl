package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item{


    public Weapon(Cell cell, String name) {
        super(cell, name);
    }

    @Override
    public String getTileName() {
        return this.getName();
    }
}
