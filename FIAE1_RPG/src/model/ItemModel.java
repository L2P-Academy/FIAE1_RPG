package model;

public class ItemModel {
    private int itemID;
    private int value;
    private int requiredLevel;
    private int damage;
    private int defense;
    private boolean isQuestItem, isEquip;
    private String description;
    private String name;
    private Slot slot;

    public enum Slot {
        HEAD, CHEST, HANDS, LEGS, FEET, NONE
    }
    
    public ItemModel(int itemID, int value, int requiredLevel, int damage, int defense, boolean isQuestItem,
			boolean isEquip, String description, String name, Slot slot) {
		super();
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

    // Getter und Setter

	public int getItemID() {
        return itemID;
    }

    public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	public void setSlot(Slot slot) {
		this.slot = slot;
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

    public boolean getIsQuestItem() {
        return isQuestItem;
    }

    public void setQuestItem(boolean isQuestItem) {
        this.isQuestItem = isQuestItem;
    }

    public String getItemDescription() {
        return description;
    }

    public void setItemDescription(String description) {
        this.description = description;
    }

    public int getItemDamage() {
        return damage;
    }

    public void setItemDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

	public Slot getType() {
		return slot;
	}

	public void setType(Slot slot) {
		this.slot = slot;
	}
}
    
