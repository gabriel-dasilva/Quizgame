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
        } else if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C") || userInput.equals("D")) {
            ProcessUserAnswer(userInput, questions[0]);
        }else if(userInput.equals("menu") || userInput.equals("m")){
            Menu();
        }else if(Integer.parseInt(input) < this.categories.size() && Integer.parseInt(input) > 1){
            questions(input);
        }
        else {
            ListCommands();
        }

    }

    private static void ListCommands(){
        System.out.println("Place Holder");
    }

    private void ProcessUserAnswer(String userAnswer, QuizQuestion q){
        char ans;
        ans = userAnswer.charAt(0);

        String answer = "";

        switch (ans) {
            case 'A' -> answer = q.options().get(0);
            case 'B' -> answer = q.options().get(1);
            case 'C' -> answer = q.options().get(2);
            case 'D' -> answer = q.options().get(3);
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
        System.out.println( "[M]enu: Go to menu                  [Q]uit: Quit game" ); // Handle user input
    }

    private void questions(String userGenre){
        this.countTotal = 0;
        this.countCorrect = 0;
        this.countWrong = 0;

//        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        List<QuizQuestion> questions;
//        questions = this.categories.get(rand.nextInt(this.categories.size()-1)).GenreQuestions();
//        System.out.println("============================================================================================");
//        System.out.println( "Begin " +  this.categories.get(rand.nextInt(this.categories.size()-1)).GenreDescription() + " Quiz!");
//        System.out.println("============================================================================================");
        questions = this.categories.get(Integer.parseInt(userGenre)-1).GenreQuestions();
        System.out.println("============================================================================================");
        System.out.println( "Begin " +  this.categories.get(Integer.parseInt(userGenre)-1).GenreDescription() + " Quiz!");
        System.out.println("============================================================================================");

        countTotal = questions.size();

        for(QuizQuestion q: questions){
            System.out.println(q.question());
            System.out.println("A : " +q.options().get(0));
            System.out.println("B : " +q.options().get(1));
            System.out.println("C : " +q.options().get(2));
            System.out.println("D : " +q.options().get(3));

            ProcessInput(sc.nextLine(),q); //Handle user input

            System.out.println("============================================================================================");
        }
        Results result = new Results(countTotal,countCorrect, countWrong);
        result.showResult();
    }
}

