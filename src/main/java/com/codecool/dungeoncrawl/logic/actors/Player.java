package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> equipment = new ArrayList<>();
    public void addItemToEq(Item item) {
        equipment.add(item);
    }

    public Player(Cell cell) {
        super(cell);
    }

    public ArrayList<String> getItemsNames(){
        ArrayList<String> names = new ArrayList<>();
        for (Item item : equipment) {
            names.add(item.getName());
        }
        return names;
    }

    public String getTileName() {
        return "player";
    }
    public ArrayList<Item> getEquipment(){
        return equipment;
    }

    public void showItems(){
        for (Item item : equipment) {
            System.out.println(item.getName());
        }
    }

    // TODO: usunąc bo to metoda testowa wypełniająca ekwipunek
//    public void testFillEq(){
//        equipment.add(new Weapon("Sword"));
//        equipment.add(new Weapon("Bow"));
//        equipment.add(new Weapon("Axe"));
//        equipment.add(new Weapon("Halabard"));
//    }
}
