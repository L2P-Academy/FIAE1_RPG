package model;


public class NpcCharacterModel extends CharacterModel{

	boolean isFriendly;
	boolean isTrader;
	boolean isQuestGiver;
	
	//constructor
	public NpcCharacterModel(String name, String race, int healthPoints, int baseDmg, int baseArmour, boolean isFriendly, boolean isTrader, boolean isQuestGiver) {
		super(name, race, healthPoints, baseDmg, baseArmour);
		this.isFriendly = isFriendly;
		this.isTrader = isTrader;
		this.isQuestGiver = isQuestGiver;
		
	}
	
	//Getter and Setter
	public boolean isFriendly() {
		return isFriendly;
	}
	
	public void setFriendly(boolean friendly) {
		this.isFriendly = friendly;
	}
	
	public boolean isTrader() {
		return isTrader;
	}
	
	public void setTrader(boolean trader) {
		this.isTrader = trader;
	}
	
	public boolean isQuestGiver() {
		return isQuestGiver;
	}
	
	public void setQuestGiver(boolean questGiver) {
		this.isQuestGiver = questGiver;
	}
}