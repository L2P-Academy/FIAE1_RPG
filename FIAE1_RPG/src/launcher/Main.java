package launcher;

import java.util.List;

import controller.CharacterController;
import controller.SQLController;
import controller.SoundController;
import model.PlayerCharacterModel;

public class Main {
	
	public static List<PlayerCharacterModel> characterModels;
	public CharacterController characterController;
	public static SoundController globalSoundController;

	public static void main(String[] args) {
		
		// Database Connection
		SQLController sqlController = new SQLController();
		// global SoundController
		globalSoundController = new SoundController();
		
		// Program initialize, show Start Menu
		characterModels = sqlController.getAllCharacters();
		
		if (characterModels.isEmpty()) {
			// create character stub
			PlayerCharacterModel character = new PlayerCharacterModel(1, "StubCharacter",
					3, 2, "m", 1, 0, 100, 1, 100, 100, 100, 100);			
			CharacterController characterController = new CharacterController(character, globalSoundController);
			characterController.initialize();
		} else {
			PlayerCharacterModel character = characterModels.get(0);
			CharacterController characterController = new CharacterController(character, globalSoundController);
			characterController.initialize();
		}


	}

}
