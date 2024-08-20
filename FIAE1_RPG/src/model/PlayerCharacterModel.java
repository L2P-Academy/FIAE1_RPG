package model;

public class PlayerCharacterModel {

	private int characterID, raceID, classID, currentHP, maxHP, 
	currentMana, maxMana, currentXP, maxXP, level, currentLocation;
	private String gender;
	private String name;

	public PlayerCharacterModel(int characterID,  String name, int raceID, 
			int classID, String gender, int currentLocation, int currentXP, 
			int maxXP, int level, int currentHP, int maxHP, int currentMana, int maxMana) {
		super();
		this.characterID = characterID;
		this.name = name;
		this.raceID = raceID;
		this.classID = classID;
		this.gender = gender;
		this.currentLocation = currentLocation;
		this.currentXP = currentXP;
		this.maxXP = maxXP;
		this.level = level;
		this.currentHP = currentHP;
		this.maxHP = maxHP;
		this.currentMana = currentMana;
		this.maxMana = maxMana;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
