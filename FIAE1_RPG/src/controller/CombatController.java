package controller;

import java.util.ArrayList;

import model.CharacterModel;

public class CombatController {
	private ArrayList<CharacterModel> combatants;
	private int currentTurnIndex;

	// Constructor
	public CombatController() {
		combatants = new ArrayList<>();
		currentTurnIndex = 0;
	}

	// Method to add a combatant to the encounter
	public void addCombatant(CharacterModel combatant) {
		combatants.add(combatant);
	}

	// Method to start the combat encounter
	public void startEncounter() {
		// TODO: under construction...
	}

	// Method to end the combat encounter
	public void endEncounter() {
		// TODO: somethings missing...
	}

	// Method to handle a combatant's turn
	private void takeTurn() {
		// TODO: will be back soon...
	}
}