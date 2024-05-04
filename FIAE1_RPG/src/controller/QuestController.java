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
    	if(player.getLevel() >= quest.getLevelRequired()) {
    		player.getActiveQuest().add(quest);
    		System.out.println("Quest '" + quest.getName() + "' started for '" + player.getName() + "'.");
    	} else {
    		System.out.println(player.getName() + " does not meet the level requirement to start the quest.");
    	}
    }

    // Method to retrieve completed quests for the player
    public void completeQuest(PlayerCharacterModel player, QuestModel quest) {
        
        if (player.getActiveQuests().contains(quest)) { // Check if the quest is in the player's active quest list
            player.getActiveQuests().remove(quest); // Remove the completed quest from the player's active quest list
            player.setExpPoints(player.getExpPoints() + quest.getRewardExpPoints()); // Add any rewards for completing the quest (e.g., experience points, gold, etc...)
            System.out.println("Quest '" + quest.getName() + "' completed for '" + player.getName() + "'.");
        } else {
            System.out.println("Quest '" + quest.getName() + "' is not active for '" + player.getName() + "'.");
        }
    }
    
    // Method to cancel the quest for the player
    public void cancelQuest(PlayerCharacterModel player, QuestModel quest) {
    	
    }

    // Method to retrieve active quests for the player
    public List<QuestModel> getActiveQuests(PlayerCharacterModel player) {
        List<QuestModel> activeQuests = new ArrayList<>();
        return activeQuests;
    }

    // Method to retrieve completed quests for the player
    public List<QuestModel> getCompleteQuests(PlayerCharacterModel player) {
        List<QuestModel> completeQuests = new ArrayList<>();
        return completeQuests;
    }

    // Method to retrieve available quests
    public List<QuestModel> getAvailableQuests() {
        List<QuestModel> availableQuests = new ArrayList<>();
        return availableQuests;
    }

    // Method to retrieve a quest by its ID
    public QuestModel getQuestId(int questId) {
        return null;
    }

    // Method to check if a quest is completed
    public boolean isQuestCompleted(QuestModel quest) {
        return false;
    }

    // Method to check if a quest is active
    public boolean isQuestActive(QuestModel quest) {
        return false;
    }

    // Method to update the progress of a quest for the player
    public void updateQuest(PlayerCharacterModel player, QuestModel quest, int progress) {
    }

    // Method to retrieve the reward associated with completing a quest
    public void getQuestReward(QuestModel quest) {
    }
}