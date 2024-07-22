package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLController {
	
	// Connection information
	private static final String URL = "jdbc:mysql://localhost:3306/rpg_db";
	private static final String USER = "root";
	private static final String PW = "";
	
	public SQLController() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PW)) {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");
			
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
				int level = resultSet.getInt("Level");
				int hp  = resultSet.getInt("HP");
				
				System.out.println("Charakterinformationen geladen");
				
				System.out.println("Spieler geladen!");
				System.out.println("Name: " + name);
				System.out.println("Rasse: " + raceID);
				System.out.println("Klasse: " + classID);
				System.out.println("Level: " + level);
				System.out.println("Lebenspunkte: " + hp);
			} else {
				System.out.println("Kein Charakter mit der angegebenen ID gefunden.");
			}
			
			resultSet.close();
			statement.close();
			
		} catch (Exception e) {
			System.out.println("Fehler beim Laden des Charakters!");
			e.printStackTrace();
		}
	}

}
