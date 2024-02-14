package CelestialBody;

import Interfaces.Describable;
import Items.Item;
import Misc.Location;

import java.util.HashSet;

/**
 * class representing moon
 *
 *  
 */
public class Moon extends CelestialBody {

    /**
     * the parent planet for the moon
     *
     *  
     */
    private Planet parentPlanet;

    public Moon(String name,
                int size,
                Location landingSpot,
                Planet parentPlanet,
                HashSet<Item> hiddenItem,
                String description) {
        super(name, size, landingSpot, hiddenItem, description);
        this.parentPlanet = parentPlanet;
    }


}
