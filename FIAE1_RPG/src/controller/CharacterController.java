/*
 * What happens after a lvl up:
 * - add: change BaseArmour
 * - add: change BaseDmg
 * - add: change max HP/MP
 * 
 * XP:
 * - add: XP conditions for lvl up
 * - add: % of actual lvl progress
 * 
 * ???:
 * Wie werden Verbesserungen durch Ausrüsten technisch umgesetzt?
 */

package controller;

import model.CharacterModel;
import model.PlayerCharacterModel;

public class CharacterController {
	
	// creates a new player character
	PlayerCharacterModel playerCharacterModel;
	
	public void createCharacter() {
		
		playerCharacterModel = new PlayerCharacterModel(null, null, 0, 0, 0, 0, 0, 0);
		playerCharacterModel.setLevel(1); 
		playerCharacterModel.setHealthPoints(100);
		playerCharacterModel.setCurrentHealthPoints(100);
		playerCharacterModel.setBaseArmour(5);
		playerCharacterModel.setManaPoints(100);
		playerCharacterModel.setCurrentManaPoints(100);
		playerCharacterModel.setBaseDmg(10);
		playerCharacterModel.setAbilityList(null);
		playerCharacterModel.setInventoryList(null);
	
	} 
	
	// adds +1 to character level
	public void levelupCharacter() {
		if (playerCharacterModel.getLevel() >= 100) // Max. LVL = 100; can be changed
			playerCharacterModel.setLevel(100);
		else {
			playerCharacterModel.setLevel(playerCharacterModel.getLevel() + 1);
		}
	}
	
	// Update Healthpoints; GP can't go below 0 or exceed the max HP
	// TO DO: What happens if current HP are 0 or below? Game over screen?
	public void updateHealthpoints(int updateHP) {	
		if (playerCharacterModel.getCurrentHealthPoints() + updateHP <= 0) {
			playerCharacterModel.setCurrentHealthPoints(0);
		} else if (playerCharacterModel.getCurrentHealthPoints() + updateHP > playerCharacterModel.getHealthPoints()) {
			playerCharacterModel.setHealthPoints(playerCharacterModel.getHealthPoints());
		} else {
			int hp = playerCharacterModel.getHealthPoints();
			playerCharacterModel.setHealthPoints(hp + updateHP);	
		}
	}
	
	// Update ManaPoints; MP can't go below 0 or exceed the max MP
	// TO DO ERROR: The method setExpPoints(int) in the type PlayerCharacterModel is not applicable for the arguments (double)	QuestController.java	/FIAE1_RPG/src/controller	line 43	Java Problem
	public void updateMana(int updateMP) {	
		if (playerCharacterModel.getCurrentManaPoints() + updateMP <= 0) {
			playerCharacterModel.setCurrentManaPoints(0);
		} else if (playerCharacterModel.getCurrentHealthPoints() + updateMP > playerCharacterModel.getManaPoints()) {
			playerCharacterModel.setManaPoints(playerCharacterModel.getManaPoints());
		} else {
			int mp = playerCharacterModel.getManaPoints();
			playerCharacterModel.setManaPoints(mp + updateMP);	
		}
	}
	
	// Add experience points
	public void addXpPoints(int addedXP) {
		playerCharacterModel.setExpPoints(playerCharacterModel.getExpPoints() + addedXP);
	}
	

}
