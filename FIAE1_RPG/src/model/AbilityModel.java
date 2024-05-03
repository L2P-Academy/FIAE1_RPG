package model; // v0.2

import java.util.ArrayList;

public class AbilityModel {
	
	boolean isRanged, isAOE;
	ArrayList<String> elementsList; // neutral, fire, water, wind, earth?
	
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

}
