package model;

public class QuestModel {
	private String title;
	private String description;
    private int questID;
    private boolean isMainQuest;
    private boolean isCompleted;
    private double rewardGold;
    private int rewardExpPoints;
    private ItemModel questItemRequired;
    private int levelRequired;
    private NpcCharacterModel questNPC;
    private int questProgress;
    private int questTarget;

    // Constructor
    public QuestModel( String title, String description, int questID, boolean isMainQuest, double rewardGold, int rewardExpPoints, ItemModel questItemRequired, int levelRequired, NpcCharacterModel questNPC, int questProgress, int questTarget) {
        this.title = title;
        this.description = description;
        this.questID = questID;
        this.isMainQuest = isMainQuest;
        this.rewardGold = rewardGold;
        this.rewardExpPoints = rewardExpPoints;
        this.questItemRequired = questItemRequired;
        this.levelRequired = levelRequired;
        this.questNPC = questNPC;
        this.isCompleted = false; // The quest is by default not complete.
        this.questProgress = questProgress;
        this.questTarget = questTarget;
    }

    // Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuestID() {
		return questID;
	}

	public void setQuestID(int questID) {
		this.questID = questID;
	}

	public boolean isMainQuest() {
		return isMainQuest;
	}

	public void setMainQuest(boolean isMainQuest) {
		this.isMainQuest = isMainQuest;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public double getRewardGold() {
		return rewardGold;
	}

	public void setRewardGold(double rewardGold) {
		this.rewardGold = rewardGold;
	}

	public int getRewardExpPoints() {
		return rewardExpPoints;
	}

	public void setRewardExpPoints(int rewardExpPoints) {
		this.rewardExpPoints = rewardExpPoints;
	}

	public ItemModel getQuestItemRequired() {
		return questItemRequired;
	}

	public void setQuestItemRequired(ItemModel questItemRequired) {
		this.questItemRequired = questItemRequired;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public void setLevelRequired(int levelRequired) {
		this.levelRequired = levelRequired;
	}

	public NpcCharacterModel getQuestNPC() {
		return questNPC;
	}

	public void setQuestNPC(NpcCharacterModel questNPC) {
		this.questNPC = questNPC;
	}

	public int getQuestProgress() {
		return questProgress;
	}
	
	public void setQuestProgress(int questProgress) {
		this.questProgress = questProgress;
	}

	public int getQuestTarget() {
		return questTarget;
	}
	
	public void setQuestTarget(int questTarget) {
		this.questTarget = questTarget;
	}
}