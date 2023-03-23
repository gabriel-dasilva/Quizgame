package za.co.bbd.quizel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;
import za.co.bbd.quizel.services.ConsoleHandler;
import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Quizel
{
    private static final Logger log = LoggerFactory.getLogger(Quizel.class);

    public static void main( String[] args ) {
        /* ToDO - Use file reading class to read genre's into an array of Genre class objects. The Genre class will have
         * functionality that will read in the questions for each genre.
         */
        Quizel quiz = new Quizel();
        quiz.begin();
    }

    void begin( ) {
        // TODO: Start game loop
        ConsoleHandler.printIntroduction();

        int countTotal = 0;
        int countCorrect = 0;
        int countWrong = 0;

        // TODO: Handle user input
        List<Genre> data = JsonDataMapper.getAllData();

        ConsoleHandler.printSingleLineBox("Choose a category");

        ConsoleHandler.printOptions(data.stream().map(Genre::GenreDescription).toList());
        ConsoleHandler.print(data.size() + ". " + "Random\n");

        ConsoleHandler.printControls();
        Random rand = new Random();

        boolean running = true;

        while(running) {
            String userInput = ConsoleHandler.getInputText("Category").toLowerCase();

            int selectedCategory;
            try {
                selectedCategory = Integer.parseInt(userInput);
            } catch(NumberFormatException exception) {
                log.debug("Invalid integer for category selection", exception);

                switch (userInput) {
                    case "m", "menu" -> ConsoleHandler.printMenu();
                    case "q", "quit" -> {
                        ConsoleHandler.printDoubleLineBox("Thank you for playing");
                        running = false;
                    }
                    default -> {
                        log.error("Invalid category", exception);
                        ConsoleHandler.print("Invalid selection");
                    }
                }
                continue;
            }

            Genre currentGenre;
            if(data.size() > Integer.parseInt(userInput)){
                currentGenre = data.get(selectedCategory-1);
            } else if(data.size() == selectedCategory) {
                currentGenre = data.get(rand.nextInt(data.size()-1));
            } else {
                log.debug("Failed to process category option");
                ConsoleHandler.print("Invalid category");
                continue;
            }

            // TODO: Try
            List<QuizQuestion> questions = currentGenre.GenreQuestions();
            ConsoleHandler.printDoubleLineBox("Begin " + currentGenre.GenreDescription() + " quiz!");
            countTotal = questions.size();

            for(QuizQuestion question: questions){
                Map<String, String> optionsMap = new HashMap<>();
                AtomicReference<Character> optionLetter = new AtomicReference<>('A');

                question.options().stream().unordered().forEach(option -> {
                    optionsMap.put(String.valueOf(optionLetter.get()), option);
                    optionLetter.updateAndGet(v -> (char) (v + 1));
                });

                String userAnswer = ConsoleHandler
                        .getUserAnswer(question.question(), optionsMap)
                        .trim().toUpperCase();

                if(question.answer().equals(optionsMap.get(userAnswer))) {
                    ConsoleHandler.printSingleLineBox("Correct Answer");
                    countCorrect++;
                } else {
                    ConsoleHandler.printSingleLineBox("Wrong Answer");
                    countWrong++;
                }
            }
        }

        Results result = new Results(countTotal,countCorrect, countWrong);
        result.showResult();
    }
}
