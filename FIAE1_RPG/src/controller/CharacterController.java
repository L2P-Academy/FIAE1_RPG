package controller;

import model.PlayerCharacterModel;
import view.MainMenuView;

public class CharacterController {
	
	private PlayerCharacterModel character;
	private SoundController soundController;
	
	public CharacterController(PlayerCharacterModel playerCharacter, SoundController soundController) {
		// initializes and loads characterInformation for all classes
		this.character = playerCharacter;
		this.soundController = soundController;
	}

	public PlayerCharacterModel getCharacter() {
		return character;
	}

	public void setCharacter(PlayerCharacterModel character) {
		this.character = character;
	}
	
	public void initialize() {
		new MainMenuView(this, soundController);
		
	}
}
