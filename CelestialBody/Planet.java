package CelestialBody;

import Interfaces.Describable;
import Items.Item;
import Misc.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Planet class
 *
 *  
 */
public class Planet extends CelestialBody {

    /**
     * List of moons
     *
     *  
     */

    private ArrayList<Moon> moonsList;


    public Planet(String name,
                  int size,
                  Location landingSpot,
                  ArrayList<Moon> moonsList,
                  HashSet<Item> hiddenItem,
                  String description) {
        super(name, size, landingSpot, hiddenItem, description);
        this.moonsList = moonsList;
    }

    /**
     * Adds a moon to the planet
     *
     * @param moon Moon to be added
     *  
     */
    public void addMoon(Moon moon) {
        moonsList.add(moon);
    }

    public ArrayList<Moon> getMoonsList() {
        return moonsList;
    }
}
