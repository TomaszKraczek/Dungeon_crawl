package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Armor.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon.Weapon;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player extends Actor {
    private int attackStrength;
    private int armorPoints;
    private int experience;
    private int playerLevel = 1;
    private ArrayList<Item> equipment = new ArrayList<>();


    public Player(Cell cell, int health) {
        super(cell, health);
    }

   public void addItemToEq(Item item) {
        equipment.add(item);
    }

    public ArrayList<String> getItemsNames(){
        return equipment.stream().map(Item::getName).collect(Collectors.toCollection(ArrayList<String>::new));
    }

    @Override
    public int getAttackStrength() {
        return attackStrength;
    }
    public int getArmorPoints(){
        return armorPoints;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void fight (Actor skeleton){

        while (skeleton.getHealth() > 0 && getHealth() > 0) {
            if ((skeleton.getHealth() - attackStrength) <= 0) {
                skeleton.setHealth((skeleton.getHealth() - attackStrength));
                this.getCell().setActor(null);
                System.out.println("You win");
                skeleton.getCell().setActor(this);
                this.setCell(skeleton.getCell());
            }
            else {
                skeleton.setHealth((skeleton.getHealth() - attackStrength));
                System.out.println("ja zadaje tyle dmg:" + this.getAttackStrength() + "zdrowie szkieleta:" + skeleton.getHealth());
                this.setHealth(getHealth() - skeleton.getAttackStrength());
                System.out.println("szkielet zadaje tyle dmg:" + skeleton.getAttackStrength() + "nasze zdrowie:" + this.getHealth());

            }
        }
        }





    @Override
    public boolean canGoThrough(Cell cell) {
        if (cell.getType() == CellType.CLOSED_DOOR &&
                equipment.stream()
                        .anyMatch(this::isKey)) {
            cell.setType(CellType.OPENED_DOOR);
            return true;
        } else return cell.getType() != CellType.WALL && cell.getType() != CellType.CLOSED_DOOR;
    }

    private boolean isKey(Item item){
        return "key".equals(item.getTileName());
    }

    public int getPlayerLvl() {
        return playerLevel;
    }

    public int getPlayerExp() {
        return experience;
    }
    public void updatePlayerStats(){
        this.attackStrength = getSumAttack() + PlayerDefaultStats.ATTACK_POWER.getDefaultValue();
        this.armorPoints = getSumArmor() + PlayerDefaultStats.ARMOR.getDefaultValue();
    }

    private int getSumArmor(){
        int sumArmorPoints = 0;
        for (Item item : equipment) {
            if (item instanceof Armor){
                sumArmorPoints += ((Armor) item).getArmorPoint();
            }
        }
        return sumArmorPoints;
    }
    private int getSumAttack(){
        int sumAttackPoints = 0;
        for (Item item : equipment) {
            if (item instanceof Weapon){
                sumAttackPoints += ((Weapon) item).getAttackPower();
            }
        }
        return sumAttackPoints;
    }
}
