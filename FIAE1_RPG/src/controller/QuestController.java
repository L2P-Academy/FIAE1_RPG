package controller;

import java.util.ArrayList;
import java.util.List;
import model.PlayerCharacterModel;
import model.QuestModel;

public class QuestController {
	private List<QuestModel> questList;

	// Constructor
	public QuestController() {
		this.questList = new ArrayList<>();
	}

	// Method to add a quest to the quest list
	public void addQuest(QuestModel quest) {
		questList.add(quest);
	}

	// Method to remove a quest from the quest list
	public void removeQuest(QuestModel quest) {
		questList.remove(quest);
	}

	// Method to get a list of all available quests
	public List<QuestModel> getAllQuests() {
		return questList;
	}

	// Method to start quests for the player if level requirement is met
	public void startQuest(PlayerCharacterModel player, QuestModel quest) {
		if (player.getLevel() >= quest.getLevelRequired()) {
			player.getActiveQuests().add(quest);
			System.out.println("Quest '" + quest.getTitle() + "' wurde von '" + player.getName() + "' angenommen.");
		} else {
			System.out.println(player.getName() + " erfüllt nicht das erforderliche Level für dieses Quest.");
		}
	}

	// Method to retrieve completed quest for the player
	public void completeQuest(PlayerCharacterModel player, QuestModel quest) {
		if (player.getActiveQuests().contains(quest)) { // Check if the quest is in the player's active quest list
			player.getActiveQuests().remove(quest); // Remove the completed quest from the player's active quest list
			player.setExpPoints(player.getExpPoints() + quest.getRewardExpPoints() + quest.getRewardGold()); // Add any rewards for completing the quest (in this case experience points and gold)
			System.out.println("Quest '" + quest.getTitle() + "' wurde von '" + player.getName() + "' abgeschlossen.");
		} else {
			System.out.println("Quest '" + quest.getTitle() + "' ist für '" + player.getName() + "' nicht aktiv.");
		}
	}

	// Method to cancel the quest for the player
	public void cancelQuest(PlayerCharacterModel player, QuestModel quest) {
		if (player.getActiveQuests().contains(quest)) { // Check if the quest is in the player's active quest list
			player.getActiveQuests().remove(quest); // Remove the quest from the player's active quest list
			System.out.println("Quest '" + quest.getTitle() + "' wurde von '" + player.getName() + "' abgebrochen.");
		} else {
			System.out.println("Quest '" + quest.getTitle() + "' ist für '" + player.getName() + "' nicht aktiv.");
		}
	}

	// Method to retrieve active quests for the player
	public List<QuestModel> getActiveQuests(PlayerCharacterModel player) {
		List<QuestModel> activeQuests = new ArrayList<>();
		for (QuestModel quest : player.getActiveQuests()) {
			if (!quest.isCompleted()) {
				activeQuests.add(quest); // Add quest to the active quests list
			}
		}
		return activeQuests; // Return all active quests of the player
	}

	// Method to retrieve completed quests for the player
	public List<QuestModel> getCompletedQuests(PlayerCharacterModel player) {
		List<QuestModel> completedQuests = new ArrayList<>();
		for (QuestModel quest : player.getActiveQuests()) {
			if (quest.isCompleted()) {
				completedQuests.add(quest); // Add quest to the completed quests list
			}
		}
		return completedQuests; // Return all completed quests by the player
	}

	// Method to retrieve available quests
	public List<QuestModel> getAvailableQuests(PlayerCharacterModel player) {
		List<QuestModel> availableQuests = new ArrayList<>();
		List<QuestModel> allQuests = getAllQuests();
		for (QuestModel quest : allQuests) {
			if (quest.getLevelRequired() <= player.getLevel() && !quest.isMainQuest()) {
				availableQuests.add(quest); // Add quest to the available quests list
			}
		}
		return availableQuests; // Return all available quests
	}

	// Method to retrieve a quest by its ID
	public QuestModel getQuestById(int questId) {
		List<QuestModel> allQuests = getAllQuests();
		for (QuestModel quest : allQuests) {
			if (quest.getQuestID() == questId) {
				return quest; // Return the quest if found
			}
		}
		return null; // Return null if the quest with the specified ID is not found
	}

	// Method to check if a quest is completed
	public boolean isQuestCompleted(PlayerCharacterModel player, QuestModel quest) {
		List<QuestModel> completedQuests = player.getCompletedQuests(); // Get the player's completed quests
		for (QuestModel completedQuest : completedQuests) {
			if (completedQuest.getQuestID() == quest.getQuestID()) { // Check if the quest is in the list of completed quests
				return true; // Return true if the quest is completed
			}
		}
		return false; // Return false if the quest is not completed
	}

	// Method to check if a quest is active
	public boolean isQuestActive(PlayerCharacterModel player, QuestModel quest) {
		List<QuestModel> activeQuests = player.getActiveQuests(); // Get the player's active quests
		return activeQuests.contains(quest) && !quest.isCompleted(); // Check if the quest is in the list of active quests and not completed
	}

	// Method to update the progress of a quest for the player
	public void updateQuest(PlayerCharacterModel player, QuestModel quest, int questProgress) {
		if (player.getActiveQuests().contains(quest)) { // Check if the quest is in the player's active quest list
			int currentProgress = quest.getQuestProgress();
			int updatedProgress = currentProgress + questProgress; // Update the progress of the quest based on the specified progress
			int questTarget = quest.getQuestTarget();
			if (updatedProgress >= questTarget) { // Make sure progress does not exceed quest requirements
				quest.setCompleted(true); // Mark the quest as completed
				updatedProgress = questTarget;
				System.out.println("Quest '" + quest.getTitle() + "' wurde von '" + player.getName() + "' abgeschlossen.");
			} else {
				quest.setQuestProgress(updatedProgress); // Update the quest progress
			}
		} else {
			System.out.println("Quest '" + quest.getTitle() + "' ist für '" + player.getName() + "' nicht aktiv.");
		}
	}

	// Method to retrieve the reward associated with completing a quest
    public void getQuestReward(PlayerCharacterModel player, QuestModel quest) {
        if (!quest.isCompleted()) { // Check if the quest is completed
            System.out.println("Quest '" + quest.getTitle() + "' ist noch nicht abgeschlossen.");
            return;
        }

	// Apply quest rewards to the player
	player.setExpPoints(player.getExpPoints() + quest.getRewardExpPoints());
//	player.setGold(player.getGold() + quest.getRewardGold());
	System.out.println("Quest '" + quest.getTitle() + "' rewards applied to '" + player.getName() + "'.");
	}
}