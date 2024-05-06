package model;// Cedric Antunes v0.3
import java.util.ArrayList;

public class EquipModel extends ItemModel {
	private int requiredLevel;
	private ArrayList<String> slot;
	
	
	// Constructor 
	public EquipModel(String itemName, int itemDurability, int itemQuantity, double itemPrice, int itemDamage, 
					  boolean isQuestItem, int requiredLevel) {
	super(itemName, itemDurability, itemQuantity, itemPrice, itemDamage, isQuestItem);
	this.requiredLevel = requiredLevel;
	this.slot = new ArrayList<>();
	}
	
	// Method to add a String entry into the ArrayList slot 
	// Ex.: for an ironsword item; "MainHand" and "OffHand", since a singlehanded weapon can be on both hands
	// 		it would require the user to use the methode twice, once for MainHand and once for OffHand.
	public void addToSlot(String slotEntry1) {
		this.slot.add(slotEntry1);
	}
	
	// Method to display the equipable item full information
	public void displayInfo() {
		
		
		System.out.println("Kategorie: " + getCategory()); // zB.: Waffe
		System.out.println("Ausrüstungs-Slot: " + getSlot()); // zB.: MainHand, OffHand
		System.out.println("Benötigtes Level zum Ausrüsten: " + getRequiredLevel());
		System.out.println("Namen " + getItemName());
		System.out.println("Durabilität: " + getItemDurability());
		System.out.println("Quantität: " + getItemQuantity());
		System.out.println("Kaufpreis: " + getItemPrice());
		System.out.println("Schaden: " + getItemDamage());	
		
		if (getIsQuestItem() == true) {
			System.out.println("Dies ist ein Quest Gegenstand!");
		} 
		//else {
		//	System.out.println("Dies ist kein Quest Gegenstand");
		//}
	}
	
	// Getters and Setters
	public int getRequiredLevel() {
		return requiredLevel;
	}
	
	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}
	
	public ArrayList<String> getSlot() {
		return slot;
	}
	
	public void setSlot(ArrayList<String> slot) {
		this.slot = slot;
	}
}