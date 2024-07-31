package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.NpcModel;
import model.PlayerCharacterModel;
import model.NpcModel.NpcCategory;
import view.CombatView;

public class CombatController {
	private ArrayList<PlayerCharacterModel> combatants;
	private int currentTurnIndex;

	// Constructor
	public CombatController() {
		combatants = new ArrayList<>();
		currentTurnIndex = 0;
	}

	// Method to add a combatant to the encounter
	public void addCombatant(PlayerCharacterModel combatant) {
		combatants.add(combatant);
	}

	// Method to start the combat encounter
	public void startEncounter() {
		Collections.shuffle(combatants);
		currentTurnIndex = 0;
		for (int i = 0; i < combatants.size(); i++) {
		}
		takeTurn();
	}

	// Method to end the combat encounter
	public void endEncounter() {
		combatants.clear();
		currentTurnIndex = 0;
	}

	// Method to handle a combatant's turn
	private void takeTurn() {
		if (combatants.isEmpty()) {
			return;
		}
		currentTurnIndex++;
		if (currentTurnIndex >= combatants.size()) {
			currentTurnIndex = 0;
		}
		takeTurn();
	}
	
	// // // Ab hier hat Cedric rumgefuschelt
	private List<NpcModel> enemiesDatabase;
	
	// Constructor for randomBattle
	public CombatController(List<NpcModel> enemiesDatabase) {
		this.enemiesDatabase = enemiesDatabase;
	}
	
	// random Encounter method
	private void encounter(int battlemaps, int... npcID) {
		List<NpcModel> encounterEnemies = new ArrayList<>();
		currentTurnIndex = 0;
		
		// this is a schleife that checks for npcID and adds them to the fight
		for (int id : npcID) {
			for (NpcModel enemy : enemiesDatabase) {
				if (enemy.getNpcID() == id) {
					encounterEnemies.add(enemy);
				}
			}
		// at this point, combatView starts
		new CombatView(battlemaps);
		}
		
		// battle starts at this point
		startBattle(encounterEnemies);
	}
	
	private void startBattle(List<NpcModel> enemies) {
		// currently just placeholders
		System.out.println("A battle between the hero and the following monsters has begun:");
        System.out.println("Hero");
        for (NpcModel enemy : enemies) {
            System.out.println(enemies);
        }
        // Placeholder for the actual combat logic
        System.out.println("Combat is simulated here...");
        System.out.println("The battle has ended.");
	}
	
	private void addMonsters() {
		// this just adds the existing monster npcs to the combatDatabase, until we work with SQL
		 NpcModel wolf = new NpcModel(1, 1, 30, 10, 1, 5, null, "Wolf");
		 NpcModel slime = new NpcModel(2, 1, 60, 20, 2, 32, null, "Schleim");
		 
	}
}