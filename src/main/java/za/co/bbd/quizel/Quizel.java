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
        List<Genre> data = JsonDataMapper.getAllData();
        Commands userInputs = new Commands(data);
        Scanner sc = new Scanner(System.in);


        // TODO: Handle user input

        System.out.println("============================================================================================");
        System.out.println( "                  Welcome to Quizel!" );
        System.out.println("------------------------------------------------------");
        userInputs.processInput("m");

        while(true){
            System.out.println( "[M]enu: Go to menu                  [Q]uit: Quit game" );
            userInputs.processInput(sc.nextLine()); //handle user input
        }
    }
}
