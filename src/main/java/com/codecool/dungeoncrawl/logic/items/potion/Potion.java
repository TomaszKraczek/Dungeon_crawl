package com.codecool.dungeoncrawl.logic.items.potion;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Potion extends Item {
    int improveHealth;
    public Potion(Cell cell, String name, int improveHealth) {
        super(cell, name);
        this.improveHealth = improveHealth;
    }

    @Override
    public String getTileName() {
        return "potion";
    }

    public int getImproveHealth() {
        return improveHealth;
    }
}
