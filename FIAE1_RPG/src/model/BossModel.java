package model;


public class BossModel {
	private int abilityID1, abilityID2, abilityID3, abilityID4, hp, questID, elementalResistance, armour, itemID, level;
	private String name;
	
	// Constructor
	public BossModel(int abilityID1, int abilityID2, int abilityID3, int abilityID4, int hp, int questID,
			int elementalResistance, int armour, int itemID, int level, String name) {
		super();
		this.abilityID1 = abilityID1;
		this.abilityID2 = abilityID2;
		this.abilityID3 = abilityID3;
		this.abilityID4 = abilityID4;
		this.hp = hp;
		this.questID = questID;
		this.elementalResistance = elementalResistance;
		this.armour = armour;
		this.itemID = itemID;
		this.level = level;
		this.name = name;
	}
	
	// Getter & Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAbilityID1() {
		return abilityID1;
	}
	public void setAbilityID1(int abilityID1) {
		this.abilityID1 = abilityID1;
	}
	public int getAbilityID2() {
		return abilityID2;
	}
	public void setAbilityID2(int abilityID2) {
		this.abilityID2 = abilityID2;
	}
	public int getAbilityID3() {
		return abilityID3;
	}
	public void setAbilityID3(int abilityID3) {
		this.abilityID3 = abilityID3;
	}
	public int getAbilityID4() {
		return abilityID4;
	}
	public void setAbilityID4(int abilityID4) {
		this.abilityID4 = abilityID4;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getElementalResistance() {
		return elementalResistance;
	}
	public void setElementalResistance(int elementalResistance) {
		this.elementalResistance = elementalResistance;
	}
	public int getArmour() {
		return armour;
	}
	public void setArmour(int armour) {
		this.armour = armour;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
