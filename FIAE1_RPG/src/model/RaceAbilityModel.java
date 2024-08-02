package model;

public class RaceAbilityModel {
	private int raceAbililtyID;
	private int damage;
	private int requiredLevel;
	private boolean isAOE;
	private String name;
	private Element element;
	
	public enum Element {
		FIRE, WATER, EARTH, LIGHT, DARKNESS, NEUTRAL, HOLY
	}
	
	// Constructor
	public RaceAbilityModel(int raceAbililtyID, String name, int damage, 
			boolean isAOE, int requiredLevel, Element element) {
		this.raceAbililtyID = raceAbililtyID;
		this.damage = damage;
		this.requiredLevel = requiredLevel;
		this.isAOE = isAOE;
		this.name = name;
		this.element = element;
	}
	
	// Getter & Setter
	public int getRaceAbililtyID() {
		return raceAbililtyID;
	}
	public void setRaceAbililtyID(int raceAbililtyID) {
		this.raceAbililtyID = raceAbililtyID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isAOE() {
		return isAOE;
	}
	public void setAOE(boolean isAOE) {
		this.isAOE = isAOE;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}
	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	
}
