package za.co.bbd.quizel;

import za.co.bbd.quizel.services.ConsoleHandler;

public class Quizel
{

    public static void main( String[] args ) {
        Quizel quiz = new Quizel();
        quiz.begin();
    }

    void begin( ) {
        ConsoleHandler.printIntroduction();
        Commands commandHandler = new Commands();
        commandHandler.printCategories();

        boolean running = true;
        while(running) {
            String input = ConsoleHandler.getInputText("Selection").toLowerCase();

            switch (input) {
                case "m", "menu" -> commandHandler.restartQuiz();
                case "q", "quit" -> {
                    ConsoleHandler.printDoubleLineBox(ConsoleHandler.heart+"Thank you for playing!!"+ConsoleHandler.heart);
                    commandHandler.printScore();
                    commandHandler.closeProgram();
                    running = false;
                }
                default -> commandHandler.handleResponse(input);
            }
        }

        commandHandler.printScore();
    }
}
