package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.CharacterModel_OLD;

public class CombatController {
	private ArrayList<CharacterModel_OLD> combatants;
	private int currentTurnIndex;

	// Constructor
	public CombatController() {
		combatants = new ArrayList<>();
		currentTurnIndex = 0;
	}

	// Method to add a combatant to the encounter
	public void addCombatant(CharacterModel_OLD combatant) {
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
}