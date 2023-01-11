package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(17,23));
        tileMap.put("sword", new Tile(0,29));
        tileMap.put("helmet", new Tile(4,22));
        tileMap.put("opened door", new Tile(2,9));
        tileMap.put("closed door", new Tile(0,9));
        tileMap.put("leafy tree", new Tile(4,2));
        tileMap.put("conifer", new Tile(1,1));
        tileMap.put("path", new Tile(5,0));
        tileMap.put("forester's lodge", new Tile(19,10));
        tileMap.put("grass", new Tile(0,2));
        tileMap.put("spider", new Tile(28,5));
        tileMap.put("warrior", new Tile(30,2));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

//    public void (Cell cell, conetxt, int x, int y){
//        if (cell.getActor() != null) {
//            Tiles.drawTile(context, cell.getActor(), x, y);
//            System.out.println(cell.getActor());
//        }
//        else if (cell.getItem() != null){
//            Tiles.drawTile(context, cell.getItem(), x, y);
//        }
//        else {
//            Tiles.drawTile(context, cell, x, y);
//        }
//    }
//    }
}
