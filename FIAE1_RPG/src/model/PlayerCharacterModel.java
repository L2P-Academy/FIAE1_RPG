package model;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;


public class PlayerCharacterModel extends CharacterModel {
	private int level;
	private int manaPoints;
	private int staminaPoints;
	private double expPoints;
	private Dictionary<String, Integer> equipList = new Hashtable<>();
	private ArrayList<QuestModel> activeQuests;
	private ArrayList<QuestModel> completedQuests;
	
	// Constructor
	public PlayerCharacterModel(String name, String race, int level, int healthPoints, int manaPoints, int staminaPoints, int baseDmg, int baseArmour) {
		super(name, race, healthPoints, baseDmg, baseArmour);
        setLevel(level); // changed to match the rest of the code!
        setExpPoints(0); // from: set_expPoints --> setExpPoints
        setManaPoints(manaPoints); // the rest of the code doesn't use _ (underscores) here
        setStaminaPoints(staminaPoints); // so i changed this model to match the rest. Made the most sense :)
        activeQuests = new ArrayList<>();
        completedQuests = new ArrayList<>();
    }

	// method to add item to equip
	public void addItemEquipList(String item, int quantity) {
		equipList.put(item, quantity);
	}
	
	// method to remove item from equip
	public void removeItemEquipList(String item) {
		equipList.remove(item);
	}
	
	// Getters and Setters
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getManaPoints() {
		return manaPoints;
	}

	public void setManaPoints(int manaPoints) {
		this.manaPoints = manaPoints;
	}

	public int getStaminaPoints() {
		return staminaPoints;
	}

	public void setStaminaPoints(int staminaPoints) {
		this.staminaPoints = staminaPoints;
	}

	public double getExpPoints() {
		return expPoints;
	}

	public void setExpPoints(double expPoints) {
		this.expPoints = expPoints;
	}

	public Dictionary<String, Integer> getEquipList() {
		return equipList;
	}
	
	public void setEquipList(Dictionary<String, Integer> equipList) {
		this.equipList = equipList;
	}

	public ArrayList<QuestModel> getActiveQuests() {
        return activeQuests;
    }

    public void setActiveQuests(ArrayList<QuestModel> activeQuests) {
        this.activeQuests = activeQuests;
    }

    public void addActiveQuest(QuestModel quest) {
        activeQuests.add(quest);
    }

	public ArrayList<QuestModel> getCompletedQuests() {
		return completedQuests;
	}

	public void setCompletedQuests(ArrayList<QuestModel> completedQuests) {
		this.completedQuests = completedQuests;
	}
}