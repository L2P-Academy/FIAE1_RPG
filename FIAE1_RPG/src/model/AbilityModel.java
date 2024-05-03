package model; // v0.2

import java.util.ArrayList;

public class AbilityModel {
	
	private boolean isRanged, isAOE;
	private ArrayList<String> elementsList; // neutral, fire, water, wind, earth?
	
	// Constructors
	
	public AbilityModel(boolean isRanged, boolean isAOE) { // model for ranged and AOE
		this.isRanged = isRanged;
		this.isAOE = isAOE;
		this.elementsList = new ArrayList<>();
	}
	
	public AbilityModel(boolean isRanged) { // model for ranged
		this.isRanged = isRanged;
		this.elementsList = new ArrayList<>();
	}
	
	public AbilityModel() { // model for close
		this.elementsList = new ArrayList<>();
	}

	// Getters and Setters
	
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
