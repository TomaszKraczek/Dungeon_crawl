package com.codecool.dungeoncrawl.logic.items.Armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Helmet extends Armor {
    private int armorPoints;

    public Helmet(Cell cell, String name, int armorPoints) {
        super(cell, name, armorPoints);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }
}
