package model;// Cedric Antunes v0.35
import java.util.ArrayList;

public class EquipModel extends ItemModel {
	private int itemDefense;
	private int requiredLevel;

	
	// Constructor for Armor (Name, Quantity, Price, Defense, req.Lvl)
	public EquipModel(String itemName, int itemQuantity, double itemPrice, int itemDefense, int requiredLevel) {
		super(itemName, itemQuantity, itemPrice);
		this.requiredLevel = requiredLevel;
		this.itemDefense = itemDefense;
	}
	
	// Constructor for Weapon: (Name, Quantity, Price, Damage, req.Lvl)
	public EquipModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, int requiredLevel) {
	super(itemName, itemQuantity, itemPrice, itemDamage);
	this.requiredLevel = requiredLevel;
	}
	
	// Constructor with all input (Name, Quantity, Price, Damage, isQuestItem, req.Lvl, Defense)
	public EquipModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, boolean isQuestItem, int requiredLevel, int itemDefense) {
		super(itemName, itemQuantity, itemPrice, itemDamage, isQuestItem);
		this.requiredLevel = requiredLevel;
		this.itemDefense = itemDefense;
	}
	
	// Getters and Setters
	public int getRequiredLevel() {
		return requiredLevel;
	}
	
	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}
	
	public int getItemDefense() {
		return itemDefense;
	}
	
	public void setItemDefense(int itemDefense) {
		this.itemDefense = itemDefense;
	}
}