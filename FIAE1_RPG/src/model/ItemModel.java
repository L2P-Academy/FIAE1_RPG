package model;

import javax.swing.JList;

public class ItemModel {
    private String itemName;
    private String itemDurability;
    private int itemQuantity;
    private int itemPrice;
    private int itemDamage;
    
	// Fragen? -> SAMAEL SCHRÖDER
	
	// aus dem Modell übernommene Eigenschaften
    
	/*String name;
	int durability;
	int quantity;
	double price;
	boolean isQuestItem;
	int damage;
	JList <String> category;*/

		// Constructor
	
	   public ItemModel(String itemName, String itemDurability, int itemQuantity, int itemPrice, int itemDamage) {
	        this.itemName = itemName;
	        this.itemDurability = itemDurability;
	        this.itemQuantity = itemQuantity;
	        this.itemPrice = itemPrice;
	        this.itemDamage = itemDamage;
	        
	   }
	   
	   // Display Item info
	   
	   public void displayIteminfo() {
	        System.out.println("Name: " + itemName);
	        System.out.println("Zustand: " + itemDurability);
	        System.out.println("Schaden: " + itemDamage);
	        System.out.println(" ");
	        System.out.println("Anzahl: " + itemQuantity);
	        System.out.println(" ");
	        System.out.println("Kosten: " + itemPrice);
	        
	   }
	   
	   // Getter & Setter
	   
	   public String getItemName() {
	        return itemName;
	    }

	    public void setItemName(String itemName) {
	    	this.itemName = itemName;
	    }
	    
	    public String itemDurability() {
	        return itemDurability;
	    }

	    public void itemDurability(String itemDurability) {
	    	this.itemDurability = itemDurability;
	    }
	    
	    public int itemQuantity() {
	        return itemQuantity;
	    }

	    public void itemQuantity(int itemQuantity) {
	    	this.itemQuantity = itemQuantity;
	    }
	    
	    public int itemPrice() {
	        return itemQuantity;
	    }

	    public void itemPrice(int itemPrice) {
	    	this.itemPrice = itemPrice;
	    }
	    
	    public int itemDamage() {
	        return itemDamage;
	    }

	    public void itemDamage(int itemDamage) {
	    	this.itemDamage = itemDamage;
	    }
	    
	    public void isQuestItem(boolean isQuestItem) {
	    	this.isQuestItem(isQuestItem);
	    	
	    }
	    public void category(String category) {
	    	this.category(category);
	    }
	    
	}
	    