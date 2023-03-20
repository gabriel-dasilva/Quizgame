package za.co.bbd.quizel;

import java.util.Scanner;

public class Quizel
{
    public static void main( String[] args )
    {
        // TODO: Start game loop
        Commands userInputs = new Commands();
        System.out.println( "Welcome to quizel!" );
        Scanner sc = new Scanner(System.in);

        while(true){
            userInputs.ProcessInput(sc.nextLine());
        }
    }
}
