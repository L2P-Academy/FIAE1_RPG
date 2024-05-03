package controller;

import model.ItemModel;
import view.InventoryView;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InventoryController {
    private InventoryView view;
    private ArrayList<ItemModel> inventory; 

    // create new inventory and keylistener i and escape 
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
}
