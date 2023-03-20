package za.co.bbd.quizel;

        import java.util.List;
import java.util.Scanner;

import za.co.bbd.quizel.utils.JsonDataMapper;

public class Quizel
{   
    void begin( String[] args )
    {
        // TODO: Start game loop
        Commands userInputs = new Commands();
        System.out.println( "Welcome to quizel!" );
        Scanner sc = new Scanner(System.in);

        // TODO: Uncomment this when loop conditions are established.
        // while(true){
        //     userInputs.ProcessInput(sc.nextLine());
        // }

        
        List<Genre> data = JsonDataMapper.getAllData();
        
        System.out.println("------------------------------------------------------");
        System.out.println( "Choose a category!" );
        System.out.println("------------------------------------------------------");
        for (Genre genreOption : data){
            System.out.println(genreOption.GenreDescription);
        }
        
        // TODO: handle user input
        String userGenre = "";
        // NEED TO UNDESTAND HOW TO USE THE DATA MAPPER TO VERIFY IF IT WORKS
        List<QuizQuestion> questions = data.get(data.indexOf(userGenre)).GenreQuestions;
        for(QuizQuestion q: questions){

            String answer = "";
            Scanner userAnswer = new Scanner(System.in);

            char ans;
            ans = userAnswer.next().charAt(0);

            switch(ans)
            {
                case 'a':
                    answer = q.getOptions()[0];
                    break;
                case 'b':
                    answer = q.getOptions()[1];
                    break;
                case 'c':
                    answer = q.getOptions()[2];
                    break;
                case 'd':
                    answer = q.getOptions()[3];
                    break;
                default:break;
            }

            if(answer.equals(q.getAnswer()))
            {
                System.out.println("------------------------------------------------------");
                System.out.println("                  Correct Answer                      ");
                System.out.println("------------------------------------------------------");
     
            }
            else
            {
                System.out.println("------------------------------------------------------");
                System.out.println("                  Wrong Answer                      ");
                System.out.println("------------------------------------------------------");
            }
            System.out.println("============================================================================================");
        }
        
    }
}
