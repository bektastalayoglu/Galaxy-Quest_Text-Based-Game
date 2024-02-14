package Enums;

import CelestialBody.*;
import Game.*;
import Interfaces.CommandFunction;
import Items.Item;
import Misc.Location;
import SpaceShip.MotherShip;
import SpaceShip.SpacePod;
import SpaceShip.SpaceShip;

import java.util.Objects;
import java.util.Set;

/**
 * The Command represents a set of commands exist in the game.
 * Each command has its own description, keyword, and action.
 * Using commands, the method associated with the command is called.
 *
 *  
 *  
 */

public enum Command {

    /**
     * ALl the possible commands
     *
     *  
     */
    HELP(
            "Shows all the available commands",
            "HELP",
            (args, game) -> Command.showHelp(),
            0
    ),
    INVENTORY(
            "This command will show you items you have in your inventory. ",
            "INVENTORY",
            (args, game) -> Command.inventory(game),
            0
    ),
    QUIT(
            "This command will quit the game.",
            "QUIT",
            (args, game) -> Command.quitGame(game),
            0

    ),
    SPACEPOD(
            "This command will hop you in and out of the spacepod.",
            "SPACEPOD",
            (args, game) -> Command.accessSpacePod(game),
            0
    ),
    PICKUP(
            "Pickup an item and add it to your inventory",
            "PICKUP",
            (args, game) -> Command.pickup(game),
            0


    ),
    FIT(
            "Fit the ship part from your inventory, write fit + part-name. Example -> fit cpu",
            "FIT",
            (args, game) -> Command.fit(args[0], game),
            1
    ),
    GO(
            "To use this command you have to write go + the name of the place (planet or moon) you want " +
                    "to go. Example -> go Lava",
            "GO",
            (args, game) -> Command.go(args[0], game),
            1
    ),
    MOVE(
            "Move one of the directions, write move + direction. Example -> move east",
            "MOVE",
            (args, game) -> Command.move(args[0], game),
            1
    ),

    LOOK(
            "Look around",
            "LOOK",
            (args, game) -> Command.look(game),
            0
    );


    // Description of each command alongside example
    private final String description;

    //The command for which the enum is used
    private final String cmdString;

    // The method assigned to the function
    private final CommandFunction function;

    //No of parameters needed for the functioning of the command
    private final int paramCount;

    //CommandFunction interface used for execution of lambdas
    Command(String description, String cmdString, CommandFunction function, int paramCount) {
        this.description = description;
        this.cmdString = cmdString;
        this.function = function;
        this.paramCount = paramCount;
    }

    /**
     * Method which matches the commands to the input
     *
     * @param inputString the String user entered
     * @return Command enum as per the input
     *  
     */
    public static Command stringToCommand(String inputString) {
        Command currCommand = null;

        for (Command command : Command.values()) {
            //Comparing lowercase values of the 2 strings
            if (Objects.equals(command.cmdString.toLowerCase(), inputString.toLowerCase())) {
                currCommand = command;
            }
        }

        return currCommand;
    }


    /**
     * Method to quit the game. Invoked when quit enum is executed
     *
     * @param game
     *  
     */
    private static void quitGame(Game game) {
        game.quit();
    }

    /**
     * Method which shows the description of all the possible commands.
     * Invoked when help command is executed
     *
     *  
     */
    private static void showHelp() {
        String s = "";
        for (Command c : Command.values()) {
            s += c.name() + " : " + c.description + "\n";
        }
        Printer.printMessage(s);
    }

    /**
     * The Method used to travel in the space pod to the celestial-bodies and back to the mother ship.
     * Also, the method used to travel back to earth and win the game.
     * <p>
     * Functioning: loops through all the celestial bodies if the currentPlace is spacepod. Otherwise, we will check for
     * the keyword from GoUtil enums(back, home)
     * <p>
     * Invoked when the go command is used
     *
     * @param placeString the string from the terminal used with the go command
     * @param game        game object
     *  
     */
    public static void go(String placeString, Game game) {
        //Flag for if destination found
        boolean destinationFound = false;


        if (game.getPlayer().getCurrentPlace() instanceof SpacePod) {

            //Getting all the possible planets
            Set<Planet> planets = game.getSpaceMap();

            //Looping over planets and moons if strings match we break out of the loop.
            for (Planet p : planets) {
                if (Objects.equals(placeString.toLowerCase(), p.getName().toLowerCase()) &&
                        !destinationFound) {
                    game.getSpacePod().travelTo(game.getPlayer(), p);
                    game.getSpacePod().setCurrentLocation(p.getLandingSpot());
                    Printer.printMessage("Travelled to Planet : " + p.getName());
                    Printer.printMessage("You are free to move now!");
                    destinationFound = true;
                    break;
                }

                for (Moon m : p.getMoonsList()) {
                    if (Objects.equals(placeString.toLowerCase(), m.getName().toLowerCase()) &&
                            !destinationFound) {
                        game.getSpacePod().travelTo(game.getPlayer(), m);
                        game.getSpacePod().setCurrentLocation(p.getLandingSpot());
                        Printer.printMessage("Travelled to Moon : " + m.getName());
                        Printer.printMessage("You are free to move now!");
                        destinationFound = true;
                        break;
                    }
                }
            }

            //If the user wants to return to the mothership
            // Comparing the input string with enum back
            if (Objects.equals(placeString.toLowerCase(),
                    GoUtils.BACK.toString().toLowerCase()) &&
                    !destinationFound) {
                game.getSpacePod().travelBack(game.getPlayer(), game.getMotherShip());
                game.getPlayer().setMovable(false);
                Printer.printMessage("Travelled back to the Mothership");
            }

        }
        //Case, when the user has a working mother ship and wants to return back to earth
        else if (game.getPlayer().getCurrentPlace() instanceof MotherShip) {
            //Checking if the conditions are met and the input string is valid
            if (game.getMotherShip().isUsable() &&
                    Objects.equals(placeString.toLowerCase(), GoUtils.HOME.toString().toLowerCase())) {
                game.getMotherShip().travelTo(game.getPlayer(), game.getDestination());
                game.win();
            } else {
                if (game.getMotherShip().isUsable()) {
                    Printer.printError("Can and should go to only 1 place EARTH");
                } else {
                    Printer.printError("MotherShip still not fixed cant travel HOME");
                }
            }
        } else {
            Printer.printError("Try again cant travel to : " + placeString);
        }
    }

    /**
     * The Method used to get into the space pod to travel to planets and moons.
     *
     * @param game the game object
     *  
     */
    private static void accessSpacePod(Game game) {

        game.getPlayer().setCurrentPlace(game.getSpacePod());
        game.getPlayer().setMovable(false);
        Printer.printMessage("Using the space pod use the go command to travel around");
        Printer.printMessage(Command.GO.description);

    }

    /**
     * Method which is used pickup the scattered items(ship parts)
     * Invoked when the PICKUP command is executed
     *
     * @param game game object
     *  
     *  
     */
    public static void pickup(Game game) {

        //Checking if player is at valid place
        if (game.getPlayer().getCurrentPlace() instanceof SpaceShip) {
            Printer.printError("Can't perform action here!");
        } else {
            CelestialBody currPlace = (CelestialBody) game.getPlayer().getCurrentPlace();

            //flag to check if item found
            boolean foundFlag = false;

            // looping the items
            for (Item item : currPlace.getHiddenItems()) {

                //Checking if the location of player and item are the same
                if (Location.isEqual(item.getItemLocation(),
                        game.getPlayer().getCurrentLocation())) {

                    //removing form the planet/moon and adding t the player inventory
                    currPlace.getHiddenItems().remove(item);
                    game.getPlayer().addItemtoInventory(item);
                    Printer.printMessage("!Added Item " + item.getItemName() + " to the bag");
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                Printer.printError("Nothing to pickup");
            }
        }
    }

    /**
     * The method which shows the current state of the player bag/inventory
     * Invoked when INVENTORY command is executed
     *
     * @param game
     *  
     */
    public static void inventory(Game game) {
        game.getPlayer().getInventory().printInventory();
    }


    /**
     * The method which handles the movement of the player.
     * Invoked when the MOVE command is executed
     *
     * @param directionString the user provided string alongside the MOVE command
     * @param game            game object
     *  
     *  
     */
    public static void move(String directionString, Game game) {

        //Check for movable and the correct place
        if ((game.getPlayer().isMovable() &&
                (game.getPlayer().getCurrentPlace() instanceof CelestialBody))) {

            //Looping all the possible directions
            for (Direction d : Direction.values()) {
                //The user input string matches one of the directions
                if (Objects.equals(directionString.toLowerCase(), d.toString().toLowerCase())) {


                    Location currLocation = game.getPlayer().getCurrentLocation();

                    //calculating the possible location
                    Location updatedLocation = Direction.move(game.getPlayer().getCurrentLocation(), d);

                    /**
                     *
                     * This part of the method checks the distance between the item and the player and
                     * prints a message to inform the player based on the movement.
                     *
                     *  
                     */
                    for (Item item : ((CelestialBody) game.getPlayer().getCurrentPlace()).getHiddenItems()) {
                        if (Location.getDistance(currLocation, item.getItemLocation()) >
                                Location.getDistance(updatedLocation, item.getItemLocation())) {
                            Printer.printMessage("You are getting closer to the hidden item maybe you " +
                                    "are even there look around!");
                        } else {
                            Printer.printMessage("You are moving away from the hidden item!");
                        }
                        break;
                    }

                    //Checking if the calculated location is out of bounds or not
                    if (!Location.isValid(updatedLocation, (CelestialBody) game.getPlayer().getCurrentPlace())) {
                        Printer.printError("Cant move in " + d.toString() +
                                " anymore move in another direction");
                        game.getPlayer().setCurrentLocation(currLocation);
                    } else {
                        game.getPlayer().setCurrentLocation(updatedLocation);
                    }

                    //Exiting the loop as found the valid direction
                    break;
                }
            }
        } else {
            Printer.printError("Cant move try again!");
        }
    }

    /**
     * The look method provides important information for players, depending on their location/place.
     * Functioning:
     * - If the player is within the mother ship, according to mother ship status look method print different message.
     * - If the player is within the space pod , it shows a list of planets to explore.
     * - If the player is on a planet, look method give you a description of the planet
     * and if the current planet has any moons the look method will list the moons.
     * - When the player is on a planet or moon and is at the exact location of the hidden item or the item is not there,
     * the look method will give you a clue.
     *
     * @param game The Game object
     *  
     */
    public static void look(Game game) {
        // Get current place/location of the player.
        Object currPlace = game.getPlayer().getCurrentPlace();

        // Get the planets in the space map
        Set<Planet> planets = game.getSpaceMap();

        // Check! Is player in the mother ship ?
        if (currPlace instanceof MotherShip) {
            // Check! Is the mother ship usable?
            if (!game.getMotherShip().isUsable()) {
                // If the mother ship is not usable print this message.
                Printer.printMessage("You are in the mothership, your mothership is malfunctioning, " + "\n" +
                        "use your spacepod and discover planets and moons. Find hidden" +
                        "parts and fix your mothership ");
            } else {
                // If the mother ship is usable print this message.
                Printer.printMessage("You are in the mothership, your mothership is usable now. " + "\n" +
                        "You are ready to go back to earth...");
            }
        }

        // Check! Is player in the space pod ?
        if (currPlace instanceof SpacePod) {
            Printer.printMessage("Now you are in the space pod. You can go to this planets: ");
            // List all the planets
            for (Planet p : planets) {
                Printer.printMessage(p.getName());
            }
            Printer.printMessage("You can also go 'BACK' to return to the Mothership");
        }

        // Check! Is player in the celestial body ?
        if (currPlace instanceof CelestialBody) {
            // Give information about the celestial body
            Printer.printMessage("You are in the : " + ((CelestialBody) currPlace).getName() + " " +
                    ((CelestialBody) currPlace).readDescription() +
                    "Use move command to move and find the hidden item...");

            // If the celestial body is a planet, list its moons
            if (currPlace instanceof Planet) {
                for (Moon moon : ((Planet) currPlace).getMoonsList()) {
                    Printer.printMessage(((Planet) currPlace).getName() + " has this moon : "
                            + moon.getName() + "\n" + "You can go to this moon and search for the hidden item...");
                }
            }

            // If the player did not pick up the item check for hidden items on the celestial body
            if (!((CelestialBody) currPlace).getHiddenItems().isEmpty()) {
                for (Item item : ((CelestialBody) currPlace).getHiddenItems()) {
                    // Check if the player is close to a hidden item
                    if (Location.isEqual(item.getItemLocation(),
                            game.getPlayer().getCurrentLocation())) {
                        Printer.printMessage("-----------------------------------------------------------");
                        Printer.printMessage("You are really close to item you can search and pickup the item...");
                    } else {
                        // Give information the player about current location
                        Printer.printMessage("Keep moving...");
                    }
                }
            } else {
                // If the player picked up the hidden item print a message about it.
                Printer.printMessage("You already found the item. Go other places to find item...");
            }
        }
    }


    /**
     * The method which handles the repairing of the mothership.
     * The ship part is fitted into the mothership to repair its state.
     * Invoked when the FIT command is executed.
     *
     * @param itemString the user input string provided alongside the FIT command
     * @param game       game object
     *  
     */
    public static void fit(String itemString, Game game) {

        //Checking if at the correct mothership
        if (game.getPlayer().getCurrentPlace() instanceof MotherShip) {

            //flag if item found
            boolean foundFlag = false;

            //Looping through the inventory and comparing the itemString with the items
            for (Item item : game.getPlayer().getInventory().getItemsInInventory()) {
                if (Objects.equals(itemString.toLowerCase(), item.getItemName().toLowerCase())) {

                    Printer.printMessage("Fitted the part : " + item.getItemName().toUpperCase());

                    //Fitting the part into the mother ship and removing from the inventory
                    game.getMotherShip().fitPart(item);
                    game.getPlayer().getInventory().removeItem(item);

                    Printer.printMessage(game.getMotherShip().getMissingPartCount() + "/"
                            + game.getMotherShip().getTotalPartCount() + " Left");

                    foundFlag = true;
                    break;
                }
            }

            if (!foundFlag) {
                Printer.printError("No part named : " + itemString + " found in inventory");
            }
        } else {
            Printer.printError("You need to be at MotherShip to fit the parts!");
        }
    }

    /**
     * The method is used to run the commands in a safe manner
     *
     * @param args    arguments for the commands
     * @param game    game object
     * @param command command to be executed
     *  
     */
    public static void runCommand(String[] args, Game game, Command command) {
        try {
            command.function.runCommand(args, game);
        } catch (Exception e) {
            Printer.printError("Oops something went wrong try again");
        }
    }

    /**
     * simple method which checks if the no of parameters provided in the terminal are correct or not.
     *
     * @param command    Command to be executed
     * @param paramCount Count of parameters provided
     * @return boolean if the no of parameters are correct
     */
    public static boolean commandParamValid(Command command, int paramCount) {
        return command.paramCount == paramCount;
    }


}
