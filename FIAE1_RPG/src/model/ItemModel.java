package model;

import java.util.List;

public class ItemModel {

	private String itemName;
	private String itemDurability;
	private int itemQuantity;
	private double itemPrice;
	private int itemDamage;
	private List<String> category;
	private boolean isQuestItem;

	// Fragen? -> SAMAEL SCHRÖDER

	// aus dem Modell übernommene Eigenschaften
	/*
	 * String name; 
	 * int durability; 
	 * int quantity; 
	 * double price; 
	 * boolean isQuestItem;
	 * int damage; 
	 * JList <String> category;
	 */

	// Constructor

	public ItemModel(String itemName, String itemDurability, int itemQuantity, int itemPrice, int itemDamage,
			boolean isQuestitem, String category) {
		this.itemName = itemName;
		this.itemDurability = itemDurability;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemDamage = itemDamage;

	}

	   // Getter & Setter

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getItemDurability() {
			return itemDurability;
		}

		public void setItemDurability(String itemDurability) {
			this.itemDurability = itemDurability;
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

		public void setItemPrice(int itemPrice) {
			this.itemPrice = itemPrice;
		}

		public int getItemDamage() {
			return itemDamage;
		}

		public void setItemDamage(int itemDamage) {
			this.itemDamage = itemDamage;
		}

		public List<String> getCategory() {
			return category;
		}

		public void setCategory(List<String> category) {
			this.category = category;
		}

		public boolean isQuestItem() {
			return isQuestItem;
		}

		public void setQuestItem(boolean isQuestItem) {
			this.isQuestItem = isQuestItem;
		}
	}

//// Display Item info
//
///* public void displayIteminfo() {
//     System.out.println("Name: " + itemName);
//     System.out.println("Kategorie: "+category);
//     System.out.println("Zustand: " + itemDurability);
//     System.out.println("Schaden: " + itemDamage);
//     System.out.println(" ");
//     System.out.println("Anzahl: " + itemQuantity);
//     System.out.println(" ");
//     System.out.println("Kosten: " + itemPrice);
//		System.out.println("Für Quest benötigt?" + printIsQuestItem);
//		*/
//// Irrelevant für Model
	    