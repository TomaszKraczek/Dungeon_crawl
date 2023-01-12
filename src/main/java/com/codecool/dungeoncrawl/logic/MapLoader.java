package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Warrior;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Spider;
import com.codecool.dungeoncrawl.logic.items.armor.Helmet;
import com.codecool.dungeoncrawl.logic.items.key.Key;
import com.codecool.dungeoncrawl.logic.items.weapon.Sword;


import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public GameMap loadMap (InputStream is) {
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> {
                            cell.setType(CellType.EMPTY);
                        }
                        case '#' -> {
                            cell.setType(CellType.WALL);
                        }
                        case '.' -> {
                            cell.setType(CellType.FLOOR);
                        }
                        case 'c' -> {
                            cell.setType(CellType.CLOSED_DOOR);
                        }
                        case 'l' -> {
                            cell.setType(CellType.LEAFY_TREE);
                        }
                        case '^' -> {
                            cell.setType(CellType.CONIFER);
                        }
                        case 'p' -> {
                            cell.setType(CellType.PATH);
                        }
                        case 'f' -> {
                            cell.setType(CellType.FORESTERS_LODGE);
                        }
                        case 'g' -> {
                            cell.setType(CellType.GRASS);
                        }
                        case 's' -> {
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                        }
                        case '@' -> {
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                        }
                        case 'k' -> {
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "Blue key");
                        }
                        case '1' -> {
                            cell.setType(CellType.FLOOR);
                            new Sword(cell, "Two-handed sword", 4);
                        }
                        case 'S' -> {
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Spider(cell));
                        }
                        case 'W' -> {
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Warrior(cell));
                        }
                        case 'H' -> {
                            cell.setType(CellType.FLOOR);
                            new Helmet(cell, "Iron helmet", 2);
                        }
                        default -> {
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                        }
                    }
                }
            }
        }
        return map;
    }

}
