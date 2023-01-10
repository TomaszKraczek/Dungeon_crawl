package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> equipment = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
    public ArrayList<Item> getEquipment(){
        return equipment;
    }
    public void testFillEq(){
        equipment.add(new Weapon("Sword"));
        equipment.add(new Weapon("Bow"));
        equipment.add(new Weapon("Axe"));
    }
    public void showEqList(){
        for (Item item : equipment) {
            System.out.println(item);
        }
    }
}
