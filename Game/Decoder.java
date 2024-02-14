package Game;

import Enums.Command;

import java.util.Arrays;

/**
 * Class responsible for extracting information from user entered prompt
 *
 *  
 */
public class Decoder {

    /**
     * Method which splits the user entered lines into an array of strings. And executes the command with
     * correct arguments
     *
     * @param string User input line from terminal
     * @param game   game object
     *  
     */
    public static void interpret(String string, Game game) {

        //splitting the line into an array of Strings
        String[] split = string.split(" ");

        //Getting the corresponding command
        Command command = Command.stringToCommand(split[0]);

        if (command == null) {
            Printer.printError("Cannot find command: " + split[0]);
        } else {
            //if valid no of parameters provided
            if (Command.commandParamValid(command, split.length - 1)) {
                Command.runCommand(Arrays.copyOfRange(split, 1, split.length), game, command);
            } else {
                Printer.printError("Incorrect no of Parameters for the Command : " + command);
            }
        }
    }
}
