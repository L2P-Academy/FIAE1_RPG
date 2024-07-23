package model;

public class MobModel extends CharacterModel_OLD {
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
	
	// Example objects to test
	MobModel GoblinWarrior = new MobModel("Goblin Krieger", "Goblin", 80, 9, 4, false, 1, false);
	
	MobModel GoblinMage = new MobModel("Goblin Magier", "Goblin", 40, 3, 1, false, 3, false);
	
	MobModel GoblinShaman = new MobModel("Goblin Schamane", "Goblin", 45, 15, 2, false, 3, false);
	
	MobModel GoblinRogue = new MobModel("Goblin Schurke", "Goblin", 50, 15, 2, false, 2, false);

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