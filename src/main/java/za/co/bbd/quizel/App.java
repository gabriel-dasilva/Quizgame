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
        Quizel quiz = new Quizel();
        quiz.begin();

    }

    
}
