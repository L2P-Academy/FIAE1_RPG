package model;
public class NpcModel {
	private int npcID, questID, hp, killXP, level, itemID;
	private String  name, category;
	
	// Categories from Database: Trader, Mob, Companion, Innkeeper
	
	public NpcModel(int npcID, int questID, int hp, int killXP, int level, int itemID, String category, String name) {
		super();
		this.npcID = npcID;
		this.questID = questID;
		this.hp = hp;
		this.killXP = killXP;
		this.level = level;
		this.itemID = itemID;
		this.category = category;
		this.name = name;
	}
	
	//Getter and Setter
	public int getNpcID() {
		return npcID;
	}
	public void setNpcID(int npcID) {
		this.npcID = npcID;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getKillXP() {
		return killXP;
	}
	public void setKillXP(int killXP) {
		this.killXP = killXP;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}