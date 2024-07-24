package model;

public class PlayerCharacterModel {

	private int characterID, raceID, classID, currentHP, maxHP, 
	currentMana, maxMana, currentXP, maxXP, level;
	private String name;

	public PlayerCharacterModel(int characterID, int raceID, int classID, int currentHP, int maxHP, int currentMana,
			int maxMana, int currentXP, int maxXP, int level, String name) {
		super();
		this.characterID = characterID;
		this.raceID = raceID;
		this.classID = classID;
		this.currentHP = currentHP;
		this.maxHP = maxHP;
		this.currentMana = currentMana;
		this.maxMana = maxMana;
		this.currentXP = currentXP;
		this.maxXP = maxXP;
		this.level = level;
		this.name = name;
	}

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public int getRaceID() {
		return raceID;
	}

	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getCurrentXP() {
		return currentXP;
	}

	public void setCurrentXP(int currentXP) {
		this.currentXP = currentXP;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public void setMaxXP(int maxXP) {
		this.maxXP = maxXP;
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
}
