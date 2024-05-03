package model;// Cedric Antunes v0.1 // I need the ItemModel in order to work and add to the list 
import java.util.ArrayList;

public class EquipModel {//extends ItemModel {
	private int requiredLevel;
	private ArrayList<String> slot;
	
	/* 
	 *	// Constructor 
		public EquipModel(String name, int durability, double price, boolean isQuestItem, int damage, List<String> category, int requiredLevel, List<String> slot) {
		super(name, durability, quantity, price, isQuestItem, damage, category);
		this.requiredLevel = requiredLevel;
		this.slot = new ArrayList<>();
		}
	*/
	
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