package model;

public class QuestModel {
	private int questID;
	private String name;
	private String description;
	private int reqLevel;
	private int rewardXP;
	private double rewardGold;
	private boolean isMainQuest;
	private int itemID;

	// Constructor
	public QuestModel(int questID, String name, String description, int reqLevel, int rewardXP, double rewardGold,
			boolean isMainQuest, int itemID) {
		super();
		this.questID = questID;
		this.name = name;
		this.description = description;
		this.reqLevel = reqLevel;
		this.rewardXP = rewardXP;
		this.rewardGold = rewardGold;
		this.isMainQuest = isMainQuest;
		this.itemID = itemID;
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

	public double getRewardGold() {
		return rewardGold;
	}

	public void setRewardGold(double rewardGold) {
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