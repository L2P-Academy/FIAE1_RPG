package controller;
import java.util.Collections;
import java.util.Comparator;

import model.ItemModel;
import view.InventoryView;
import java.util.ArrayList;

public class InventoryController {
    private InventoryView view;
    private ArrayList<ItemModel> inventory;
    int goldBalance;
    String selectedItem;

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
    
    // function to equip a item
    public void equipItem(String item) {
    	this.selectedItem = item;
    }
    
    // function to unequip a item
    public void unequipItem(String item) {
    	this.selectedItem = null;
    }
    
    // Sort method for name with compare the items
    public void sortByName() {
        Collections.sort(inventory, new Comparator<ItemModel>() {
            public int compare(ItemModel item1, ItemModel item2) {
                return item1.getItemName().compareTo(item2.getItemName());
            }
        });
    }
    
    
    // Sort method for price with compare method 
//    public void sortByPrice() {
//        Collections.sort(inventory, new Comparator<ItemModel>() {
//            public int compare(ItemModel item1, ItemModel item2) {
//                return Integer.compare(item1.getItemPrice(), item2.getItemPrice());
//            }
//        });
//    }
}



