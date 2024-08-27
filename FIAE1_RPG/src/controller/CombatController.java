package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AbilityModel;
import model.NpcModel;
import model.PlayerCharacterModel;
import view.CombatView;

public class CombatController {
	private PlayerCharacterModel character;
	private SoundController soundController;
	private SQLController sqlController;
	private NpcModel selectedNpc;
	private List<NpcModel> allNpcsList, combatantsNpcList;
	private int currentRound, numberOfEnemies, randomEnemyNumber;
	private CombatView combatView;

	/*
	 * TODO (erledigt): NPCs (Mobs) -> zufällige Anzahl 1-4 bei Öffnen der View 1. Zufallszahl
	 * zwischen 1 und 4 festlegen für Anzahl der Gegner 2. Zufallszahl (derzeit!)
	 * zwischen 1-9 für zufällige Gegnerauswahl 3. -> SQL-Methode: getAllMobs() ->
	 * für alle NPCs mit Category="Mob" 4. Mobs in Arrayliste laden für spätere
	 * Zufallsauswahl 5. NPCModels je nach Anzahl erstellen -> Methode
	 * 
	 * 6. Methoden für den Kampf calculateRound calculateDamage -> HP reduzieren,
	 * prüfen ob HP=0 checkConditions (win (zurück zur Map -> XP, Gold, Items...
	 * hinzufügen) or lose(zurück zur Taverne -> Spieler wird geheilt) healCharacter
	 * (verwenden von Tränken, bei Besuch der Taverne, auf der Map...)
	 * getAllAbilities (Datenbank)
	 */

	public CombatController(PlayerCharacterModel playerCharacter, SoundController soundController, CombatView combatView) {
		this.character = playerCharacter;
		this.soundController = soundController;
		this.sqlController = new SQLController();
		this.combatView = combatView;
	}

	public List<NpcModel> combatInitialize() {
		numberOfEnemies = createRngOfEnemies();
		combatantsNpcList = setEnemiesForCombat(numberOfEnemies);
		currentRound = 1;
		selectNpc(combatantsNpcList.get(0));
		
		while (isCombatRunning(combatantsNpcList)) {
			currentRound = startCombat(numberOfEnemies, currentRound);
		}
		return combatantsNpcList;
	}
	
	private void selectNpc(NpcModel npcModel) {
		this.selectedNpc = npcModel;
	}

	public int startCombat(int numberOfEnemies, int currentRound) {
		int maxRounds = numberOfEnemies + 1;
		if (currentRound == 1) {
			currentRound++;
		} else if (currentRound == maxRounds) {
			currentRound = 1;
		} else {
			for (int i = 0; i < combatantsNpcList.size(); i++) {
				mobAttack(character);
				currentRound++;
			}

		}
		return currentRound;
	}

	public void processPlayerAction(String action) {
		switch (action) {
		case "Angreifen": {
			if (selectedNpc != null) {
				playerAttack(selectedNpc);
				combatView.updateNpcHealth(selectedNpc);
			}
			break;
		}
		case "Flüchten": {
			if (combatView != null) {
				soundController.stopMusicLoop();
				combatView.dispose();
			}
			break;
		}
		}
	}

	public void playerAttack(NpcModel npcModel) {
		int baseDmg = 20;
		npcModel.setHp(calculateDamage(npcModel.getHp(), baseDmg));
		soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
		combatView.updateNpcHealth(npcModel);
	}

	public void mobAttack(PlayerCharacterModel character) {
		int baseDmg = 5;
		character.setCurrentHP(calculateDamage(character.getCurrentHP(), baseDmg));
	}

	public int calculateDamage(int targetHP, int damageDealt) {
		return targetHP - damageDealt;
	}

	public List<NpcModel> setEnemiesForCombat(int numberOfEnemies) {
		allNpcsList = sqlController.getAllNpcs("MOB");

		combatantsNpcList = new ArrayList<>();

		for (int i = 0; i < numberOfEnemies; i++) {
			randomEnemyNumber = createRngForEnemySelection();
			NpcModel selectedNpc = allNpcsList.get(randomEnemyNumber);
			combatantsNpcList.add(selectedNpc);
		}
		return combatantsNpcList;
	}

	public boolean isCombatRunning(List<NpcModel> combatantsNpcList) {
		if (character.getCurrentHP() <= 0) {
			System.out.println(character.getName() + " hat kein kampffähiges Pokemon mehr, du fällst in Ohnmacht!");
			return false;
		}
		for (NpcModel npc : combatantsNpcList) { 
			if (npc.getHp() <= 0) {
				System.out.println("Alle Monster wurden besiegt!");
				return true;
			}
		}
		return false;
	}

	public int createRngOfEnemies() {
		return new Random().nextInt(4) + 1;
	}

	public int createRngForEnemySelection() {
		// TODO: adjust number of enemies based on database
		return new Random().nextInt(7);
	}

	public PlayerCharacterModel getCharacter() {
		return character;
	}

	public void setCharacter(PlayerCharacterModel character) {
		this.character = character;
	}
}