package com.codecool.dungeoncrawl.logic.actors;

public enum MonstersStats {
    SKELETON(1, 1), SPIDER(2, 2), WARRIOR(3, 3);
    private int healthPoints;
    private int attackStrength;

    MonstersStats(int healthPoints, int attackStrength) {
        this.healthPoints = healthPoints;
        this.attackStrength = attackStrength;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
