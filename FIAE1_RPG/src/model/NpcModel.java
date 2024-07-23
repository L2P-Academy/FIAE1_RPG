package model;


public class NpcModel{

	private int npcID;
	private int QuestID;
	private int HP;
	private int attackDamage;
	private int killXP;
	private int Level;
	private int itemID;
	private String Name;
	private String Category;
	
	//constructor
	
	public NpcModel(int npcID, int QuestID, int HP, int attackDamage, int killXP, int Level, int itemID, String Name, String Category) {
		super ();
		this.npcID = npcID;
		this.QuestID = QuestID;
		this.HP = HP;
		this.attackDamage = attackDamage;
		this.killXP = killXP;
		this.Level = Level;
		this.itemID = itemID;
		this.Name = Name;
		this.Category = Category;
		
	}

	public int getNpcID() {
		return npcID;
	}

	public void setNpcID(int npcID) {
		this.npcID = npcID;
	}

	public int getQuestID() {
		return QuestID;
	}

	public void setQuestID(int questID) {
		QuestID = questID;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}
	
	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getKillXP() {
		return killXP;
	}

	public void setKillXP(int killXP) {
		this.killXP = killXP;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

		
	}
	