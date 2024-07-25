package model;

public class RaceAbilityModel {
	private int raceAbililtyID;
	private String name;
	private int damage;
	private boolean isAOE;
	private int requiredLevel;
	private Element element;
	
	public enum Element {
		FIRE, WATER, EARTH, LIGHT, DARKNESS, NEUTRAL, HOLY
	}
	
	public RaceAbilityModel(int raceAbililtyID, String name, int damage, 
			boolean isAOE, int requiredLevel, Element element) {
		this.raceAbililtyID = raceAbililtyID;
		this.name = name;
		this.damage = damage;
		this.isAOE = isAOE;
		this.requiredLevel = requiredLevel;
		this.element = element;
	}
	
	// Getter und Setter

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
