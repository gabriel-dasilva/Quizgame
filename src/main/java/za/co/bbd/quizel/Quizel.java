package za.co.bbd.quizel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;
import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        Commands userInputs = new Commands();
        System.out.println( "Welcome to quizel!" );
        Scanner sc = new Scanner(System.in);

        int countTotal = 0;
        int countCorrect = 0;
        int countWrong = 0;

        // TODO: Handle user input
        List<Genre> data = JsonDataMapper.getAllData();

        System.out.println("============================================================================================");
        System.out.println( "                  Welcome to Quizel!" );
        System.out.println("------------------------------------------------------");
        System.out.println( "                  Choose a category!" );
        System.out.println("------------------------------------------------------");

        int count = 1;
        for (Genre genreOption : data){
            System.out.println(count + ". " + genreOption.GenreDescription());
            count++;
        }
        System.out.println(count + ". " + "Random");

        System.out.println();
        System.out.println( "[M]enu: Go to menu                  [Q]uit: Quit game" ); // Handle user input
        boolean temp = true;
        Random rand = new Random();

        while(temp){
//            userInputs.ProcessInput(sc.nextLine());
            String userGenre = sc.nextLine(); //handle user input
            List<QuizQuestion> questions;
            if (count == Integer.parseInt(userGenre)){
                questions = data.get(rand.nextInt(data.size()-1)).GenreQuestions();
                System.out.println("============================================================================================");
                System.out.println( "Begin " +  data.get(rand.nextInt(data.size()-1)).GenreDescription() + " Quiz!");
                System.out.println("============================================================================================");
            } else {
                questions = data.get(Integer.parseInt(userGenre)-1).GenreQuestions();
                System.out.println("============================================================================================");
                System.out.println( "Begin " +  data.get(Integer.parseInt(userGenre)-1).GenreDescription() + " Quiz!");
                System.out.println("============================================================================================");
            }

            countTotal = questions.size();

            for(QuizQuestion q: questions){
                System.out.println(q.question());
                System.out.println("A : " +q.options().get(0));
                System.out.println("B : " +q.options().get(1));
                System.out.println("C : " +q.options().get(2));
                System.out.println("D : " +q.options().get(3));

                // userInputs.ProcessInput(sc.nextLine()); //Handle user input
                String userAnswer = sc.nextLine();

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

                System.out.println("============================================================================================");
            }
            temp= !temp;
        }

        Results result = new Results(countTotal,countCorrect, countWrong);
        result.showResult();
    }
}
