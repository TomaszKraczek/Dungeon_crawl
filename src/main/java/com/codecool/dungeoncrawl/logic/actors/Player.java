package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;

public class Player extends Actor {
    private ArrayList<String> equipment = new ArrayList<>();
    public void setEquipment(ArrayList<String> equipment, String item) {
        equipment.add(item);
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
}
