package CelestialBody;

import Interfaces.Describable;
import Items.Item;
import Misc.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class which represents celestial bodies ie planets and moons here.
 *
 *  
 */
public abstract class CelestialBody implements Describable {
    /**
     * name of the place
     *
     *  
     */
    protected String name;

    /**
     * Radius which can be discovered
     *
     *  
     */
    protected int radius;

    /**
     * Landing spot where the spaceship lands
     *
     *  
     */
    protected Location landingSpot;

    /**
     * Set of all the hidden items
     *
     *  
     */
    protected Set<Item> hiddenItems;

    /**
     * Description
     *
     *  
     */
    protected String description;

    protected CelestialBody(String name, int radius, Location landingSpot, HashSet<Item> hiddenItems,
                            String description) {
        this.name = name;
        this.radius = radius;
        this.landingSpot = landingSpot;
        this.hiddenItems = hiddenItems;
        this.description = description;
    }

    /**
     * Landing spot getter
     *
     * @return Location of landing spot
     *  
     */
    public Location getLandingSpot() {
        return this.landingSpot;
    }

    /**
     * Name getter
     *
     * @return name of the celestial body
     *  
     */
    public String getName() {
        return name;
    }

    public int getRadius() {
        return radius;
    }

    public Set<Item> getHiddenItems() {
        return hiddenItems;
    }

    /**
     * This method gives description of the celestial body.
     *
     * @return The description of the celestial body.
     * @
     */
    @Override
    public String readDescription() {
        return this.description;
    }

}
