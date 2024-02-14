package Enums;

import Game.Printer;
import Misc.Location;

/**
 * The enums representing Direction for the MOVE command.
 */
public enum Direction {
    NORTH(0,
            1),
    SOUTH(0,
            -1),
    EAST(1,
            0),
    WEST(-1,
            0);

    private int xChange;

    private int yChange;

    Direction(int xChange, int yChange) {
        this.xChange = xChange;
        this.yChange = yChange;
    }

    /**
     * Method used to move in a direction.
     *
     * @param location  current location
     * @param direction direction for movement
     * @return Location
     */
    public static Location move(Location location, Direction direction) {

        Location updatedLocation = new Location();
        //calculating new coordinates
        updatedLocation.setX(location.getX() + direction.getXChange());
        updatedLocation.setY(location.getY() + direction.getYChange());

        Printer.printMessage("Moved " + direction.toString());

        return updatedLocation;
    }

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }
}
