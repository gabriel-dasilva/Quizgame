package za.co.bbd.quizel;

import java.io.FileReader;
// import java.io.FileReader;/
import java.io.IOException;
import java.io.Reader;
// import java.io.Reader;
import java.util.Scanner;

import com.google.gson.Gson;


public class App 
{
    public static void main( String[] args )
    {
        /* ToDO - Use file reading class to read genre's into an array of Genre class objects. The Genre class will have
         * functionality that will read in the questions for each genre.
         */


        System.out.println("------------------------------------------------------");
        System.out.println( "Welcome to quizel!" );
        System.out.println("------------------------------------------------------");
        System.out.println( "Choose a category!" );
        System.out.println("------------------------------------------------------");
        // TODO: read availble genres from json file and dispaly them to player

        // TODO: give player option to mix genres
        // select number of questions they want to take
        // choose prefered question type, betwen true/false & multiple choice
        // API 
        
        String[]  genres = {"Math", "Play Station"};
        for (String genreOption : genres){
            System.out.println(genreOption);
        }

        // take user input
        Scanner userInput = new Scanner(System.in);
        String userGenre = userInput.nextLine();

        Genre genre = new Genre(userGenre);

        QuizQuestion[] questions = genre.GenreQuestions;

        // score keeper 
        // timer potentially
        for(QuizQuestion q: questions){
            System.out.println(q.getQuestion());
            
            
            String answer = "";
            Scanner userAnswer = new Scanner(System.in);

            char ans;
            ans = userAnswer.next().charAt(0);

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


            

        }



    }

    
}
