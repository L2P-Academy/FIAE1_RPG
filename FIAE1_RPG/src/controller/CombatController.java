package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

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
	private int numberOfEnemies, randomEnemyNumber;
	private CombatView combatView;
	private boolean isPlayerFinished;

	public CombatController(PlayerCharacterModel playerCharacter, SoundController soundController, CombatView combatView) {
		this.character = playerCharacter;
		this.soundController = soundController;
		this.sqlController = new SQLController();
		this.combatView = combatView;
		numberOfEnemies = createRngOfEnemies();
		combatantsNpcList = setEnemiesForCombat(numberOfEnemies);
		selectNpc(combatantsNpcList.get(0));
		isPlayerFinished = false;
	}
	
	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}

	public void setNumberOfEnemies(int numberOfEnemies) {
		this.numberOfEnemies = numberOfEnemies;
	}

	public List<NpcModel> getCombatantsNpcList() {
		return combatantsNpcList;
	}

	public void setCombatantsNpcList(List<NpcModel> combatantsNpcList) {
		this.combatantsNpcList = combatantsNpcList;
	}

	public void selectNpc(NpcModel npcModel) {
		this.selectedNpc = npcModel;
	}
	
    public void startCombatLoop() {
        while (isCombatRunning(combatantsNpcList)) {
            if (!isPlayerFinished) {
                combatView.attackBtn.setEnabled(true);
                combatView.abilityBtn.setEnabled(true);
                combatView.inventoryBtn.setEnabled(true);
                combatView.escapeBtn.setEnabled(true);
                combatView.setTextToCombatLog("Du bist dran! Warte auf Aktion...");
                break;
            } else {
            	combatView.setTextToCombatLog("Gegner greifen an!");
                for (NpcModel npc : combatantsNpcList) {
                	if (npc.getHp() > 0) {
                		mobAttack(npc, character);
                		combatView.updatePlayerHealth(character);
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
				}                
                isPlayerFinished = false;
            }
        }
    }
    
    public void logEnemyAttack(NpcModel npc, int damage) {
        String attackMessage = npc.getName() + " greift an und verursacht " + damage + " Schaden!";
        combatView.setTextToCombatLog(attackMessage);
    }

	public boolean isPlayerFinished() {
		return isPlayerFinished;
	}

	public void setPlayerFinished(boolean isPlayerFinished) {
		this.isPlayerFinished = isPlayerFinished;
	}

	public void processPlayerAction(String action) {
		switch (action) {
		case "Angreifen": {
			if (selectedNpc != null) {
				playerAttack(selectedNpc);
				combatView.updateNpcHealth(selectedNpc);
				isPlayerFinished = true;
				SwingUtilities.invokeLater(() -> startCombatLoop());				
			}
			break;
		}
		case "Flüchten": {
			if (combatView != null) {
				soundController.stopMusicLoop();
				soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
				combatView.dispose();
			}
			break;
		}
		}
	}

	public void playerAttack(NpcModel npcModel) {
		int baseDmg = 20;
		SwingUtilities.invokeLater(() -> soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav"));
		npcModel.setHp(calculateDamage(npcModel.getHp(), baseDmg));
		combatView.setTextToCombatLog("Du greifst " + npcModel.getName() + " an und verursachst " + baseDmg + " Schaden");
		combatView.updateNpcHealth(npcModel);
	}

	public void mobAttack(NpcModel npcModel, PlayerCharacterModel character) {
		if (npcModel.getHp() > 0) { 
			SwingUtilities.invokeLater(() -> soundController.playFxSound("res/soundFX/fxEffects/swoosh_sound.wav"));
			character.setCurrentHP(calculateDamage(character.getCurrentHP(), 2));
			logEnemyAttack(npcModel, npcModel.getDamage());
		}
	}

	public int calculateDamage(int targetHP, int damageDealt) {
		return targetHP - damageDealt;
	}

	public List<NpcModel> setEnemiesForCombat(int numberOfEnemies) {
		allNpcsList = sqlController.getAllNpcs("MOB");
		combatantsNpcList = new ArrayList<>();
		
		while (combatantsNpcList.size() < numberOfEnemies ) {
			randomEnemyNumber = createRngForEnemySelection();
			NpcModel selectedNpc = allNpcsList.get(randomEnemyNumber);
			// select only distinct NPCs, TODO: quick and dirty - Cedrics Job to be fixed :>
			if (!combatantsNpcList.contains(selectedNpc)) {
				combatantsNpcList.add(selectedNpc);
			}
		}
		return combatantsNpcList;
	}

	public boolean isCombatRunning(List<NpcModel> combatantsNpcList) {
		boolean allEnemiesDefeated = true;
		if (character.getCurrentHP() <= 0) {
			System.out.println(character.getName() + " wurde besiegt! " + character.getName() + " fällt in Ohnmacht!");
			return false;
		}
		for (NpcModel npc : combatantsNpcList) { 
			if (npc.getHp() > 0) {
				allEnemiesDefeated = false;
			}
		}
		if (allEnemiesDefeated) {
			System.out.println("Alle Monster wurden besiegt!");
		}
		return !allEnemiesDefeated;
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