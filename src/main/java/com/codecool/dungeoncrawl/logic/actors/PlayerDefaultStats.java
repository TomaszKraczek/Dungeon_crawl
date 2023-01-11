package com.codecool.dungeoncrawl.logic.actors;

public enum PlayerDefaultStats {
    ATTACK_POWER(5), ARMOR(0), HEALTH(20);

    private int defaultValue;
    PlayerDefaultStats(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    public int getDefaultValue(){
        return defaultValue;
    }
}
