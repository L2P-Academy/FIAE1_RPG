package model;

import java.util.ArrayList;

public class CharacterModel {
    private String name;
    private String race;
    private int healthPoints;
    private int baseDmg;
    private int baseArmour;
    private ArrayList<String> inventoryList;
    private ArrayList<String> abilityList;
    private int currentHealthPoints;

    // Constructor
    public CharacterModel(String name, String race, int healthPoints, int baseDmg, int baseArmour) {
        this.name = name;
        this.race = race;
        this.healthPoints = healthPoints;
        this.baseDmg = baseDmg;
        this.baseArmour = baseArmour;
        this.inventoryList = new ArrayList<>();
        this.abilityList = new ArrayList<>();
        this.currentHealthPoints = healthPoints;
    }
    
	// Method to add an item to the inventory
    public void addToInventory(String item) {
        inventoryList.add(item);
    }
    
    // Method to add an ability to the ability list
    public void addToAbility(String ability) {
    	abilityList.add(ability);
    }
    
    // Method to display character information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Race: " + race);
        System.out.println("Lebenspunkte: " + healthPoints);
        System.out.println("Schaden: " + baseDmg);
        System.out.println("Rüstung: " + baseArmour);
        System.out.println("Inventar: " + inventoryList);
        System.out.println("Fähigkeiten: " + abilityList);
    }

    // Getters and Setters
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

    public ArrayList<String> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(ArrayList<String> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public ArrayList<String> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(ArrayList<String> abilityList) {
        this.abilityList = abilityList;
    }
}