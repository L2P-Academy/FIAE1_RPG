package model;

public class ItemModel {

	// Fragen? -> SAMAEL SCHRÖDER

	private String itemName;
	private int itemQuantity;
	private double itemPrice;
	private int itemDamage;
	private boolean isQuestItem;

	// Item-Liste:
	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87
	//
	// (enthält auch Infos zu consumable/usable + Description für Views)

	// TO-DO 10.05.
	//
	//

	// Constructors

	// Standard Constructor
	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, boolean isQuestItem) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemDamage = itemDamage;
		this.isQuestItem = isQuestItem;
	}

	// Constructor für Quest-Items
	public ItemModel(String itemName, int itemQuantity) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.isQuestItem = true;
	}

	// Constructor für Equipment
	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.isQuestItem = false;
		this.itemDamage = itemDamage;
	}

	// Getter & Setter

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemDamage() {
		return itemDamage;
	}

	public void setItemDamage(int itemDamage) {
		this.itemDamage = itemDamage;
	}

	public boolean getIsQuestItem() {
		return isQuestItem;
	}

	public void setIsQuestItem(boolean isQuestItem) {
		this.isQuestItem = isQuestItem;
	}

	//
	// --------------------------------------------------------------------------
	// Item-Liste
	//
	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87
	//
	// ItemModel itemName = new ItemModel "Bezeichnung",
	// itemQuantity,itemPrice,itemDamage,isQuestItem

	ItemModel Gold = new ItemModel("Gold", 1, 1, 0, false);

	ItemModel KleinerHeiltrank = new ItemModel("Kleiner Heiltrank", 1, 20, 0, false);

	ItemModel KleinerManatrank = new ItemModel("Kleiner Manatrank", 1, 20, 0, false);

	ItemModel KleinerStaminatrank = new ItemModel("Kleiner Staminatrank", 1, 20, 0, false);

	ItemModel Heiltrank = new ItemModel("Heiltrank", 1, 50, 0, false);

	ItemModel Manatrank = new ItemModel("Manatrank", 1, 50, 0, false);

	ItemModel Staminatrank = new ItemModel("Staminatrank", 1, 50, 0, false);

	ItemModel GrosserHeiltrank = new ItemModel("Großer Heiltrank", 1, 85, 0, false);

	ItemModel GrosserManatrank = new ItemModel("Großer Manatrank", 1, 85, 0, false);

	ItemModel GrosserStaminatrank = new ItemModel("Großer Staminatrank", 1, 85, 0, false);

	ItemModel Wiederbelebungstrank = new ItemModel("Wiederbelebungstrank", 1, 190, 0, false);

	ItemModel Brot = new ItemModel("Brot", 1, 5, 0, false);

	ItemModel Zimtschnecke = new ItemModel("Zimtschnecke", 1, 12, 0, false);

	ItemModel Weintrauben = new ItemModel("Weintrauben", 1, 10, 0, false);

	ItemModel Fisch = new ItemModel("Fisch", 1, 7, 0, false);

	ItemModel Kebab = new ItemModel("Kebab", 1, 25, 0, false);

	ItemModel Ei = new ItemModel("Ei", 1, 3, 0, false);

	ItemModel GoldEi = new ItemModel("Gold-Ei", 1, 200, 0, false);

	ItemModel HandVollWuermer = new ItemModel("Hand voll Würmer", 1, 1, 0, false);

	ItemModel EinStueckKuchen = new ItemModel("Ein Stück Kuchen", 1, 20, 0, false);

	ItemModel AnanasAusDerDose = new ItemModel("Ananas aus der Dose", 1, 4, 0, false);

	ItemModel Scheiblettenkaese = new ItemModel("Scheiblettenkäse", 1, 4, 0, false);

	ItemModel ScheibeFormschinken = new ItemModel("Scheibe Formschinken", 1, 4, 0, false);

	ItemModel ScheibeToast = new ItemModel("Scheibe Toast", 1, 4, 0, false);

	ItemModel Seil = new ItemModel("Seil", 1, 5, 0, false);

	ItemModel Stock = new ItemModel("Stock", 1, 2, 0, false);

	ItemModel HaeufchenAsche = new ItemModel("Häufchen Asche", 1, 1, 0, false);

	ItemModel Knoepfe = new ItemModel("Knöpfe", 1, 1, 0, false);

	ItemModel EtwasDreck = new ItemModel("Etwas Dreck", 1, 1, 0, false);

	ItemModel Knochen = new ItemModel("Knochen", 1, 3, 0, false);

	ItemModel Eingeweide = new ItemModel("Eingeweide", 1, 5, 0, false);

	ItemModel Monsterleber = new ItemModel("Monsterleber", 1, 5, 0, false);

	ItemModel Banditenkopf = new ItemModel("Banditenkopf", 1, 0, 0, true);

}
