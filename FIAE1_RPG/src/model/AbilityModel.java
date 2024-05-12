package model; // v0.2

import java.util.ArrayList;

public class AbilityModel {
	
	private String name;
	private boolean isRanged, isAOE;
	private ArrayList<String> elementsList; // neutral, fire, water, wind, earth?
	
	// Constructors
	
	public AbilityModel(String name, boolean isRanged, boolean isAOE){ // model for ranged and AOE
		this.name = name;
		this.isRanged = isRanged;
		this.isAOE = isAOE;
		this.elementsList = new ArrayList<>();
	}
	
	public AbilityModel(String name, boolean isRanged) { // model for ranged
		this.name = name;
		this.isRanged = isRanged;
		this.elementsList = new ArrayList<>();
	}
	
	public AbilityModel() { // model for close
		this.elementsList = new ArrayList<>();
	}

	// Getters and Setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsRanged() {
        return isRanged;
    }

    public void setIsRanged(boolean isRanged) {
        this.isRanged = isRanged;
    }
	public boolean getIsAOE() {
        return isAOE;
    }

    public void setIsAOE(boolean isAOE) {
        this.isAOE = isAOE;
    }
    public ArrayList<String> getElementsList() {
        return elementsList;
    }

    public void setElementsList(ArrayList<String> elementsList) {
        this.elementsList = elementsList;
    }
}
