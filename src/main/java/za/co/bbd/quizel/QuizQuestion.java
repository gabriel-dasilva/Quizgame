package za.co.bbd.quizel;

public class QuizQuestion {
    public String question;
    public String answer;
    public String[] options;
    public QuizQuestion(String question, String answer, String[] options){
        this.question = question;
        this.answer = answer;
        this.options = options;
    }
    public String getQuestion() {
        return this.question;
    }
    public String getAnswer() {
        return this.answer;
    }
    public String[] getOptions() {
        return this.options;
    }
}
