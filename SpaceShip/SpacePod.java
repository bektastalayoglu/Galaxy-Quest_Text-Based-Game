package SpaceShip;

import Misc.Location;
import Player.Player;

/**
 * class representing space pod
 *
 *  ,  
 */
public class SpacePod extends SpaceShip {

    /**
     * Current location of the space pod
     */
    private Location currentLocation;

    /**
     * Constructor of SpacePod Class
     *
     * @param name            : represent name of the spacepod
     * @param currentLocation : represent current location of the spacepod
     */
    public SpacePod(String name, Location currentLocation) {
        super(name);
        this.currentLocation = currentLocation;
    }


    /**
     * This method sets the current location of the space pod.
     *
     * @param currentLocation The new current location of the space pod.
     *  
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Method which transports back the space pod to the mother ship
     *
     * @param player     current player object
     * @param motherShip the mother ship object
     *  
     */
    public void travelBack(Player player, MotherShip motherShip) {
        player.setCurrentPlace(motherShip);
        player.setMovable(false);
    }

}
