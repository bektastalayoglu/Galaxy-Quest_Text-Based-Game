package Misc;

import CelestialBody.CelestialBody;

/**
 * Class representing the location of the player/items using a 2D coordinate system.
 *
 *  
 */
public class Location {

    /**
     * Representing the position along the x-axis
     *
     *  
     */
    private int x;


    /**
     * Representing the position along y-axis
     *
     *  
     */
    private int y;


    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Method to check if the players' current location is within bounds of the planet/moon
     *
     * @param location current location of the player
     * @param celestialBody planet/moon for which we are checking
     * @return boolean True if the current location is withing bounds
     *
     *  
     */
    public static boolean isValid(Location location, CelestialBody celestialBody){

        //Checking along x axis
        if(location.getX() > celestialBody.getRadius() || location.getX() < -(celestialBody.getRadius())){
            return false;
        }

        //Checking along y axis
        if(location.getY() > celestialBody.getRadius() || location.getY() < -(celestialBody.getRadius())){
            return false;
        }

        return true;
    }

    /**
     * Method which compares the locations along both axes and check if they are equal.
     *
     *
     * @param l1 location1
     * @param l2 location2
     * @return True if the locations are identical along x and y dimension.
     */
    public static boolean isEqual(Location l1, Location l2){
        return (l1.getY() == l2.getY()) && (l1.getX() == l2.getX());
    }

    /**
     * Using the Pythagorean theorem calculates the distance between two points
     * @param l1 The first location
     * @param l2 The second location
     * @return The distance between the two locations
     *
     *  
     */
    public static double getDistance(Location l1, Location l2){
        double diffX = l1.getX() - l2.getX();
        double diffY = l1.getY() - l2.getY();
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }


}
