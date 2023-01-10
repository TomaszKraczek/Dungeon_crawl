package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {

    private int attackStrength =5;

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public int getAttackStrength() {
        return attackStrength;
    }

    public String getTileName() {
        return "player";
    }
    @Override
    public void fight (Actor skeleton){

        while (skeleton.getHealth()>0 && getHealth()>0) {
            if ((skeleton.getHealth() - attackStrength)<=0) {
                skeleton.setHealth((skeleton.getHealth() - attackStrength));
                this.getCell().setActor(null);
                System.out.println("You win");
                skeleton.getCell().setActor(this);
                this.setCell(skeleton.getCell());
            }
            else {
                skeleton.setHealth((skeleton.getHealth() - attackStrength));
                this.setHealth(getHealth() - skeleton.getAttackStrength());
            }
        }
    }
}
