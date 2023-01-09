package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    public String getTileName() {
        return null;
    }
    public int getX() {
        return 0;
    }
    public int getY() {
        return 0;
    }
}
