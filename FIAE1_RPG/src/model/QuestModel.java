package model;

public class QuestModel {
	private int questID;
	private int reqLevel;
	private int rewardXP;
	private int itemID;
	private int rewardGold;
	private boolean isMainQuest;
	private String name;
	private String description;

	// Constructor
	public QuestModel(int questID, int reqLevel, int rewardXP, int itemID, int rewardGold, boolean isMainQuest,
			String name, String description) {
		super();
		this.questID = questID;
		this.reqLevel = reqLevel;
		this.rewardXP = rewardXP;
		this.itemID = itemID;
		this.rewardGold = rewardGold;
		this.isMainQuest = isMainQuest;
		this.name = name;
		this.description = description;
	}

	// Getter & Setter
	public int getQuestID() {
		return questID;
	}	

	public void setQuestID(int questID) {
		this.questID = questID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReqLevel() {
		return reqLevel;
	}

	public void setReqLevel(int reqLevel) {
		this.reqLevel = reqLevel;
	}

	public int getRewardXP() {
		return rewardXP;
	}

	public void setRewardXP(int rewardXP) {
		this.rewardXP = rewardXP;
	}

	public int getRewardGold() {
		return rewardGold;
	}

	public void setRewardGold(int rewardGold) {
		this.rewardGold = rewardGold;
	}

	public boolean isMainQuest() {
		return isMainQuest;
	}

	public void setMainQuest(boolean isMainQuest) {
		this.isMainQuest = isMainQuest;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
}