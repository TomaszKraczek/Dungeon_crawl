package com.codecool.dungeoncrawl.logic.items.Key;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Key extends Item {
    public Key(Cell cell, String name) {
        super(cell, name);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}

