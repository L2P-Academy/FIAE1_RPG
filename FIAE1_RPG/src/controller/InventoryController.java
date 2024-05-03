package controller;

import model.ItemModel;
import view.InventoryView;
import java.util.ArrayList;

public class InventoryController {
    private InventoryView view;
    private ArrayList<ItemModel> inventory;
    int goldBalance;
    String SelectedItem;

    // create new inventory
    public InventoryController() {
        this.view = new InventoryView();
        this.inventory = new ArrayList<ItemModel>(); 
    }
    
    // function to show the inventory
    public void showInventory() {
    	view.setVisible(true);
    }
    
    // function to hide inventory
    public void hideInventory() {
    	view.setVisible(false);
    }

    // function to add and remove items
    public void addItem(ItemModel item) {
        this.inventory.add(item);
    }

    public void removeItem(ItemModel item) {
        this.inventory.remove(item);
    }
    
    // function to add gold to value
    public void addGold(int GoldValue) {
        this.goldBalance += GoldValue;
    }
    
    // function to remove Gold from Balance
    public void reduceGold(int reduceValue) {
    	this.goldBalance -= reduceValue;
    }
    
}
