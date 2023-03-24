package za.co.bbd.quizel.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleHandler {
    private static final Logger log = LoggerFactory.getLogger(ConsoleHandler.class);
    private static final Scanner sc = new Scanner(System.in);
    public static final String sadFace = "\uD83D\uDE13";
    public static final String confetti = "\uD83C\uDF89";
    public static final String happyFace = "\uD83E\uDD73";
    public static final String heart= "\uD83D\uDC96";

    private ConsoleHandler() {
    }

    public static void printIntroduction() {
        printDoubleLineBox("Welcome to quizel!");
    }

    public static void printDoubleLineBox(String text) {
        print("================================================================================");
        print(padWithSpaces(text));
        print("================================================================================\n");
    }

    public static void printSingleLineBox(String text) {
        print("----------------------------------------------------------------------");
        print(padWithSpaces(text, 70));
        print("----------------------------------------------------------------------");
    }

    private static String padWithSpaces(String text) {
        return padWithSpaces(text, 80);
    }

    private static String padWithSpaces(String text, int maxLength) {
        if(text.length() == 0 || text.length() >= maxLength) {
            return text;
        }

        int numberOfSpaces = (maxLength / 2) - (text.length() / 2);
        return " ".repeat(numberOfSpaces) + text;
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void printControls() {
        print(addSpacesBetween("[M]enu: Go to menu", "[Q]uit: Quit game\n"));
    }

    private static String addSpacesBetween(String leftString, String rightString) {
        return addSpacesBetween(leftString, rightString, 80);
    }

    private static String addSpacesBetween(String leftString, String rightString, int maxLength) {
        int numberOfSpaces = maxLength - (leftString.length() + rightString.length());

        if(numberOfSpaces < 1) {
            numberOfSpaces = 1;
        }

        return leftString + " ".repeat(numberOfSpaces) + rightString;
    }

    /**
     * Gets user input text
     *
     * @param label for guiding user during input prompt
     * @return valid string entered by the user
     * @throws IllegalArgumentException when user fails to enter valid input 10 times
     */
    public static String getInputText(String label) throws IllegalArgumentException {
        int numberOfAttempts = 0;

        String input = "";

        while(input.trim().length() == 0) {
            if(numberOfAttempts > 10) {
                throw new IllegalArgumentException("Max attempts reached");
            }

            printInputLabel(label);
            input = sc.nextLine();
            numberOfAttempts++;
        }

        return input.trim();
    }

    private static void printInputLabel(String label) {
        if(label != null && label.length() > 0) {
            System.out.print(label + ": ");
        }
    }

    public static void printQuestion(String question, Map<String, String> options) {
        print(question);
        options.forEach((key, value) -> print(key + ". " + value));
        print("");
    }

    public static void closeInputStream() {
        try {
            sc.close();
        } catch (IllegalStateException exception) {
            log.error("Scanner resource already closed", exception);
        }
    }

    public static void printGenreDescriptions(List<Genre> genres) {
        int count = 1;

        for (Genre genre : genres){
            print(count + ". " + genre.GenreDescription());
            count++;
        }

        ConsoleHandler.print(count + ". " + "Random\n");
    }
}
