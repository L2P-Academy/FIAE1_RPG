package model;

public class RaceModel {
	private int raceID;
	private int racialAbilityID;
	private String name;
	
	// Constructor
	public RaceModel(int raceID, int racialAbilityID, String name) {
		this.raceID = raceID;
		this.racialAbilityID = racialAbilityID;
		this.name = name;
	}
	
	// Getter & Setter
	public int getRaceID() {
		return raceID;
	}
	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getRacialAbilityID() {
		return racialAbilityID;
	}
	public void setRacialAbilityID(int racialAbilityID) {
		this.racialAbilityID = racialAbilityID;
	}
}