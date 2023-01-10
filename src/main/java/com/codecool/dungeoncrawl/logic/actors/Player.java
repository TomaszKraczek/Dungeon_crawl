package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {
    private ArrayList<Item> equipment = new ArrayList<>();
    public void setEquipment(Item item) {
        equipment.add(item);
    }

    public ArrayList<Item> getEquipment() {
        return equipment;
    }

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
}
