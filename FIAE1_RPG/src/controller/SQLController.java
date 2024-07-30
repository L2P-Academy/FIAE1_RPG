package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



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

	public PlayerCharacterModel getCharacterInformation(int characterID) {
		PlayerCharacterModel character = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {

			// SQL-Statement Object
			Statement statement = connection.createStatement();

			// query Character information from database -> Load Game
			// TODO: add dynamic character selection!
			String query = "SELECT * FROM playercharacter";
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
			statement.close();

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

	public void insertIntoTable(String tableName, Map<String, String> columnValueMap) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
		
			StringBuilder columns = new StringBuilder();
			StringBuilder wildcards = new StringBuilder();
			
			for (String column: columnValueMap.keySet()) {
				columns.append(column).append(",");
				wildcards.append("?,");		
			}
			
			columns.setLength(columns.length()-1);
			wildcards.setLength(wildcards.length()-1);
			
			String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + wildcards + ")";
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
				
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
}
