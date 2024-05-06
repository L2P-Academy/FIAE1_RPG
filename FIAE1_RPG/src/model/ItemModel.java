package model;

import javax.swing.JList;

public class ItemModel {
    private String itemName;
    private String itemDurability;
    private int itemQuantity;
    private int itemPrice;
    private int itemDamage;
    private String category;
    private boolean isQuestItem;
    
	// Fragen? -> SAMAEL SCHRÖDER
	
	// aus dem Modell übernommene Eigenschaften
    
	/*String name;
	int durability;
	int quantity;
	double price;
	boolean isQuestItem;
	int damage;
	JList <String> category;*/
    
    /*TODO
    
    Array statt List*/

		// Constructor
	
	   public ItemModel(String itemName, String itemDurability, int itemQuantity, int itemPrice, int itemDamage, boolean isQuestitem, String category) {
	        this.itemName = itemName;
	        this.itemDurability = itemDurability;
	        this.itemQuantity = itemQuantity;
	        this.itemPrice = itemPrice;
	        this.itemDamage = itemDamage;
	        
	   }
	   
	   // Display Item info
	   
	   public void displayIteminfo() {
	        System.out.println("Name: " + itemName);
	        System.out.println("Kategorie: "+category);
	        System.out.println("Zustand: " + itemDurability);
	        System.out.println("Schaden: " + itemDamage);
	        System.out.println(" ");
	        System.out.println("Anzahl: " + itemQuantity);
	        System.out.println(" ");
	        System.out.println("Kosten: " + itemPrice);
			// System.out.println("Für Quest benötigt?" + printIsQuestItem);
			// printIsQuestItem soll Ja oder Nein ausgeben, siehe Code unten
	        
	   }
	   
	   // Getter & Setter
	   
	   public String getItemName() {
	        return itemName;
	    }

	    public void setItemName(String itemName) {
	    	this.itemName = itemName;
	    }
	    
	    public String getitemDurability() {
	        return itemDurability;
	    }

	    public void setitemDurability(String itemDurability) {
	    	this.itemDurability = itemDurability;
	    }
	    
	    public int getitemQuantity() {
	        return itemQuantity;
	    }

	    public void setitemQuantity(int itemQuantity) {
	    	this.itemQuantity = itemQuantity;
	    }
	    
	    public int getitemPrice() {
	        return itemPrice;
	    }

	    public void setitemPrice(int itemPrice) {
	    	this.itemPrice = itemPrice;
	    }
	    
	    public int getitemDamage() {
	        return itemDamage;
	    }

	    public void setitemDamage(int itemDamage) {
	    	this.itemDamage = itemDamage;
	    }
	    
	    public void printIsQuestItem() {
    	    if (this.isQuestItem) {
    	        System.out.println("Ja");
    	    } else {
    	        System.out.println("Nein");
    	    }
    	}
	    
	   
	    // Ich krieg die getter und setter für QuestItem nicht hin
	    
	    /*public void setisQuestItem(boolean isQuestItem) {
	    	this.isQuestItem(isQuestItem);
	    }*/
	    
	    
		// public void category(Object[] categories) {
		//   for (Object category : categories) {
		//     this.category(category.toString());
		//	}
		// } 
		// "the method category Object[] in the type ItemModel is not applicable for the arguments (String)"
	    
	    // Sonderregel für isQuestItem
	    
	    
	    }
	    
	    