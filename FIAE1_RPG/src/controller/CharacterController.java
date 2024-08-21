package controller;

import model.PlayerCharacterModel;
import view.MainMenuView;

public class CharacterController {
	
	private PlayerCharacterModel character;
	
	public CharacterController(PlayerCharacterModel playerCharacter) {
		// initializes and loads characterInformation for all classes
		this.character = playerCharacter;
	}

	public PlayerCharacterModel getCharacter() {
		return character;
	}

	public void setCharacter(PlayerCharacterModel character) {
		this.character = character;
	}
	
	public void initialize() {
		new MainMenuView(this);
		
	}
}
