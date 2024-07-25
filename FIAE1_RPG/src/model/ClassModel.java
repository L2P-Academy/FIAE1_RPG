package model;

public class ClassModel {
	private int classID;
	private String name;
	private int abilityID1;
	private int abilityID2;
	private int abilityID3;
	
	public ClassModel(int classID, String name, int abilityID1, int abilityID2, int abilityID3) {
		this.classID = classID;
		this.name = name;
		this.abilityID1 = abilityID1;
		this.abilityID2	= abilityID2;
		this.abilityID3 = abilityID3;
	}
	
	// Getter, Setter

	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAbilityID1() {
		return abilityID1;
	}
	public void setAbilityID1(int abilityID1) {
		this.abilityID1 = abilityID1;
	}

	public int getAbilityID2() {
		return abilityID2;
	}
	public void setAbilityID2(int abilityID2) {
		this.abilityID2 = abilityID2;
	}

	public int getAbilityID3() {
		return abilityID3;
	}
	public void setAbilityID3(int abilityID3) {
		this.abilityID3 = abilityID3;
	}
	
}
