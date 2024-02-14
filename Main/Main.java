package Main;

import CelestialBody.CelestialBody;
import CelestialBody.Planet;
import Game.*;

import java.util.Scanner;

/**
 * The main class
 *
 *
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * 
     */
    public static void main(String[] args) {
        //scanner to get users name
        Scanner nameScanner = new Scanner(System.in);

        Printer.printMessage("Please enter your name");

        String playerName = nameScanner.nextLine();

        /**
         * Welcome prompt
         *
         * 
         */
        Printer.printMessage("Welcome, " + playerName + "! In \"Galaxy Quest,\" your mothership is " +
                "malfunctioning, " + "\n" + "and you must embark on an interstellar scavenger hunt to find scattered" +
                " ship parts across planets and moons." + "\n" + "Use the 'HELP' command to learn essential " +
                "commands for your space pod. Good luck on your cosmic adventure!");

        /**
         * Instantiating a new game object.
         *
         * 
         */
        Game game = new Game(playerName);
        game.run();
    }
}
