package Game;

/**
 * This class handles the printing Operations for the game
 *
 *  
 * @source https://ask.replit.com/t/how-to-change-text-colour-in-the-console/703,
 * https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
 */
public class Printer {
    private static final String BACKGROUND_COLOR = "\033[30m";//Black (background)
    private static final String RESET = "\u001B[0m";//reset color
    private static final String MESSAGE_COLOR = "\u001B[37m"; //white
    private static final String ERROR_COLOR = "\u001B[31m"; //red

    /**
     * Initializes background
     *
     *  
     */
    public static void initBg() {
        System.out.print(Printer.BACKGROUND_COLOR);
    }

    /**
     * To Display normal promp
     *
     * @param msg string to be displayed
     *  
     */
    public static void printMessage(String msg) {
        Printer.initBg();
        System.out.println(Printer.MESSAGE_COLOR + msg + Printer.RESET);
    }

    /**
     * Method which displays error prompts
     *
     * @param msg
     *  
     */
    public static void printError(String msg) {
        Printer.initBg();
        System.out.println(Printer.ERROR_COLOR + msg + Printer.RESET);
    }
}
