package com.codecool.dungeoncrawl.logic.items.crown;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Crown extends Item {

    public Crown(Cell cell, String name) {
        super(cell, name);

    }

    @Override
    public String getTileName() {
        return "crown";
    }
}
