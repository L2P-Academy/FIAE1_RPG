package model; // v0.2

public class AbilityModel <AbilityElement extends Enum<AbilityElement>>{
	
	private int abilityID, damage, level;
	private boolean isAOE;
	private String name;
	private AbilityElement abilityElement;
	
	public enum AbilityElement {
		Fire, Water, Earth, Light, Darkness, Neutral, Holy
	}
	
	// Constructor
	public AbilityModel(int abilityID, int damage, int level, String name, boolean isAOE,
			model.AbilityModel.AbilityElement abilityElement) {
		super();
		this.abilityID = abilityID;
		this.damage = damage;
		this.level = level;
		this.name = name;
		this.isAOE = isAOE;
		this.abilityElement = abilityElement;
	}

	// Getter & Setter
	public int getAbilityID() {
		return abilityID;
	}

	public void setAbilityID(int abilityID) {
		this.abilityID = abilityID;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAOE() {
		return isAOE;
	}

	public void setAOE(boolean isAOE) {
		this.isAOE = isAOE;
	}

	public AbilityElement getAbilityElement() {
		return abilityElement;
	}

	public void setAbilityElement(AbilityElement abilityElement) {
		this.abilityElement = abilityElement;
	}
	
}
