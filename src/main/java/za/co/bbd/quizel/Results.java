package za.co.bbd.quizel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import za.co.bbd.quizel.services.ConsoleHandler;

@Getter
@AllArgsConstructor
public class Results {
    private int correctAnswers;
    private int wrongAnswers;

    public void showResult() {
        ConsoleHandler.print("Your results!");
        ConsoleHandler.print("Total Questions " + getTotalQuestions());
        ConsoleHandler.print("Number of correct answers " + getCorrectAnswers());
        ConsoleHandler.print("Number of wrong answers " + getWrongAnswers());
    }

    public int getTotalQuestions() {
        return getCorrectAnswers() + getWrongAnswers();
    }

    public void resetCounts() {
        correctAnswers = 0;
        wrongAnswers = 0;
    }

    public void incrementWrongAnswers() {
        wrongAnswers++;
    }

    public void incrementCorrectAnswers() {
        correctAnswers++;
    }
}
