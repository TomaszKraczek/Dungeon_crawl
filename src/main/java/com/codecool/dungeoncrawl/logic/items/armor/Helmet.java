package com.codecool.dungeoncrawl.logic.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Armor {
    public Helmet(Cell cell, String name, int armorPoints) {
        super(cell, name, armorPoints);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }
}
