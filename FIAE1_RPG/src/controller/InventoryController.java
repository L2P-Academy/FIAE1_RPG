package controller;
//import java.util.Collections;
//import java.util.Comparator;
//
//import model.ItemModel;
//import view.InventoryView;
//import java.util.ArrayList;
//
public class InventoryController {
//    private InventoryView view;
//    private ArrayList<ItemModel> inventory;
//    int goldBalance;
//    ItemModel selectedItem;
//
//    // create new inventory
//    public InventoryController() {
//        this.view = new InventoryView();
//        this.inventory = new ArrayList<ItemModel>(); 
//    }
//    
//    // function to show the inventory
//    public void showInventory() {
//    	view.setVisible(true);
//    }
//    
//    // function to hide inventory
//    public void hideInventory() {
//    	view.setVisible(false);
//    }
//
//    // function to add and remove items
//    public void addItem(ItemModel item) {
//        this.inventory.add(item);
//    }
//
//    public void removeItem(ItemModel item) {
//        this.inventory.remove(item);
//    }
//    
//    // function to add gold to value
//    public void addGold(int GoldValue) {
//        this.goldBalance += GoldValue;
//    }
//    
//    // function to remove Gold from Balance
//    public void reduceGold(int reduceValue) {
//    	this.goldBalance -= reduceValue;
//    }
//    
//    // function to equip a item
//    public void equipItem(String itemName) {
//        for (ItemModel item : inventory) {
//            if (item.getName().equals(itemName)) {
//                this.selectedItem = item; 
//                break;
//            }
//        }
//    }
//
//    // function to unequip a item
//    public void unequipItem(String itemName) {
//        if (this.selectedItem != null && this.selectedItem.getName().equals(itemName)) {
//            this.selectedItem = null; 
//        }
//    }
//
//    
//    // Sort method for name with compare the items
//    public void sortByName() {
//        Collections.sort(inventory, new Comparator<ItemModel>() {
//            public int compare(ItemModel item1, ItemModel item2) {
//                return item1.getName().compareTo(item2.getName());
//            }
//        });
//    }
//    
//    
//    public void sortByPrice() {
//        int n = inventory.size();
//        for (int i = 0; i < n-1; i++) {
//            for (int j = 0; j < n-i-1; j++) {
//                if (inventory.get(j).getValue() > inventory.get(j+1).getValue()) {
//                    ItemModel temp = inventory.get(j);
//                    inventory.set(j, inventory.get(j+1));
//                    inventory.set(j+1, temp);
//                }
//            }
//        }
//    }
//
}
//
//
//
