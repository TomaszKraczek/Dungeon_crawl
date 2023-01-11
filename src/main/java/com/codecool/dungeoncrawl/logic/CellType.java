package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    OPENED_DOOR("opened door"),
    CLOSED_DOOR("closed door"),
    LEAFY_TREE("leafy tree"),
    CONIFER("conifer"),
    PATH("path"),
    FORESTERS_LODGE("forester's lodge"),
    GRASS("grass");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
