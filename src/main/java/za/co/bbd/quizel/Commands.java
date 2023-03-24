package za.co.bbd.quizel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;
import za.co.bbd.quizel.services.ConsoleHandler;
import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Commands {
    private static final Logger log = LoggerFactory.getLogger(Commands.class);
    private static final List<Genre> categories = JsonDataMapper.getAllData();;
    private static int currentQuestionIndex = 0;
    private String currentCorrectAnswer = null;
    private final Results result = new Results(0, 0);
    boolean isMiddleOfGame = false;
    Genre currentGenre;

    private void processUserAnswer(String userAnswer){
        if(userAnswer.equalsIgnoreCase(currentCorrectAnswer)) {
            ConsoleHandler.printSingleLineBox("Correct Answer");
            result.incrementCorrectAnswers();
        } else {
            ConsoleHandler.printSingleLineBox("Wrong Answer");
            result.incrementWrongAnswers();
        }
    }

    public void closeProgram(){
        ConsoleHandler.closeInputStream();
        System.exit(0);
    }

    public void printScore() {
        if(!isMiddleOfGame) {
            result.showResult();
        } else if(result.getCorrectAnswers() > 0 || result.getWrongAnswers() > 0){
            int numberOfQuestions = result.getTotalQuestions();
            String midGameScore = "Your score was " + result.getCorrectAnswers() + "/" + numberOfQuestions;
            ConsoleHandler.printSingleLineBox(midGameScore);
        }
    }

    public void handleResponse(String input) {
        boolean isNumberOption = input.length() == 1 && Character.isDigit(input.charAt(0));

        if(isNumberOption && null == currentGenre && Integer.parseInt(input) >= 1
                && Integer.parseInt(input) - 1 <= categories.size()) {
            int selection = Integer.parseInt(input);
            selectCategory(selection - 1);
            ConsoleHandler.printSingleLineBox("Begin " + currentGenre.GenreDescription() + " quiz!");
            displayNextQuestion(currentGenre.GenreQuestions().get(currentQuestionIndex));
        } else if(null != currentGenre) {
            processUserAnswer(input);
            currentQuestionIndex++;

            if(currentGenre.GenreQuestions().size() == currentQuestionIndex) {
                log.info("Resetting the genre: {}", currentQuestionIndex);
                resetGenre();
                result.showResult();
                printCategories();
            } else {
                displayNextQuestion(currentGenre.GenreQuestions().get(currentQuestionIndex));
            }
        } else {
            printCategories();
        }
    }

    private void resetGenre() {
        currentGenre = null;
        currentQuestionIndex = 0;
    }

    private void displayNextQuestion(QuizQuestion question) {
        Map<String, String> optionsMap = new HashMap<>();
        AtomicReference<Character> optionLetter = new AtomicReference<>('A');

        Collections.shuffle(question.options());
        question.options().forEach(option -> mapLetterToOption(option, question, optionsMap, optionLetter));

        ConsoleHandler.printQuestion(question.question(), optionsMap);
    }

    private void mapLetterToOption(String option, QuizQuestion question, Map<String, String> optionsMap,
                                   AtomicReference<Character> optionLetter) {
        if(question.answer().equals(option)) {
            currentCorrectAnswer = String.valueOf(optionLetter.get());
        }

        optionsMap.put(String.valueOf(optionLetter.get()), option);
        optionLetter.updateAndGet(v -> (char) (v + 1));
    }

    private void selectCategory(int selection) {
        if(categories.size() == selection) {
            List<QuizQuestion> randomQuestions = categories.stream()
                    .map(category -> category.GenreQuestions().get(new Random().nextInt(4)))
                    .toList();
            currentGenre = new Genre("Random", randomQuestions);
        } else if(selection >= 0 && selection < categories.size()) {
            currentGenre = categories.get(selection);
        }
    }

    public void printCategories() {
        ConsoleHandler.printSingleLineBox("Choose a category");
        ConsoleHandler.printGenreDescriptions(categories);
        ConsoleHandler.printControls();
    }

    public void restartQuiz() {
        resetGenre();
        result.showResult();
        result.resetCounts();
        printCategories();
    }
}

