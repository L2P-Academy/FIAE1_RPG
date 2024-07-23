package model;


public class NpcCharacterModel extends CharacterModel_OLD{

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



	NpcCharacterModel GuterGoblin = new NpcCharacterModel("Guter Goblin", "Goblin", 40, 15, 8, true, true, true);
	NpcCharacterModel SchattigeSandra = new NpcCharacterModel("Schattige Sandra", "Elf", 50, 20, 10, true, true, false);
	NpcCharacterModel GrantigerGünter = new NpcCharacterModel("Grantiger Günter", "Zwerg", 60, 25, 12, true, false, true);
	NpcCharacterModel NeugierigeNadine = new NpcCharacterModel("Neugierige Nadine", "Mensch", 70, 30, 15, true, false, false);
	NpcCharacterModel GeheimnisvollerMax = new NpcCharacterModel("Geheimnisvoller Max", "Ork", 80, 35, 18, false, true, true);
	NpcCharacterModel VertrauenswürdigeTina = new NpcCharacterModel("Vertrauenswürdige Tina", "Troll", 90, 40, 20, false, true, false);
	NpcCharacterModel ListigerLukas = new NpcCharacterModel("Listiger Lukas", "Gnom", 100, 45, 22, false, false, true);
	NpcCharacterModel BöserBernd = new NpcCharacterModel("Böser Bernd", "Fee", 110, 50, 25, false, false, false);

}
