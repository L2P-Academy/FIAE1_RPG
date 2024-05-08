package model;

public class ItemModel {

	// Fragen? -> SAMAEL SCHRÖDER

	private String itemName;
	private int itemQuantity;
	private double itemPrice;
	private int itemDamage;
	private boolean isQuestItem;

	// Item-Liste:
	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87

	// TO-DO 08.05.
	//
	//
	//

	// Standard Constructor
	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, boolean isQuestItem) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemDamage = itemDamage;
		this.isQuestItem = isQuestItem;
	}

	// Constructor für Quest-Items
	public ItemModel(String itemName, int itemQuantity) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.isQuestItem = true;
	}

	// Constructor für Equipment
	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.isQuestItem = false;
		this.itemDamage = itemDamage;
	}
	
	

	// Getter & Setter

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemDamage() {
		return itemDamage;
	}

	public void setItemDamage(int itemDamage) {
		this.itemDamage = itemDamage;
	}

	public boolean getIsQuestItem() {
		return isQuestItem;
	}

	public void setIsQuestItem(boolean isQuestItem) {
		this.isQuestItem = isQuestItem;
	}

// Item-Liste

	// Item-Liste:
	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87

	// itemName,itemQuantity,itemPrice,itemDamage,isQuestItem

	ItemModel item2 = new ItemModel("Stein", 1, 0.1, 5, false);

	ItemModel item3 = new ItemModel("Heiltrank", 1, 20, 0, false);

	ItemModel item5 = new ItemModel("Seil", 1, 5, 0, false);

	ItemModel item6 = new ItemModel("Banditenkopf", 0, 0, 0, true);
}
