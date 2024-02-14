package Items;

import Interfaces.Describable;
import Misc.Location;

/**
 * The Item class represents an item in the game.
 * This class implements the interface called Describable
 *
 *  
 */
public class Item implements Describable {

    /**
     * Name of the item
     */
    private String itemName;

    /**
     * Description of the item
     *
     *  
     */
    private String description;

    /**
     * Location of the item
     *
     *  
     */
    private Location itemLocation;

    /**
     * Constructs a new Item object.
     *
     * @param itemName     The name of the item.
     * @param description  The description of the item.
     * @param itemLocation The location of the item.
     */
    public Item(String itemName, String description, Location itemLocation) {
        this.itemName = itemName;
        this.description = description;
        this.itemLocation = itemLocation;
    }

    /**
     * This method gets the name of the item.
     *
     * @return The name of the item.
     *  
     */
    public String getItemName() {
        return itemName;
    }


    /**
     * This method is used to get Item Location.
     *
     * @return The location of the item.
     *  
     */
    public Location getItemLocation() {
        return itemLocation;
    }

    /**
     * This method gives description of the item.
     *
     * @return The description of the item.
     *  
     */
    @Override
    public String readDescription() {
        return this.description;
    }

}
