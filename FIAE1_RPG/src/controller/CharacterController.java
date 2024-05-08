package controller;

import model.CharacterModel; // this is shown as unused?	
import model.PlayerCharacterModel;

public class CharacterController {
	
	PlayerCharacterModel playerCharacterModel;
	
	/* this must be added to CharacterModel
	private int maxHealthPoints; needed?
	private int maxManaPoints; needed? */
	
	
	// creates a new player character, sets Level to 1 (values can be altered)
	public void createCharacter() {
		
		playerCharacterModel.setName(null); // should be chosen in View !!!
		playerCharacterModel.setRace(null); // should be chosen in View !!!
		
		playerCharacterModel = new PlayerCharacterModel(null, null, 0, 0, 0, 0, 0, 0);
		playerCharacterModel.set_level(1); 
		playerCharacterModel.setHealthPoints(100);
		playerCharacterModel.setBaseArmour(5);
		playerCharacterModel.set_manaPoints(100); 
		playerCharacterModel.setBaseDmg(10);
		playerCharacterModel.setAbilityList(null);
		playerCharacterModel.setInventoryList(null);
		// playerCharacterModel.set_staminaPoints(10); // not used, but exists in models
		
		// !!! implement switch case for races (alterations for HP, MP, Dmg, Ability) here !!!
	
	} 
	
	// Change health points
	public void updateHealthpoints(int updateHP) {	
				
		 int hp = playerCharacterModel.getHealthPoints();
		 
		 playerCharacterModel.setHealthPoints(hp + updateHP); 
		 if (hp + updateHP <= 0) {
			 playerCharacterModel.setHealthPoints(0); // !!! GAME OVER SCREEN HERE?
			} else {
			playerCharacterModel.setHealthPoints(hp + updateHP);
			}	
	}
	
	// Change mana points, if <= 0 mana points are set to 0
	public void updateMana(int updateMP) {	
		
		int mp = playerCharacterModel.get_manaPoints();
		
		if (mp + updateMP <= 0) {
			playerCharacterModel.set_manaPoints(0);
		} else {
			playerCharacterModel.set_manaPoints(mp + updateMP);
		}
	}
	
	// add experience points
	//public void addXP (XP) {
		
	//}
	

}
