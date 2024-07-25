package model;

public class RaceModel {
	private int raceID;
	private String name;
	private int racialAbilityID;
	
	public RaceModel(int raceID, String name, int racialAbilityID) {
		this.raceID = raceID;
		this.name = name;
		this.racialAbilityID = racialAbilityID;
	}
	
	// Getter, Setter

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