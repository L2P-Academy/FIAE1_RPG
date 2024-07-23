package model;

import java.util.ArrayList;

public class CharacterModel_OLD {
    private String name;
    private String race;
    private int healthPoints;
    private int currentHealthPoints;
    private int baseDmg;
    private int baseArmour;
    private ArrayList<ItemModel> inventoryList;
    private ArrayList<AbilityModel> abilityList;

    // Constructor
    public CharacterModel_OLD(String name, String race, int healthPoints, int baseDmg, int baseArmour) {
        this.name = name;
        this.race = race;
        this.healthPoints = healthPoints;
        this.baseDmg = baseDmg;
        this.baseArmour = baseArmour;
        this.inventoryList = new ArrayList<>();
        this.abilityList = new ArrayList<>();
    }
    
	// Method to add an item to the inventory
    public void addToInventory(ItemModel item) {
        inventoryList.add(item);
    }
    
    // Method to add an ability to the ability list
    public void addToAbility(AbilityModel ability) {
    	abilityList.add(ability);
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public int getBaseArmour() {
        return baseArmour;
    }

    public void setBaseArmour(int baseArmour) {
        this.baseArmour = baseArmour;
    }

    public ArrayList<ItemModel> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(ArrayList<ItemModel> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public ArrayList<AbilityModel> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(ArrayList<AbilityModel> abilityList) {
        this.abilityList = abilityList;
    }
}