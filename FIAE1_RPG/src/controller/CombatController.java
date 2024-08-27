package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.AbilityModel;
import model.NpcModel;
import model.PlayerCharacterModel;

public class CombatController {
	private PlayerCharacterModel character;
	private SoundController soundController;
	private SQLController sqlController;
	private NpcModel npcModel1, npcModel2, npcModel3, npcModel4;
	private List<NpcModel> allNpcsList, combatantsNpcList;
	private int currentRound, numberOfEnemies, randomEnemyNumber;

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

	public CombatController(PlayerCharacterModel playerCharacter, SoundController soundController) {
		this.character = playerCharacter;
		this.soundController = soundController;
		sqlController = new SQLController();
	}

	public List<NpcModel> combatInitialize() {
		numberOfEnemies = createRngOfEnemies();
		combatantsNpcList = setEnemiesForCombat(numberOfEnemies);
		currentRound = 1;
		while (isCombatRunning(combatantsNpcList)) {
			currentRound = startCombat(numberOfEnemies, currentRound);
			return combatantsNpcList;
		}
		return combatantsNpcList;
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

	public void processPlayerAction(String action, NpcModel target) {
		switch (action) {
		case "Angreifen": {
			playerAttack(target);
			break;
		}
		case "Flüchten": {
			playerEscape();
		}
		}
	}

	public void playerAttack(NpcModel npcModel) {
		int baseDmg = 20;
		npcModel.setHp(calculateDamage(npcModel.getHp(), baseDmg));
	}

	public void playerEscape() {

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
		return new Random().nextInt(3) + 1;
	}

	public int createRngForEnemySelection() {
		return new Random().nextInt(6) + 1;
	}

	public PlayerCharacterModel getCharacter() {
		return character;
	}

	public void setCharacter(PlayerCharacterModel character) {
		this.character = character;
	}
}