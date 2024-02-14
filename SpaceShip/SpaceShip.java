package SpaceShip;

import CelestialBody.*;
import Game.Printer;
import Player.Player;

/**
 * Abstract class which represents spaceships: mother ship and space pod here.
 *
 *  
 */
public abstract class SpaceShip {

    /** name of the spaceship
     *
     *  
     */
    protected String name;

    /**
     * Constructor of Spaceship Abstract Class
     *
     * @param name         : represent name of the spaceship
     *
     *  
     */
    protected SpaceShip(String name) {
        this.name = name;
    }

    /**
     * the method responsible for the travel mechanism of the space pod and the mother ship.
     * allows the spaceships to travel to a celestial body
     *
     * @param player player object
     * @param celestialBody The destination
     *
     *  
     */
    public  void travelTo(Player player, CelestialBody celestialBody){
            //setting the location to the landing spot and enabling player movement
            player.setCurrentPlace(celestialBody);
            player.setCurrentLocation(celestialBody.getLandingSpot());
            player.setMovable(true);
    }

}
