package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Spider extends Monster{

    public Spider(Cell cell) {
        super(cell);
    }

    @Override
    public void move() {
        String direction = getRandomDirection();
        switch (direction) {
            case "UP" -> move(0, -1);
            case "DOWN" -> move(0, 1);
            case "LEFT" -> move(-1, 0);
            case "RIGHT" -> move(1, 0);
        }
    }

    private String getRandomDirection(){
        List<String> directions = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
        Random random = new Random();
        return directions.get(random.nextInt(directions.size()));
    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
