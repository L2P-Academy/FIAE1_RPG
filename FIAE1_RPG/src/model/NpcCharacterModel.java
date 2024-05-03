package model;

public class NpcCharacterModel extends CharacterModel{
	
	public NpcCharacterModel(String name, String race, int healthPoints, int baseDmg, int baseArmour) {
		super(name, race, healthPoints, baseDmg, baseArmour);
		// TODO Auto-generated constructor stub
	}
	
	boolean isFriendly;
	boolean isTrader;
	boolean isQuestGiver;
	
	
	}
