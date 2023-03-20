package za.co.bbd.quizel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.Arrays;
import java.util.Scanner;

public class Quizel
{
    private static final Logger log = LoggerFactory.getLogger(Quizel.class);

    public static void main( String[] args )
    {
        Commands userInputs = new Commands();
        System.out.println( "Welcome to quizel!" );
        log.info(Arrays.toString(JsonDataMapper.getAllData().toArray()));
        Scanner sc = new Scanner(System.in);

        while(true){
            Commands.ProcessInput(sc.nextLine());
        }
    }
}
