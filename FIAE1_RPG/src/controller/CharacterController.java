package controller;

import model.CharacterModel;	
import model.PlayerCharacterModel;

public class CharacterController {
	
	/* defines max Points, so HP/MP can't fall below 0
	private int maxHealthPoints; needed?
	private int maxManaPoints; needed? */
	
	// create a new player character, sets Level to 1 
	// Singleton-pattern required?
	
	public void createCharacter() {
		
		PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel(null, null, 0, 0, 0, 0, 0, 0);
		playerCharacterModel.set_level(1);
		playerCharacterModel.setHealthPoints(100); // not final
		playerCharacterModel.setBaseArmour(5); // not final
		playerCharacterModel.set_manaPoints(100); // not final
		playerCharacterModel.setBaseDmg(10); // not final
		playerCharacterModel.setAbilityList(null); // not final
		playerCharacterModel.setInventoryList(null); // weapons, armour? not final
	
	} 
	
	// Change HP; TO DO: >= 0 = Game Over
	
	public void updateHealthpoints(int updateHP) {	
		
		int hp = playerCharacterModel.get_healthPoints();
		playerCharacterModel.set_healthPoints(hp + updateHP);
		// How to solve this with getter/setter?
			
	}
	
	// Change Mana; TO DO: >= 0 = 0
	
	public void updateMana(int updateMana) {	
		
		int mp = playerCharacterModel.get_healthPoints();
		playerCharacterModel.set_healthPoints(mp + updateMana);
		// How to solve this with getter/setter?
		
	}
	

}
