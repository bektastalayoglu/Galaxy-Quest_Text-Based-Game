package Game;

import CelestialBody.Planet;
import Player.Player;
import SpaceShip.MotherShip;
import SpaceShip.SpacePod;

import java.util.Scanner;
import java.util.Set;

/**
 * The class which houses all the variables and methods related to the current game session.
 * Object of game class holds all the pertaining information for the current game session.
 *
 */
public class Game {

    /**
     * Scanner which facilitates use input
     *
     */
    private Scanner scanner;

    /**
     * boolean to check if the game is still going on.
     *
     */
    private boolean gameOver;

    /**
     * a set containing all the planets
     *
     */
    private Set<Planet> spaceMap;

    /**
     * The player object which contains info about the player
     */
    private Player player;

    /**
     * The space pod object which the player can use to explore the space
     *
     */
    private SpacePod spacePod;

    /**
     * The mother ship which the player has to repair to go home
     *
     */
    private MotherShip motherShip;

    /**
     * Final planet where player travels to win the game
     *
     */
    private Planet destination;


    public Game(String playerName) {
        this.scanner = new Scanner(System.in);
        this.gameOver = false;
        this.spaceMap = GameGenerator.generatePlanets();
        this.player = GameGenerator.generatePlayer(playerName);
        this.destination = GameGenerator.generateDestination();
        this.spacePod = GameGenerator.generateSpacePod();
        this.motherShip = GameGenerator.generateMotherShip();
    }

    /**
     * The main loop of the game. will run until gameOver flag is false
     *
     */
    public void run() {
        do {
            Printer.printMessage("Enter Command");
            //Taking the input and parsing it
            Decoder.interpret(this.scanner.nextLine(), this);

        } while (!this.gameOver);
    }

    /**
     * Method to quit the game
     *
     *  
     */
    public void quit() {
        this.gameOver = true;
    }

    public Set<Planet> getSpaceMap() {
        return spaceMap;
    }

    public Player getPlayer() {
        return player;
    }

    public SpacePod getSpacePod() {
        return spacePod;
    }

    public MotherShip getMotherShip() {
        return motherShip;
    }

    public Planet getDestination() {
        return destination;
    }

    /**
     * Method invoked when mother ship is fixed and player travels back to home
     *
     */
    public void win() {
        Player winner = getPlayer();
        Printer.printMessage("Congratulations astronaut " + winner.getName() + ". You were able to do the " +
                "unthinkable and return back to the earth");
        this.quit();
    }
}
