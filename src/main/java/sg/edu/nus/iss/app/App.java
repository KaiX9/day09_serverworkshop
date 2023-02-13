package sg.edu.nus.iss.app;

import java.util.Random;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        
        Random random = new Random();

        // generate random number between 0 to 99
        Integer randomNumber = random.nextInt(100);

        // store my guess
        Integer myGuess = 0;

        // expect input from keyboard
        // convert to expert from inputStream if it's a socket app
        Scanner scanner = new Scanner(System.in);

        // allow user to guess until the random number is guessed correctly
        while (myGuess != randomNumber) {
            System.out.println("Enter your guess: ");
            myGuess = scanner.nextInt();

            if (myGuess < randomNumber) {
                System.out.println("Your guessed number is lower.");
            } else if (myGuess > randomNumber) {
                System.out.println("Your guessed number is higher.");
            } else {
                System.out.println("You have finally guessed it right!");
                scanner.close();
            }
        }
    }
}
