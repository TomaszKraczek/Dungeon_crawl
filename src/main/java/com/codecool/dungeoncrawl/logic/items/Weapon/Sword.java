package com.codecool.dungeoncrawl.logic.items.Weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Sword extends Weapon {

        private int attackPower;

        public Sword(Cell cell, String name, int attackPower) {
            super(cell, name, attackPower);
        }

        @Override
        public String getTileName() {
            return "sword";
        }

}
