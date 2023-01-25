package com.codecool.dungeoncrawl.logic.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Sword extends Weapon {

        public Sword(Cell cell, String name, int attackPower) {
            super(cell, name, attackPower);
        }

        @Override
        public String getTileName() {
            return "sword";
        }


}
