package za.co.bbd.quizel;

import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.List;
import java.util.Scanner;

public class Quizel
{
    void begin( )
    {
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
            System.out.println(count + ". " + genreOption.GenreDescription);
            count++;
        }

        System.out.println();
        System.out.println( "[Q]uit: Quit game" ); // Handle user input
        boolean temp = true;
        while(temp){
//            userInputs.ProcessInput(sc.nextLine());
            String userGenre = sc.nextLine(); //handle user input
            List<QuizQuestion> questions = data.get(Integer.parseInt(userGenre)-1).GenreQuestions;
            countTotal = questions.size();
            System.out.println("============================================================================================");
            System.out.println( "Begin " +  data.get(Integer.parseInt(userGenre)-1).GenreDescription + " Quiz!");
            System.out.println("============================================================================================");

            for(QuizQuestion q: questions){
                System.out.println(q.getQuestion());
                System.out.println("A : " +q.getOptions()[0]);
                System.out.println("B : " +q.getOptions()[1]);
                System.out.println("C : " +q.getOptions()[2]);
                System.out.println("D : " +q.getOptions()[3]);

                // userInputs.ProcessInput(sc.nextLine()); //Handle user input
                String userAnswer = sc.nextLine();

                char ans;
                ans = userAnswer.charAt(0);

                String answer = "";

                switch(ans)
                {
                    case 'A':
                        answer = q.getOptions()[0];
                        break;
                    case 'B':
                        answer = q.getOptions()[1];
                        break;
                    case 'C':
                        answer = q.getOptions()[2];
                        break;
                    case 'D':
                        answer = q.getOptions()[3];
                        break;
                    default:break;
                }

                if(answer.equals(q.getAnswer()))
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
