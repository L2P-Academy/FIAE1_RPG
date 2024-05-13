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
	public QuestModel(String title, String description, int questID, boolean isMainQuest, double rewardGold,
			int rewardExpPoints, ItemModel questItemRequired, int levelRequired, NpcCharacterModel questNPC,
			int questProgress, int questTarget) {
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
	
	// Example 
	// QuestModel test = new QuestModel(title, description, questID, isMainQuest, rewardGold, rewardExpPoints, questItemRequired, levelRequired, questNPC, questProgress, questTarget);
	
	// Example objects to test
	QuestModel TheDragonCave = new QuestModel("Die Drachenhöhle", "Ein Drache terrorisiert die Landschaft...", 1, true, 250, 500, getQuestItemRequired(), 10, getQuestNPC(), 5, 1);
	
	QuestModel TheCursedAmulet = new QuestModel("Der Fluch des Knochenamuletts", "Ein verfluchtes Amulett wurde aus einem heiligen Tempel\r\n"
			+ "gestohlen und bringt...", 2, true, 500, 15, getQuestItemRequired(), 15, getQuestNPC(), 5, 2);
	
	QuestModel TheCursedGrave = new QuestModel("Der Fluch des Vergessenen Grabmals", "Dorfbewohner verschwinden, und Gerüchte über ein\r\n"
			+ "verfluchtes Grabmal in den nahegelegenen Bergen machen die Runde...", 3, false, 70, 0, getQuestItemRequired(), 0, getQuestNPC(), 0, 3);
	
	QuestModel TheGhostForest = new QuestModel("Der Geisterwald", "Erkunde die Geheimnisse des Geisterwaldes... ", 4, false, 50, 0, getQuestItemRequired(), 0, getQuestNPC(), 0, 4);
	
	QuestModel TheLostExpedition = new QuestModel("Die Verlorene Expedition", "Eine Expedition in unerforschtes Gebiet ist verschwunden...", 5, false, 55, 0, getQuestItemRequired(), 0, getQuestNPC(), 0, 5);
	
	QuestModel TheHerbalist = new QuestModel("Die Bitte des Kräutersammlers", "Ein örtlicher Kräutersammler sucht seltene Kräuter, die tief\r\n"
			+ "im Wald zu finden sind...", 6, false, 55, 0, getQuestItemRequired(), 0, getQuestNPC(), 0, 6);
	
	QuestModel TheTrollBridge = new QuestModel("Die Troll-Brücken-Maut", "Ein Troll hat sich unter einer Brücke niedergelassen und verlangt\r\n"
			+ "Maut von Reisenden...", 7, false, 45, 0, getQuestItemRequired(), 3, getQuestNPC(), 0, 7);
	
	QuestModel TheBandits = new QuestModel("Das Versteck der Banditen", "Eine Gruppe von Banditen hat in einer nahegelegenen Höhle ein\r\n"
			+ "Lager aufgeschlagen und terrorisiert Reisende...", 8, false, 250, 500, getQuestItemRequired(), 10, getQuestNPC(), 1, 8);
	
	QuestModel TheLostBlacksmith = new QuestModel("Der verschwundene Schmiedelehrling", "Der Lehrling des Dorfschmieds ist verschwunden...", 9, false, 60, 100, getQuestItemRequired(), 2, getQuestNPC(), 1, 9);
	
	QuestModel TheGhostGraveyard = new QuestModel("Der Geisterfriedhof", "Ein Friedhof am Stadtrand ist von ruhelosen Skeletten befallen...", 10, false, 75, 100, getQuestItemRequired(), 3, getQuestNPC(), 1, 10);
	
	QuestModel HaraldsRoster = new QuestModel("Haralds Hahn", "Kikeriki! Oh warte mal, hast du zufällig ein grosses, praechtiges Huhn\r\n"
			+ "gesehen...", 11, false, 75, 100, getQuestItemRequired(), 2, getQuestNPC(), 1, 11);
	
	// Getter & Setter
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