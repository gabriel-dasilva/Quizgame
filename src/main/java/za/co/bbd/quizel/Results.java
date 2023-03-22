package za.co.bbd.quizel;

public class Results implements IResults {
    int totalQuestions;
    int correctAnswers;
    int wrongAnswers;

    public Results(int totalQuestions, int correctAnswers, int wrongAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public void showResult() {
        System.out.println("Your results!");
        System.out.println("Total Questions " + totalQuestions);
        System.out.println("Number of correct answers " + correctAnswers);
        System.out.println("Number of wrong answers " + wrongAnswers);
    }

}
