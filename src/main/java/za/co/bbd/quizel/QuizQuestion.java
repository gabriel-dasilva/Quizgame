package za.co.bbd.quizel;

public class QuizQuestion {
    public String Question;
    public String Answer;
    public String[] Options;
    public QuizQuestion(String ques, String ans, String[] opt){
        Question = ques;
        Answer = ans;
        Options = opt;
    }
    public String getQuestion() {
        return Question;
    }
    public String getAnswer() {
        return Answer;
    }
    public String[] getOptions() {
        return Options;
    }
}
