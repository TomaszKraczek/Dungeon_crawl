package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {

    private int attackStrength =5;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public void fight (Cell cell, Player player, Skeleton skeleton){

        while (skeleton.getHealth()>0 && player.getHealth()>0) {
            if ((skeleton.getHealth() - attackStrength)<=0) {
                System.out.println("You win");
                cell.setActor(player);
            }
            else {
                skeleton.setHealth((skeleton.getHealth() - attackStrength));
                player.setHealth(player.getHealth() - skeleton.getAttackStrength());
            }
        }
    }
}
