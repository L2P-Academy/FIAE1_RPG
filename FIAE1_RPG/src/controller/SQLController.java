package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String query = "SELECT * FROM playercharacter WHERE CharacterID = 1";
			ResultSet resultSet = statement.executeQuery(query);

			if (resultSet.next()) {
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
			} else {
				System.out.println("Kein Charakter mit der angegebenen ID gefunden.");
			}

			resultSet.close();
			statement.close();

		} catch (Exception e) {
			System.out.println("Fehler beim Laden des Charakters!");
			e.printStackTrace();
		}
		return character;
	}
}
