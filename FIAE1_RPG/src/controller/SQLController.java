package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;

import model.NpcModel;
import model.NpcModel.NpcCategory;
import model.PlayerCharacterModel;
import model.QuestModel;

public class SQLController {
	
	// TODO für Chris: 
	// Methoden für Tabellen: Ability, Boss, -> *NPC* <-
 

	// Connection information
	private static final String URL = "jdbc:mysql://localhost:3306/rpg_db";
	private static final String USER = "root";
	private static final String PW = "";

	public SQLController() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");
		} catch (Exception e) {
			System.out.println("Fehler beim Laden des Datenbanktreibers");
			e.printStackTrace();
		}
	}

	public List<PlayerCharacterModel> getAllCharacters() {
		List<PlayerCharacterModel> characters = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery("SELECT * FROM playercharacter");

			while (resultSet.next()) {
				PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel(resultSet.getInt("CharacterID"),
						resultSet.getString("Name"), resultSet.getInt("RaceID"), resultSet.getInt("ClassID"),
						resultSet.getString("Gender"), resultSet.getInt("CurrentLocation"),
						resultSet.getInt("CurrentXP"), resultSet.getInt("MaxXP"), resultSet.getInt("Level"),
						resultSet.getInt("CurrentHP"), resultSet.getInt("MaxHP"), resultSet.getInt("CurrentMana"),
						resultSet.getInt("MaxMana"));
				characters.add(playerCharacterModel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return characters;
	}
	/**
	 * Getting all Npc from a specific Category
	 * @param String npcCategory
	 * @return List<NpcModel>
	 */
	public List<NpcModel> getAllNpcs(String npcCategory) {
		List<NpcModel> npcModels = new ArrayList<NpcModel>();
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			String query = "SELECT * FROM npc WHERE Category = \"" + npcCategory +"\"";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				NpcModel model = new NpcModel(resultSet.getInt("NpcID"),
						resultSet.getInt("QuestID"), resultSet.getInt("HP"), resultSet.getInt("Damage"),
						resultSet.getInt("KillXP"), resultSet.getInt("Level"), resultSet.getInt("ItemID"), 
						resultSet.getInt("LocationID"), NpcCategory.valueOf(resultSet.getString("Category")),
						resultSet.getString("Name"));
				npcModels.add(model);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return npcModels;
	}

	/**
	 * Getting Character Information within all Fields and Values from the Database.
	 * Using PreparedStatement and Resultset Class.
	 * 
	 * @param Integer characterID ID from the Database
	 * @return PlayerCaracterModel
	 */
	public PlayerCharacterModel getCharacterInformation(int characterID) {
		PlayerCharacterModel character = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			// SQL-Statement Object
			// Statement statement = connection.createStatement();

			// query Character information from database -> Load Game
			// TODO: add dynamic character selection!
			String query = "SELECT * FROM playercharacter WHERE CharacterID = " + characterID;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, characterID);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("Name");
				int raceID = resultSet.getInt("RaceID");
				int classID = resultSet.getInt("ClassID");
				String gender = resultSet.getString("Gender");
				int currentLocation = resultSet.getInt("CurrentLocation");
				int currentHP = resultSet.getInt("currentHP");
				int maxHP = resultSet.getInt("MaxHP");
				int currentMana = resultSet.getInt("CurrentMana");
				int maxMana = resultSet.getInt("MaxMana");
				int currentXP = resultSet.getInt("CurrentXP");
				int maxXP = resultSet.getInt("MaxXP");
				int level = resultSet.getInt("Level");

				character = new PlayerCharacterModel(characterID, name, raceID, classID, gender, currentLocation,
						currentXP, maxXP, level, currentHP, maxHP, currentMana, maxMana);

				System.out.println("Charakterinformationen geladen");
			}

			resultSet.close();
			// statement.close();

		} catch (Exception e) {
			System.out.println("Fehler beim Laden des Charakters!");
			e.printStackTrace();
		}
		return character;
	}

	/**
	 * Delete specific Dataset from Table
	 * 
	 * @param Integer datasetKey primary key
	 * @param String  tableName
	 * @param String  iDName
	 */
	public void deleteDatasetFromTable(int datasetKey, String tableName, String iDName) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "DELETE FROM " + tableName + " WHERE " + iDName + " = " + datasetKey;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void setCharacterInformation(PlayerCharacterModel character) {
//		
//		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
//			
//
//			String query = "INSERT INTO playercharacter (CharacterID, Name, RaceID, ClassID, CurrentXP, MaxXP, Level, CurrentHP, MaxHP, CurrentMana, MaxMana)"
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//				
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, character.getCharacterID());
//			preparedStatement.setString(2, character.getName());
//			preparedStatement.executeUpdate(query);
//			System.out.println("Charakterinformationen wurden gespeichert");
//		}
//		catch (Exception e) {
//			System.out.println("Charakterinformation nicht gespeichert.");// TODO: handle exception
//		}
//		
//	}

	/**
	 * Dynamic Method to insert any Data into any Table from the Database. Using
	 * Stringbuilder Class to build the query String and PreparedStatement Class to
	 * execute the query.
	 * 
	 * @param String tableName
	 * @param <k>    column
	 * @param <v>    value
	 */
	public void insertIntoTable(String tableName, Map<String, String> columnValueMap) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			StringBuilder columns = new StringBuilder();
			StringBuilder wildcards = new StringBuilder();
			// building columns String from all column values and wildcards String ? for
			// every singel value
			// for loop ends with the last key, in this case the last column name. So ever
			// column name will added
			// to the columns String + "," . Also the wildcards String gets "?," for each
			// loop pass
			for (String column : columnValueMap.keySet()) {
				columns.append(column).append(",");
				wildcards.append("?,");
			}
			// cut of the last char from the strings, because it is a ","
			columns.setLength(columns.length() - 1);
			wildcards.setLength(wildcards.length() - 1);
			// building the SQL query
			String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + wildcards + ")";
			// try to initialize the PreparedStatement with the query
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				// Change Wildcards "?" with the exact column values from the table
				int index = 1;
				for (String value : columnValueMap.values()) {
					preparedStatement.setString(index++, value);
				}

				preparedStatement.executeUpdate();
				System.out.println(
						"Daten wurden in die Tabelle " + tableName + " hinzugefügt! (SQLController.java: 195)");
			}
		} catch (Exception e) {
			System.out.println("Fehler bei der Datenübertragung in die Tabelle " + tableName + " !");
			e.printStackTrace();
		}

	}

	/**
	 * Method to delete 1 Data set from the playercharacter table. Using
	 * PreparedStatement Class.
	 * 
	 * @param Integer characterID
	 */
	public void deleteCharacter(int characterID) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			if (characterID != 0) {
				String query = "DELETE FROM playercharacter WHERE playercharacter.CharacterID = " + characterID;
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.executeUpdate();
				System.out.println("Charakter gelöscht!");
			} else {
				System.out.println("Ungültige CharacterID.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to check if a table has at least one Data Set
	 * 
	 * @param String tableName
	 * @return boolean
	 */
	public boolean doesDataExistInTable(String tableName) {
		 
		boolean dataExist = false;
 
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
 
			String query = "SELECT COUNT(*) FROM " + tableName;
			PreparedStatement preparedStatment = connection.prepareStatement(query);
			ResultSet result = preparedStatment.executeQuery();
			if(result.next()) {
				int Quantity = result.getInt(1);
				if (Quantity > 0) {
					dataExist = true;
				}	
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return dataExist;
	}

	/**
	 * Select all Informations from the DB for the Equipment Table in CharacterView
	 * 
	 * @return Object[][]
	 */
	public Object[][] getEquipFromInventory() {

		int numberOfFields = 7;
		List<Object[]> resultList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

//			String query = "SELECT inventory.InventoryID, inventory.Quantity, inventory.IsEquiped"
//					+ ", item.Name, item.Slot, item.Damage, item.Defense "
//					+ "FROM inventory, item "
//					+ "WHERE item.ItemID = inventory.ItemID" ;

			String query = "SELECT i.InventoryID, i.ItemID, i.Quantity, it.Name, it.Slot, it.Damage, it.Defense, it.ReqLevel"
					+ " FROM inventory i" + " JOIN item it ON i.ItemID = it.ItemID"
					+ "	WHERE it.Slot != 'None' ORDER BY it.Slot ASC";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("Name");
				String slot = resultSet.getString("Slot");
				int inventoryID = resultSet.getInt("InventoryID");
				int quantity = resultSet.getInt("Quantity");
				int damage = resultSet.getInt("Damage");
				int defense = resultSet.getInt("Defense");
				int reqLevel = resultSet.getInt("ReqLevel");

				Object[] row = new Object[numberOfFields];
				row[0] = name;
				row[1] = slot;
				row[2] = damage;
				row[3] = defense;
				row[4] = reqLevel;
				row[5] = inventoryID;
				row[6] = quantity;

				resultList.add(row);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertListToObjectArray(resultList, numberOfFields);
	}

	/**
	 * Specific Method for getting all Items from the Inventory within the
	 * fields(DB) InventoryID, Quantity, Value and Name. Used by the InventoryView
	 * 
	 * @return Object[][]
	 */
	public Object[][] getItemsFromInventory() {

		int numberOfFields = 5;
		List<Object[]> resultList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "SELECT i.InventoryID, i.Quantity, it.Name, it.Value " + "From inventory i "
					+ "JOIN item it ON i.ItemID = it.ItemID " + "ORDER BY i.InventoryID ASC";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			// Loop to turn all Datasets from the Result List to the Object List
			while (resultSet.next()) {
				int inventoryID = resultSet.getInt("InventoryID");
				int quantity = resultSet.getInt("Quantity");
				int value = resultSet.getInt("Value");
				int totalValue = quantity * value;
				String name = resultSet.getString("Name");

				Object[] row = new Object[numberOfFields];
				row[0] = inventoryID;
				row[1] = name;
				row[2] = quantity;
				row[3] = value;
				row[4] = totalValue;

				resultList.add(row);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertListToObjectArray(resultList, numberOfFields);

	}

	/**
	 * Change IsEquiped field in the Database to 1
	 * 
	 * @param String itemName
	 */

	public void itemEquipped(String itemName) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "UPDATE inventory i " + "JOIN item it ON i.ItemID = it.ItemID " + "SET i.IsEquiped = 1 "
					+ "WHERE it.Name = \"" + itemName + "\"";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Change IsEquiped field in the Database to 0
	 * 
	 * @param String itemName
	 */
	public void itemUnEquipped(String itemName) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "UPDATE inventory i " + "JOIN item it ON i.ItemID = it.ItemID " + "SET i.IsEquiped = 0 "
					+ "WHERE it.Name = \"" + itemName + "\"";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void useItem(String itemName) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "UPDATE inventory i " + "JOIN item it ON it.ItemID = i.ItemID"
					+ "SET i.Quantity = i.Quantity-1 " + "WHERE it.Name = \"" + itemName + "\"";
			PreparedStatement preparedStatemant = connection.prepareStatement(query);
			preparedStatemant.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the Description from the Item
	 * 
	 * @param itemName
	 * @return
	 */
	public String getItemDescription(String itemName) {
		String description = null;

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			String query = "SELECT Description FROM item WHERE Name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, itemName);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				description = resultSet.getString("Description");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return description;
	}

	/**
	 * Getting active Quest within all Fields. Used by QuestView and JournalView
	 * 
	 * @return QuestModel
	 */
	public QuestModel getActiveQuest() {
		
		QuestModel activeQuest;

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "SELECT * FROM quest q JOIN journal j ON q.QuestID = j.QuestID WHERE j.IsActive = 1";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int questID = resultSet.getInt("QuestID");
				int reqLevel = resultSet.getInt("ReqLevel");
				int rewardXP = resultSet.getInt("RewardXP");
				int rewardGold = resultSet.getInt("RewardGold");
				int item = resultSet.getInt("ItemID");
				String name = resultSet.getString("Name");
				String description = resultSet.getString("Description");

				activeQuest = new QuestModel(questID, reqLevel, rewardXP, item, rewardGold, name, description);
				return activeQuest;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QuestModel acceptQuest(int questID) {

		QuestModel activeQuest = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "SELECT * FROM quest WHERE QuestID = " + questID;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int ID = resultSet.getInt("QuestID");
				int reqLevel = resultSet.getInt("ReqLevel");
				int rewardXP = resultSet.getInt("RewardXP");
				int rewardGold = resultSet.getInt("RewardGold");
				int item = resultSet.getInt("ItemID");
				String name = resultSet.getString("Name");
				String description = resultSet.getString("Description");

				activeQuest = new QuestModel(ID, reqLevel, rewardXP, item, rewardGold, name, description);
				query = "INSERT INTO journal (JournalID, IsCompleted, IsActive, QuestID) VALUES (" + questID
						+ ", 0, 1, " + questID + ");";
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				preparedStatement2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return activeQuest;
	}

	/**
	 * Method for turnin and accept the following Quest
	 * 
	 * @param Integer questID
	 */
	public void changeNewQuest(int questID) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "UPDATE journal " + "SET isActive = 0, IsCompleted = 1 " + "WHERE QuestID = " + questID;

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

			if (questID > 12) {

				questID++;
				query = "UPDATE journal " + "SET isActive = 1 " + "WHERE QuestID = " + questID;

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get NPC within all Fields.
	 * 
	 * @param Integer NpcID
	 * @return NpcModel
	 */
//	public NpcModel getNpcModelFromID(int NpcID) {
//
//		NpcModel npc = null;
//
//		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
//
//			String query = "SELECT * FROM npc WHERE NpcID = " + NpcID;
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//
//				int iD = resultSet.getInt("NpcID");
//				int questID = resultSet.getInt("QuestID");
//				int hP = resultSet.getInt("HP");
//				int killXP = resultSet.getInt("KillXP");
//				int level = resultSet.getInt("Level");
//				int itemID = resultSet.getInt("ItemID");
//				int locationID = resultSet.getInt("LocationID");
//				String name = resultSet.getString("Name");
//				String category = resultSet.getString("Category");
//				// TODO Warum braucht man hier ein ENUM?
//				npc = new NpcModel(NpcID, questID, hP, killXP, level, itemID, null, name);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return npc;
//	}

	/**
	 * Convert ItemID to specific Item Name. For every View with Quests
	 * 
	 * @param Integer itemID
	 * @return String itemName
	 */

	public String getItemNameFromItemID(int itemID) {

		String itemName = null;

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "SELECT Item.Name FROM item WHERE ItemID = " + itemID;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				itemName = resultSet.getString("Name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemName;
	}

	/**
	 * Update one specific value in a table
	 * 
	 * @param Integer datasetKey primary key
	 * @param String  tableName
	 * @param String  fieldName
	 * @param String  value
	 * @param String  iDName
	 */
	public void updateSpecificValue(int datasetKey, String tableName, String fieldName, String value, String iDName) {

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			String query = "UPDATE " + tableName + " SET " + fieldName + " = " + value + " WHERE " + tableName + "."
					+ iDName + " = " + datasetKey;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] convertListToObjectArray(List<Object[]> list, int fields) {

		Object[][] dataArray = new Object[list.size()][fields];

		for (int i = 0; i < list.size(); i++) {

			dataArray[i] = list.get(i);
		}

		return dataArray;
	}

	/**
	 * Count all existent datasets from a table
	 * 
	 * @param tableName
	 * @return int
	 */
	public int getNumberOfDatasets(String tableName) {

		int anzahl = 0;

		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			String query = "SELECT COUNT(*) FROM " + tableName;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				anzahl = resultSet.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return anzahl;
	}

	/**
	 * convert RaceID to the actually Race name
	 * 
	 * @param Integer raceID
	 * @return String race Name
	 */
	public String convertRaceIDToString(int raceID) {
		String race = "";

		switch (raceID) {

		case 1:
			race = "Mensch";
			break;

		case 2:
			race = "Elf";
			break;

		case 3:
			race = "Zwerg";
			break;

		case 4:
			race = "Halbling";
			break;

		case 5:
			race = "Ork";
			break;

		case 6:
			race = "Goblin";
			break;

		}
		return race;
	}

	/**
	 * convert ClassID to the actually Class name
	 * 
	 * @param Integer ClassID
	 * @return String Class name
	 */

	public String convertClassIDToString(int ClassID) {
		String playerClass = "";

		switch (ClassID) {

		case 1:
			playerClass = "Krieger";
			break;

		case 2:
			playerClass = "Schurke";
			break;

		case 3:
			playerClass = "Magier";
			break;

		case 4:
			playerClass = "Elementarist";
			break;

		case 5:
			playerClass = "Waldläufer";
			break;

		}
		return playerClass;
	}
}
