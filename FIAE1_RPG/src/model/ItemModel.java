package model;

public class ItemModel<Type extends Enum<Type>> {
    private int itemID;
    private String name;
    private boolean isEquip;
    private int value;
    private int requiredLevel;
    private boolean isQuestItem;
    private String description;
    private int damage;
    private int defense;
    private Type type;

    public enum Slot {
        HEAD, CHEST, HANDS, LEGS, FEET, NONE
    }
    
    public ItemModel(int itemID, String name, boolean isEquip, int value, Type type, int requiredLevel, boolean isQuestItem, String description, int damage, int defense) {
        super();
    	this.itemID = itemID;
        this.name = name;
        this.isEquip = isEquip;
        this.value = value;
        this.requiredLevel = requiredLevel;
        this.isQuestItem = isQuestItem;
        this.description = description;
        this.damage = damage;
        this.defense = defense;

    }
    
    

    // Getter und Setter
    
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEquip() {
        return isEquip;
    }

    public void setEquip(boolean isEquip) {
        this.isEquip = isEquip;
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

    public boolean isQuestItem() {
        return isQuestItem;
    }

    public void setQuestItem(boolean isQuestItem) {
        this.isQuestItem = isQuestItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
    
