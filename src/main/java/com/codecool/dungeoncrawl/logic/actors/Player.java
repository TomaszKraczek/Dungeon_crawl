package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Armor.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon.Weapon;

import java.util.ArrayList;

public class Player extends Actor {
    private int attackStrength = 0;
    private int armorPoints = 0;
    private int experience = 0;
    private int playerLevel = 1;
    private ArrayList<Item> equipment = new ArrayList<>();
    public void addItemToEq(Item item) {
        equipment.add(item);
    }

    public Player(Cell cell) {
        super(cell);
    }

    public ArrayList<String> getItemsNames(){
        ArrayList<String> names = new ArrayList<>();
        for (Item item : equipment) {
            names.add(item.getName());
        }
        return names;
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
    public ArrayList<Item> getEquipment(){
        return equipment;
    }

    public void showItems(){
        for (Item item : equipment) {
            System.out.println(item.getName());
        }
    }

    // TODO: usunąc bo to metoda testowa wypełniająca ekwipunek
//    public void testFillEq(){
//        equipment.add(new Weapon("Sword"));
//        equipment.add(new Weapon("Bow"));
//        equipment.add(new Weapon("Axe"));
//        equipment.add(new Weapon("Halabard"));
//    }
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
