package za.co.bbd.quizel;

import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Commands {
    List<Genre> categories;
    int countTotal = 0;
    int countCorrect = 0;
    int countWrong = 0;
    int count = 1;

    public Commands(List<Genre> genre) {
        this.categories = genre;
    }
    public void ProcessInput(String input, QuizQuestion... questions){
        String userInput = input.toLowerCase().trim();
        if (userInput.equals("quit") || userInput.equals("q")) {
            CloseProgram();
        } else if (userInput.equals("a") || userInput.equals("b") || userInput.equals("c") || userInput.equals("d")) {
            ProcessUserAnswer(userInput, questions[0]);
        }else if(userInput.equals("menu") || userInput.equals("m")){
            Menu();
        }else if(Integer.parseInt(input) <= this.categories.size() && Integer.parseInt(input) >= 1){
            questions(input);
        }
        else {
            ListCommands();
        }

    }

    private static void ListCommands(){
        System.out.println( "[M]enu: Go to menu\n[Q]uit: Quit game\nPick a Category using the corresponding index ie: 1 for 1.Sport\nInput answer A,B,C or D" );
    }

    private void ProcessUserAnswer(String userAnswer, QuizQuestion q){
        char ans;
        ans = userAnswer.charAt(0);

        String answer = "";

        switch (ans) {
            case 'a' -> answer = q.options().get(0);
            case 'b' -> answer = q.options().get(1);
            case 'c' -> answer = q.options().get(2);
            case 'd' -> answer = q.options().get(3);
            default -> {
            }
        }

        if(answer.equals(q.answer()))
        {
            System.out.println("------------------------------------------------------");
            System.out.println("                  Correct Answer                      ");
            System.out.println("------------------------------------------------------");
            countCorrect++;
        }
        else
        {
            System.out.println("------------------------------------------------------");
            System.out.println("                  Wrong Answer                      ");
            System.out.println("------------------------------------------------------");
            countWrong++;
        }
    }

    private static void CloseProgram(){
        System.exit(0);
    }

    private void Menu(){
        System.out.println("------------------------------------------------------");
        System.out.println( "                  Choose a category!" );
        System.out.println("------------------------------------------------------");

        this.count = 1;

        for (Genre genreOption : this.categories){
            System.out.println(this.count + ". " + genreOption.GenreDescription());
            this.count++;
        }
        System.out.println(this.count + ". " + "Random\n");
    }

    private void questions(String userGenre){
        this.countTotal = 0;
        this.countCorrect = 0;
        this.countWrong = 0;
        Scanner sc = new Scanner(System.in);
        List<QuizQuestion> questions;

        questions = this.categories.get(Integer.parseInt(userGenre)-1).GenreQuestions();
        System.out.println("============================================================================================");
        System.out.println( "Begin " +  this.categories.get(Integer.parseInt(userGenre)-1).GenreDescription() + " Quiz!");
        System.out.println("============================================================================================");

        this.countTotal = questions.size();

        for(QuizQuestion q: questions){
            System.out.println(q.question());
            System.out.println("A : " +q.options().get(0));
            System.out.println("B : " +q.options().get(1));
            System.out.println("C : " +q.options().get(2));
            System.out.println("D : " +q.options().get(3));

            ProcessInput(sc.nextLine(),q); //Handle user input

            System.out.println("============================================================================================");
        }
        Results result = new Results(this.countTotal,this.countCorrect, this.countWrong);
        result.showResult();
    }
}

