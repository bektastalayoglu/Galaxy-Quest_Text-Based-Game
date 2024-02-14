package Interfaces;

import Game.Game;

/**
 * Interface for Commands in the game. Every command has a method which can be executed.
 *
 *  
 */
public interface CommandFunction {
    /**
     * Method to run the Commands Lambda Functions
     *
     * @param args arguments for the command
     * @param game the game object
     *  
     */
    void runCommand(String[] args, Game game);
}
