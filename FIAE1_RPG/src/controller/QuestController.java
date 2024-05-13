package controller;

import java.util.ArrayList;

import model.ItemModel;
import model.PlayerCharacterModel;
import model.QuestModel;

public class QuestController {
	private ArrayList<QuestModel> questList;

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
	public ArrayList<QuestModel> getAllQuests() {
		return questList;
	}

	// Method to start quests for the player if level requirement is met
	public void startQuest(PlayerCharacterModel player, QuestModel quest) {
		if (player.getLevel() >= quest.getLevelRequired()) {
			player.getActiveQuests().add(quest);
		}
	}

	// Method to retrieve completed quest for the player
	public void completeQuest(PlayerCharacterModel player, QuestModel quest, ItemModel gold) {
		if (player.getActiveQuests().contains(quest)) {
			player.getActiveQuests().remove(quest);
			player.setExpPoints(player.getExpPoints() + quest.getRewardExpPoints());
			gold.setItemQuantity((int) (gold.getItemQuantity() + quest.getRewardGold()));
		}
	}

	// Method to cancel the quest for the player
	public void cancelQuest(PlayerCharacterModel player, QuestModel quest) {
		if (player.getActiveQuests().contains(quest)) {
			player.getActiveQuests().remove(quest);
		}
	}

	// Method to retrieve active quests for the player
	public ArrayList<QuestModel> getActiveQuests(PlayerCharacterModel player) {
		ArrayList<QuestModel> activeQuests = new ArrayList<>();
		for (QuestModel quest : player.getActiveQuests()) {
			if (!quest.isCompleted()) {
				activeQuests.add(quest);
			}
		}
		return activeQuests;
	}

	// Method to retrieve completed quests for the player
	public ArrayList<QuestModel> getCompletedQuests(PlayerCharacterModel player) {
		ArrayList<QuestModel> completedQuests = new ArrayList<>();
		for (QuestModel quest : player.getActiveQuests()) {
			if (quest.isCompleted()) {
				completedQuests.add(quest);
			}
		}
		return completedQuests;
	}

	// Method to retrieve available quests
	public ArrayList<QuestModel> getAvailableQuests(PlayerCharacterModel player) {
		ArrayList<QuestModel> availableQuests = new ArrayList<>();
		ArrayList<QuestModel> allQuests = getAllQuests();
		for (QuestModel quest : allQuests) {
			if (quest.getLevelRequired() <= player.getLevel() && !quest.isMainQuest()) {
				availableQuests.add(quest);
			}
		}
		return availableQuests;
	}

	// Method to retrieve a quest by its ID
	public QuestModel getQuestById(int questId) {
		ArrayList<QuestModel> allQuests = getAllQuests();
		for (QuestModel quest : allQuests) {
			if (quest.getQuestID() == questId) {
				return quest;
			}
		}
		return null;
	}

	// Method to check if a quest is completed
	public boolean isQuestCompleted(PlayerCharacterModel player, QuestModel quest) {
		ArrayList<QuestModel> completedQuests = player.getCompletedQuests();
		for (QuestModel completedQuest : completedQuests) {
			if (completedQuest.getQuestID() == quest.getQuestID()) {

				return true;
			}
		}
		return false;
	}

	// Method to check if a quest is active
	public boolean isQuestActive(PlayerCharacterModel player, QuestModel quest) {
		ArrayList<QuestModel> activeQuests = player.getActiveQuests();
		return activeQuests.contains(quest) && !quest.isCompleted();
	}

	// Method to update the progress of a quest for the player
	public void updateQuest(PlayerCharacterModel player, QuestModel quest, int questProgress) {
		if (player.getActiveQuests().contains(quest)) {
			int currentProgress = quest.getQuestProgress();
			int updatedProgress = currentProgress + questProgress;

			int questTarget = quest.getQuestTarget();
			if (updatedProgress >= questTarget) {
				quest.setCompleted(true);
				updatedProgress = questTarget;
			} else {
				quest.setQuestProgress(updatedProgress);
			}
		}
	}

	// Method to retrieve the reward associated with completing a quest
	public void getQuestReward(PlayerCharacterModel player, QuestModel quest, ItemModel gold) {
	    if (!quest.isCompleted()) {
	        return;
	    }
	    player.setExpPoints(player.getExpPoints() + quest.getRewardExpPoints());
	        gold.setItemQuantity((int) (gold.getItemQuantity() + quest.getRewardGold()));
	}
}