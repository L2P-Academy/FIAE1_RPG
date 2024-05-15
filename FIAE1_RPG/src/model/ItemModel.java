package model;

public class ItemModel {

	// Fragen? -> SAMAEL SCHRÖDER

	private String itemName;
	private int itemQuantity;
	private double itemPrice;
	private int itemDamage;
	private boolean isQuestItem;
	private String itemDescription;

	// Item-Liste:
	
	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87

	// (enthält auch Infos zu consumable/usable + Description für Views)

	// Constructors

	// 3 Stück weil EquipModel 2 braucht

	// Standard Constructor

	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, boolean isQuestItem,
			String itemDescription) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemDamage = itemDamage;
		this.isQuestItem = isQuestItem;
		this.itemDescription = itemDescription;
	}

	// Constructor für Quest-Items

	public ItemModel(String itemName, int itemQuantity) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.isQuestItem = true;
	}

	// Constructor für Equipment

	public ItemModel(String itemName, int itemQuantity, double itemPrice, int itemDamage, String itemDescription) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.isQuestItem = false;
		this.itemDamage = itemDamage;
		this.itemDescription = itemDescription;
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

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	// --------------------------------------------------------------------------

	// Item-Liste

	// https://more2learn-my.sharepoint.com/:x:/g/personal/ssch467_more2learn_de/EcYh5wDUl6xOkwiXIFZANVQB8GkH7YTvfICZ9erPfrlPkA?e=BddI87
	//
	// ItemModel itemName = new ItemModel
	// Bezeichnung, itemQuantity,itemPrice,itemDamage,isQuestItem, itemDescription

	// Basic

	ItemModel Gold = new ItemModel("Gold", 1, 1, 0, false, "Gold.");

	// Potions (consumable)

	ItemModel KleinerHeiltrank = new ItemModel("Kleiner Heiltrank", 1, 20, 0, false, "Füllt einige HP wieder auf.");

	ItemModel KleinerManatrank = new ItemModel("Kleiner Manatrank", 1, 20, 0, false, "Füllt ein paar Manapunkte auf.");

	ItemModel KleinerStaminatrank = new ItemModel("Kleiner Staminatrank", 1, 20, 0, false,
			"Füllt einige Stamina-Punkte auf.");

	ItemModel Heiltrank = new ItemModel("Heiltrank", 1, 50, 0, false, "Ein durchschnittlicher Heiltrank");

	ItemModel Manatrank = new ItemModel("Manatrank", 1, 50, 0, false, "Ein durchschnittlicher Manatrank");

	ItemModel Staminatrank = new ItemModel("Staminatrank", 1, 50, 0, false, "Ein durchschnittlicher Staminatrank");

	ItemModel GrosserHeiltrank = new ItemModel("Großer Heiltrank", 1, 85, 0, false, "Heilt dich schnell und gut");

	ItemModel GrosserManatrank = new ItemModel("Großer Manatrank", 1, 85, 0, false,
			"Gibt dir neue Energie zum Zaubern");

	ItemModel GrosserStaminatrank = new ItemModel("Großer Staminatrank", 1, 85, 0, false,
			"Schneller Rennen in wenigen Schlucken");

	ItemModel Wiederbelebungstrank = new ItemModel("Wiederbelebungstrank", 1, 190, 0, false,
			"Deine zweite Chance - nur einmal verwendbar");

	// Food (consumable)

	ItemModel Brot = new ItemModel("Brot", 1, 5, 0, false, "Ein frischer Laib Brot");

	ItemModel Zimtschnecke = new ItemModel("Zimtschnecke", 1, 12, 0, false, "Riecht nach viel Zimt");

	ItemModel Weintrauben = new ItemModel("Weintrauben", 1, 10, 0, false, "Zuckrig süße Trauben");

	ItemModel Fisch = new ItemModel("Fisch", 1, 7, 0, false, "Noch ist er frisch!");

	ItemModel Kebab = new ItemModel("Kebab", 1, 25, 0, false, "Mit allem ohne Kraut");

	ItemModel Ei = new ItemModel("Ei", 1, 3, 0, false, "Ein leckeres Ei");

	ItemModel GoldEi = new ItemModel("Gold-Ei", 1, 200, 0, false, "Legendäres Ei - auch lecker!");

	ItemModel HandVollWuermer = new ItemModel("Hand voll Würmer", 1, 1, 0, false, "Wenn sonst nichts da ist");

	ItemModel EinStueckKuchen = new ItemModel("Ein Stück Kuchen", 1, 20, 0, false, "Frisch gebacken und noch warm");

	ItemModel AnanasAusDerDose = new ItemModel("Ananas aus der Dose", 1, 4, 0, false,
			"Na na na na na naaa, das ist der Hawaiitoast");

	ItemModel Scheiblettenkaese = new ItemModel("Scheiblettenkäse", 1, 4, 0, false, "Toast Hawaii schmeckt allen gut");

	ItemModel ScheibeFormschinken = new ItemModel("Scheibe Formschinken", 1, 4, 0, false,
			"Spendet in der Not Trost, das ist der Hawaiitoast");

	ItemModel ScheibeToast = new ItemModel("Scheibe Toast", 1, 4, 0, false, "Wichtig ist das Weißbrot, ist doch klar!");

	// Special Items (consumable & usable)

	ItemModel Ueberraschungsei = new ItemModel("Überraschungsei", 1, 300, 0, false,
			"Enthält eines von Haralds hochwertigen Hühnchen");

	// Trash

	ItemModel EinGlasDreck = new ItemModel("Ein Glas Dreck", 1, 2, 0, false, "Wer würde Dreck in ein Glas … ach egal");

	ItemModel Seil = new ItemModel("Seil", 1, 5, 0, false, "Gut zum Klettern");

	ItemModel Stock = new ItemModel("Stock", 1, 2, 0, false, "Ein Stock.");

	ItemModel HaeufchenAsche = new ItemModel("Häufchen Asche", 1, 1, 0, false, "Einfach nur Asche");

	ItemModel Knoepfe = new ItemModel("Knöpfe", 1, 1, 0, false, "Eine Hand voll verschiedener Knöpfe");

	ItemModel EtwasDreck = new ItemModel("Etwas Dreck", 1, 1, 0, false, "Bisschen Dreck, zu absolut nichts nutze");

	ItemModel Knochen = new ItemModel("Knochen", 1, 3, 0, false, "Wem die wohl gehört haben mögen");

	ItemModel Eingeweide = new ItemModel("Eingeweide", 1, 5, 0, false, "Da ist noch ein Stück Milz in den Gedärmen");

	ItemModel Monsterleber = new ItemModel("Monsterleber", 1, 5, 0, false, "Nicht mehr ganz funktionstüchtiges Organ");

	// Pets (can be equipped?)

	ItemModel FlugunfaehigesHuhn = new ItemModel("Flugunfähiges Huhn (Pet)", 1, 200, 0, false,
			"Ist das ein Dodo verkleidet als Huhn?");

	ItemModel RotesHuhn = new ItemModel("Rotes Huhn (Pet)", 1, 200, 0, false, "Sehr rot, sehr laut");

	ItemModel GruenesHuhn = new ItemModel("Grünes Huhn (Pet)", 1, 200, 0, false, "Klein und grün");

	ItemModel GrosserHahn = new ItemModel("Großer Hahn (Pet)", 1, 500, 0, false, "Ziemlich großer Hahn");

	ItemModel GepanzertesKampfhuhn = new ItemModel("Gepanzertes Kampfhuhn (Pet)", 1, 3, 0, false,
			"Dieses Huhn sieht stark aus, wartet aber trotzdem lieber ab.");

	ItemModel GepanzertesFlughuhn = new ItemModel("Gepanzertes Flughuhn (Mount)", 1, 3, 0, false,
			"Theoretisch ein gutes Reittier, aber sehr klein.");

	// Quest items

	ItemModel MassiverHolzhahn = new ItemModel("Massiver Holzhahn", 1, 0, 0, true,
			"Erstaunlich großer Hahn aus Holz, der wohl Harald gehört");

	ItemModel Banditenkopf = new ItemModel("Banditenkopf", 1, 0, 0, true, "Der war mal an jemandem dran");

}
