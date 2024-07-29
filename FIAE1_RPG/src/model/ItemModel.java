package model;

/**
 * @author Weirdmustard
 * @version 1.3 - last modified 28.07.24
 * @since 02.05.24
 * @summary The ItemModel contains all attributes assignable to any item in the
 *          game
 */

public class ItemModel {
	private int itemID;
	private int value;
	private int requiredLevel;
	private int damage;
	private int defense;
	private boolean isQuestItem;
	private boolean isEquip;
	private String description;
	private String name;
	private Slot slot;

	public enum Slot {
		HEAD, CHEST, HANDS, LEGS, FEET, NONE
	}

	public ItemModel(int itemID, int value, int requiredLevel, int damage, int defense, boolean isQuestItem,
			boolean isEquip, String description, String name, Slot slot) {
		this.itemID = itemID;
		this.value = value;
		this.requiredLevel = requiredLevel;
		this.damage = damage;
		this.defense = defense;
		this.isQuestItem = isQuestItem;
		this.isEquip = isEquip;
		this.description = description;
		this.name = name;
		this.slot = slot;
	}

	// getter and setter

	public int getItemID() {
		return itemID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public boolean isQuestItem() {
		return isQuestItem;
	}

	public void setQuestItem(boolean isQuestItem) {
		this.isQuestItem = isQuestItem;
	}

	public boolean isEquip() {
		return isEquip;
	}

	public void setEquip(boolean isEquip) {
		this.isEquip = isEquip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Slot getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

}