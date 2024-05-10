package model;// Cedric Antunes v0.4

public class EquipModel extends ItemModel {
	private int itemDefense;
	private int requiredLevel;


	
	// Constructor with all input (Name, Quantity, Price, Damage, isQuestItem, Defense, req.Lvl)
	public EquipModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, boolean isQuestItem, int itemDefense, int requiredLevel, String itemDescription) {
		super(itemName, itemQuantity, itemPrice, itemDamage, isQuestItem, itemDescription);
		this.itemDefense = itemDefense;
		this.requiredLevel = requiredLevel;
	}
	
	// Constructor for Equipments: (Name, Quantity, Price, Damage, Defense, req.Lvl)
	public EquipModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, int itemDefense, int requiredLevel) {
	super(itemName, itemQuantity, itemPrice, itemDamage);
	this.itemDefense = itemDefense;
	this.requiredLevel = requiredLevel;
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
	
	// Examples for Equipments // Model(Name, amount, price, bonus attack, bonus armor, required level)
	EquipModel IronShortsword = new EquipModel("Eisen Kurzschwert", 1, 10, 10, 0, 1); // close weapon
	EquipModel IronLongsword = new EquipModel("Eisen Langschwert", 1, 30, 25, 5, 2); // close weapon
	EquipModel IronShortbow = new EquipModel("Eisen Kurzbogen", 1, 15, 30, 0, 1); // ranged weapon, two-handed
	EquipModel SteelZweihaender = new EquipModel("Stahl Zweihänder", 1, 100, 100, -10, 8); // two-handed
	
	EquipModel EisenHelm = new EquipModel("Eisen Helm", 1, 10, 0, 5, 1); // Head
	EquipModel LeatherArmor = new EquipModel("Leder Rüstung", 1, 20, 0, 15, 1); // Torso
	EquipModel IronChainMail = new EquipModel("Eisen Kettenhemd", 1, 100, -10, 40, 4); // Torso, Heavy 
	EquipModel SteelPlateMail = new EquipModel("Stahl Plattenrüstung", 1, 300, -25, 100, 8); // Torso, Heavy
	
}