package com.codecool.dungeoncrawl.logic.items.Armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Armor extends Item {
    private int armorPoint;
    public Armor(Cell cell, String name, int armorPoint) {
        super(cell, name);
        this.armorPoint = armorPoint;
    }
    public int getArmorPoint(){
        return armorPoint;
    }
}
