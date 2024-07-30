package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;

import model.PlayerCharacterModel;

public class SQLController {

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
	/**
	 * Getting Character Information within all Fields and Values from the Database. Using PreparedStatement and Resultset Class.
	 * @param characterID ID from the Database
	 * @return PlayerCaracterModel
	 */
	public PlayerCharacterModel getCharacterInformation(int characterID) {
		PlayerCharacterModel character = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			// SQL-Statement Object
			//Statement statement = connection.createStatement();

			// query Character information from database -> Load Game
			// TODO: add dynamic character selection!
			String query = "SELECT * FROM playercharacter WHERE CharacterID = " + characterID;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, characterID);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				String name = resultSet.getString("Name");
				int raceID = resultSet.getInt("RaceID");
				int classID = resultSet.getInt("ClassID");
				int currentHP = resultSet.getInt("currentHP");
				int maxHP = resultSet.getInt("MaxHP");
				int currentMana = resultSet.getInt("CurrentMana");
				int maxMana = resultSet.getInt("MaxMana");
				int currentXP = resultSet.getInt("CurrentXP");
				int maxXP = resultSet.getInt("MaxXP");
				int level = resultSet.getInt("Level");
				
				character = new PlayerCharacterModel(characterID, raceID, classID, currentHP, maxHP, currentMana, maxMana, currentXP, maxXP, level, name);
						
				System.out.println("Charakterinformationen geladen");
			}

			resultSet.close();
			//statement.close();

		} catch (Exception e) {
			System.out.println("Fehler beim Laden des Charakters!");
			e.printStackTrace();
		}
		return character;
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
	 * Dynamic Method to insert any Data into any Table from the Database. Using Stringbuilder Class to build the query String
	 * and PreparedStatement Class to execute the query.  
	 * @param tableName Name of the target Table 
	 * @param columnValueMap Map of fieldnames and values
	 */
	public void insertIntoTable(String tableName, Map<String, String> columnValueMap) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			
			StringBuilder columns = new StringBuilder();
			StringBuilder wildcards = new StringBuilder();
			// building columns String from all column values and wildcards String ? for every singel value
			// for loop ends with the last key, in this case the last column name. So ever column name will added
			// to the columns String + "," . Also the wildcards String gets "?," for each loop pass
			for (String column: columnValueMap.keySet()) {
				columns.append(column).append(",");
				wildcards.append("?,");		
			}
			// cut of the last char from the strings, because it is a ","
			columns.setLength(columns.length()-1);
			wildcards.setLength(wildcards.length()-1);
			//building the SQL query
			String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + wildcards + ")";
			// try to initialize the PreparedStatement with the query
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
				
				//Change Wildcards "?" with the exact column values from the table 
				int index = 1;
				for (String value : columnValueMap.values()) {
					preparedStatement.setString(index++, value);
				}
				
				preparedStatement.executeUpdate();
				System.out.println("Daten wurden in die Tabelle " + tableName + " hinzugefügt!");
			} 
		} 
		catch (Exception e) {
			System.out.println("Fehler bei der Datenübertragung in die Tabelle " + tableName + " !");
			e.printStackTrace();
		}
	
	}
	/**
	 * Method to delete 1 Data set from the playercharacter table. Using PreparedStatement Class.
	 * @param characterID 
	 */
	public void deleteCharacter(int characterID) {
		
		try(Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			
			if(characterID != 0){
				String query = "DELETE FROM playercharacter WHERE playercharacter.CharacterID = " + characterID;
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.executeUpdate();
				System.out.println("Charakter gelöscht!");
			} 
			else {
				System.out.println("Ungültige CharacterID.");
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to check if a table has at least one Data Set
	 * @param tableName
	 * @return boolean 
	 */
	public boolean doesDataExist(String tableName) {
		
		boolean dataExist = false;
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			
			String query = "SELECT COUNT(*) FROM " + tableName;
			PreparedStatement preparedStatment = connection.prepareStatement(query);
			ResultSet result = preparedStatment.executeQuery();
			int Quantity = result.getInt(0);
			if(Quantity > 0) {
				dataExist = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataExist;
	}
	
	public Object[][] getEquipFromInventory(){
		
		List<Object[]> resultList = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			
		
//			String query = "SELECT inventory.InventoryID, inventory.Quantity, inventory.IsEquiped"
//					+ ", item.Name, item.Slot, item.Damage, item.Defense "
//					+ "FROM inventory, item "
//					+ "WHERE item.ItemID = inventory.ItemID" ;
			
			String query = "SELECT i.InventoryID, i.ItemID, i.Quantity, it.Name, it.Slot, it.Damage, it.Defense, it.ReqLevel"
					+ " FROM inventory i"
					+ " JOIN item it ON i.ItemID = it.ItemID"
					+ "	WHERE it.Slot != 'None' ORDER BY it.Name ASC";
					
					
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String name = resultSet.getString("Name");
				String slot = resultSet.getString("Slot");				
				int inventoryID = resultSet.getInt("InventoryID");
				int quantity = resultSet.getInt("Quantity");
				int damage = resultSet.getInt("Damage");
				int defense = resultSet.getInt("Defense");
				int reqLevel = resultSet.getInt("ReqLevel");
				
				Object[] row = new Object[7];
				row[0] = name;
				row[1] = slot;
				row[2] = damage;
				row[3] = defense;
				row[4] = reqLevel;
				row[5] = inventoryID;
				row[6] = quantity;
				
				resultList.add(row);
				
			}
			
			
			
		} 
			catch (Exception e) {
			e.printStackTrace();
		}
		return convertListToObjectArray(resultList);
	}
	
	public Object[][] convertListToObjectArray(List<Object[]> list){
		
		Object[][] dataArray = new Object[list.size()][7];
		
		for(int i = 0; i < list.size(); i++ ) {
			
			dataArray[i] = list.get(i);
		}
		
		return dataArray;
	}
}
