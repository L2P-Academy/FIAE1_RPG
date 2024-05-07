package model;

public class MobModel extends CharacterModel {
	private boolean isFriendly;
	private int level;
	private boolean isFlying;

	// Constructor
	public MobModel(String name, String race, int healthPoints, int baseDmg, int baseArmour, boolean isFriendly,
			int level, boolean isFlying) {
		super(name, race, healthPoints, baseDmg, baseArmour);
		this.isFriendly = isFriendly;
		this.level = level;
		this.isFlying = isFlying;
	}

	// Method to display extra mob information
	public void displayMobInfo() {
	}

	// Getter & Setter
	public boolean isFriendly() {
		return isFriendly;
	}

	public void setFriendly(boolean friendly) {
		isFriendly = friendly;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isFlying() {
		return isFlying;
	}

	public void setFlying(boolean flying) {
		isFlying = flying;
	}
}