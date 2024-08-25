package model;

import java.util.ArrayList;
import java.util.List;

public class NpcModel {
	private int npcID, questID, hp, killXP, level, itemID;
	private String  name;
	private NpcCategory category;
	private static List<NpcModel> npcModels = new ArrayList<>(); // for combatView
	
	// Categories from Database: Trader, Mob, Companion, Innkeeper
	public enum NpcCategory {
		TRADER, MOB, COMPANION, INNKEEPER
	}
	
	public NpcModel(int npcID, int questID, int hp, int killXP, int level, int itemID, NpcCategory category, String name) {
		super();
		this.npcID = npcID;
		this.questID = questID;
		this.hp = hp;
		this.killXP = killXP;
		this.level = level;
		this.itemID = itemID;
		this.category = category;
		this.name = name;
		npcModels.add(this); // for combatView
		System.out.println(this + " added to " + npcModels); // for combatView
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
	public NpcCategory getCategory() {
		return category;
	}
	public void setCategory(NpcCategory category) {
		this.category = category;
	}
	
	// Combatview extras
	public static NpcModel getNpcModelByID(int npcID) {
		
		for (NpcModel npcModel : npcModels) {
			if (npcModel.getNpcID() == npcID) {
				return npcModel;
			}
		}
		return null;
	}
}