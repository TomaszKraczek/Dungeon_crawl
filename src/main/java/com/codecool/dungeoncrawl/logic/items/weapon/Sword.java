package com.codecool.dungeoncrawl.logic.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;

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
