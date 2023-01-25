package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.armor.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.crown.Crown;
import com.codecool.dungeoncrawl.logic.items.potion.Potion;
import com.codecool.dungeoncrawl.logic.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player extends Actor {
    private int id;
    private int attackStrength;
    private int armorPoints;
    private int experience;
    private int playerLevel = 1;
    private boolean isPlayerKilled=false;

    private boolean hasCrown=false;
    private ArrayList<Item> equipment = new ArrayList<>();
    private Cell cell;

    private String name;

    public Player(int id, Cell cell, int health, String name) {
        super(cell, health);
        this.id = id;
        this.cell=cell;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
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
    public void fight (Actor actor){
        while (actor.getHealth() > 0 && getHealth() > 0) {
            if (getHealth() - actor.getAttackStrength() <= 0) {
                isPlayerKilled=true;
                break;
            } else if ((actor.getHealth() - attackStrength) <= 0) {
                actor.setHealth((actor.getHealth() - attackStrength));
                cell.getMap().removeMonster((Monster) actor);
                this.getCell().setActor(null);
                System.out.println("You win");
                actor.getCell().setActor(this);
                this.setCell(actor.getCell());
                this.setHealth(getHealth() - actor.getAttackStrength());
            } else {
                actor.setHealth((actor.getHealth() - attackStrength));
                System.out.println("ja zadaje tyle dmg:" + this.getAttackStrength() + "zdrowie szkieleta:" + actor.getHealth());
                //this.setHealth(getHealth() - actor.getAttackStrength());
                System.out.println("szkielet zadaje tyle dmg:" + actor.getAttackStrength() + "nasze zdrowie:" + this.getHealth());
            }
        }
    }

    public boolean isPlayerKilled(){
        return isPlayerKilled;
    }

    public void checkCellForCrown(){
        Item item= getCell().getItem();
           if (item instanceof Crown){
               equipment.add(item);
                hasCrown=true;
        }
    }

    public boolean hasCrown(){
        return hasCrown;
    }

    @Override
    public boolean canGoThrough(Cell cell) {
        if ((cell.getType() == CellType.CLOSED_DOOR || cell.getType() == CellType.CLOSED_EXIT) &&
                equipment.stream().anyMatch(this::isKey)) {
            switch (cell.getType()) {
                case CLOSED_DOOR -> cell.setType(CellType.OPENED_DOOR);
                case CLOSED_EXIT -> cell.setType(CellType.OPENED_EXIT);
            }
            equipment.remove(equipment.stream().filter(this::isKey).findFirst().get());
            return true;
        } else return cell.getType() != CellType.WALL && cell.getType() != CellType.CLOSED_DOOR && cell.getType() != CellType.CLOSED_EXIT;
    }

    private boolean isKey(Item item){
        return "key".equals(item.getTileName());
    }

    public int getPlayerLvl() {
        return playerLevel;
    }

    public int incrementPlayerLevel() {
        return ++this.playerLevel;
    }

    public int getPlayerExp() {
        return experience;
    }
    public void updatePlayerStats(){
        this.attackStrength = getSumAttack() + PlayerDefaultStats.ATTACK_POWER.getDefaultValue();
        this.armorPoints = getSumArmor() + PlayerDefaultStats.ARMOR.getDefaultValue();
    }

    public void drinkPotion(Potion potion) {
        if (getX() == potion.getX() && getY() == potion.getY()) {
            setHealth(getHealth() + potion.getImproveHealth());
        }
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
