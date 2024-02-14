package Player;

import Inventory.Inventory;
import Items.Item;
import Misc.Location;

/**
 * The Player class represents a player in the game.
 *
 *  ,  
 */
public class Player {

    /**
     * Name of the player
     */
    private String name;

    /**
     * Inventory of the player
     *
     *  
     */
    private Inventory inventory;

    /**
     * Current location of the player
     *
     *  
     */
    private Location currentLocation;

    /**
     * Current place of the player
     *
     *  
     */
    private Object currentPlace;

    /**
     * describe if player is movable or not
     *
     *  
     */
    private boolean movable;

    /**
     * Constructor of Player Class
     *
     * @param name         : represent name of the player
     * @param inventory    : represent inventory of the player
     * @param currentPlace : represent current location of the player
     * @param movable      : represent if the player is movable or not
     */
    public Player(String name,
                  Inventory inventory,
                  Object currentPlace,
                  boolean movable) {
        this.name = name;
        this.inventory = inventory;
        this.currentLocation = new Location();
        this.currentPlace = currentPlace;
        this.movable = movable;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     *  
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The new name of the player.
     *  
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added to the inventory.
     *  
     */
    public void addItemtoInventory(Item item) {
        inventory.addItem(item);
    }

    /**
     * Gets the inventory object
     *
     * @return Inventory
     *  
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * This method gets the current location of the player.
     *
     * @return Location
     *  
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * This method sets the current location of the player.
     *
     * @param currentLocation The new current location of the player.
     *  
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * This method gets the current place of the player.
     *
     * @return Location
     *  
     */
    public Object getCurrentPlace() { // We could use Place interface put all places there... Try it...

        return currentPlace;
    }

    /**
     * This method sets the current place of the player.
     *
     * @param currentPlace The new current place of the player.
     *  
     */
    public void setCurrentPlace(Object currentPlace) {
        this.currentPlace = currentPlace;
    }

    /**
     * simple getter for movable
     *
     * @return boolean movable
     *  
     */
    public boolean isMovable() {
        return movable;
    }


    /**
     * Sets whether the player is movable.
     *
     * @param movable True to make the player movable, false otherwise.
     *  
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

}
