package model;

import java.util.Dictionary;
import java.util.Hashtable;

public class PlayerCharacterModel extends CharacterModel {

	private int level;
	private int manaPoints;
	private int staminaPoints;
	private int expPoints;
	private Dictionary<String, Integer> equipList = new Hashtable<>();
	
	// Constructor
	public PlayerCharacterModel(String name, String race, int level, int healthPoints, int manaPoints, int staminaPoints, int baseDmg, int baseArmour) {
		super(name, race, healthPoints, baseDmg, baseArmour);
        set_level(level);
        set_expPoints(0);
        set_manaPoints(manaPoints);
        
    }

	// method to add item to equip
	public void add_item_equipList(String item, int quantity) {
		equipList.put(item, quantity);
	}
	
	// method to remove item from equip
	public void remove_item_equipList(String item) {
		equipList.remove(item);
	}
	
	// getters/setters
	public int get_level() {
		return level;
	}

	public void set_level(int lvl) {
		this.level = level;
	}

	public int get_manaPoints() {
		return manaPoints;
	}

	public void set_manaPoints(int manaPoints) {
		this.manaPoints = manaPoints;
	}

	public int get_staminaPoints() {
		return staminaPoints;
	}

	public void set_staminaPoints(int staminaPoints) {
		this.staminaPoints = staminaPoints;
	}

	public int get_expPoints() {
		return expPoints;
	}

	public void set_expPoints(int expPoints) {
		this.expPoints = expPoints;
	}

	public Dictionary<String, Integer> get_equipList() {
		return equipList;
	}
}
