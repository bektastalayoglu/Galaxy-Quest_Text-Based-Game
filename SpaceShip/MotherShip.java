package SpaceShip;

import Items.Item;

import java.util.Map;
import java.util.Objects;

/**
 * The MotherShip class represents a subclass of SpaceShip class
 *
 *  
 */
public class MotherShip extends SpaceShip {

    /**
     * A mapping of missing items needed to fix the mother ship.
     * The map contains a list of items and a boolean that indicates if they are missing.
     *
     *  
     */
    private Map<Item, Boolean> missingItems;

    /**
     * Describe if the mothership is usable or not
     *
     *  
     */
    private boolean usable;

    /**
     * Constructs a new MotherShip object
     *
     * @param name         The name of the mothership.
     * @param missingItems A mapping of missing items needed to fix the mother ship.
     * @param usable       It describes whether the mother ship is usable.
     */
    public MotherShip(String name, Map<Item, Boolean> missingItems, boolean usable) {
        super(name);
        this.missingItems = missingItems;
        this.usable = usable;
    }

    /**
     * Method which fits a ship part and fixes the mothership.
     *
     * @param item Item to be fitted
     *  
     */
    public void fitPart(Item item) {
        // Looping all the missing parts and comparing with the argument item
        for (Map.Entry<Item, Boolean> entry : missingItems.entrySet()) {

            Item itemToDel = entry.getKey();

            if (Objects.equals(itemToDel.getItemName().toLowerCase(),
                    item.getItemName().toLowerCase())) {

                //marking the item as fitted/True in the hashmap
                missingItems.remove(itemToDel);
                missingItems.put(item, true);

                //if no more missing parts then ship is good to go
                if (getMissingPartCount() == 0) {
                    usable = true;
                }
                return;
            }
        }
    }


    public boolean isUsable() {
        return usable;
    }

    /**
     * Method which counts the number of missing parts in the mother ship
     *
     * @return count of missing parts
     *  
     */
    public int getMissingPartCount() {
        int count = 0;

        //Looping over the missing parts. If not found(False) we increment the count
        for (Map.Entry<Item, Boolean> entry : missingItems.entrySet()) {
            if (!entry.getValue()) {
                count++;
            }
        }
        return count;
    }


    /**
     * Get the total number of parts in the ship regardless of if fitted or not.
     *
     * @return count of total parts
     *  
     */
    public int getTotalPartCount() {
        return missingItems.size();
    }

}
