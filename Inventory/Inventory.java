package Inventory;

import Game.Printer;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The Inventory class represents a container for managing items.
 *
 *  ,  
 */
public class Inventory {

    /**
     * Represent list of items.
     *
     *  
     */
    private ArrayList<Item> items;

    /**
     * Constructs a new Inventory object.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * This method adds the item to the inventory if it does not already exist.
     *
     * @param item The item will be added.
     * @return If the item is added return true, otherwise false.
     *  
     */
    public boolean addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            return true;
        } else {
            System.out.println("Item already exist in your inventory....");
            return false;
        }
    }

    /**
     * This method removes the item from the inventory.
     *
     * @param item The item will be removed.
     *  
     */
    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            System.out.println("Chosen item is not in your inventory... ");
        }
    }

    /**
     * This method gets a list of all items in the inventory.
     *
     * @return The list of items in the inventory.
     *  
     */
    public List<Item> getItemsInInventory() {
        return this.items;
    }

    /**
     * This method prints the inventory
     *
     *  
     */
    public void printInventory() {
        if (!items.isEmpty()) {
            String s = "";
            for (Item item : items) {
                s += item.getItemName() + " : " + item.readDescription() + "\n";
            }
            Printer.printMessage(s);
        } else {
            Printer.printMessage("Empty Inventory");
        }
    }

}
