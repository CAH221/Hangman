//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: Main class for the Hangman game,    *//
//*              includes the game loop for user     *//
//*              interaction, processing guesses,    *//
//*              and determining win/loss outcomes.  *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a new game instance
        Hangman game = new Hangman();

        // Print the introductory text
        System.out.println(Hangman.getIntroduction());

        // Show the initial scaffold and word display
        System.out.println(game.hangmanDisplay());
        System.out.println(game.wordDisplay());

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Game loop
        while (true) {
            // Check if player has won
            if (game.checkForWin()) {
                System.out.println("Congratulations! You guessed the word: " + game.getSecretWord());
                break;
            }

            // Check if player has lost
            if (game.checkForLoss()) {
                System.out.println("You have lost. The word was: " + game.getSecretWord());
                break;
            }

            // Ask the player for a new guess
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine();

            // Ensure that the input is a single letter
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a valid single letter.");
                continue;
            }

            char guess = Character.toLowerCase(input.charAt(0));

            // Process the guess
            if (game.guessLetter(guess)) {
                System.out.println("Good guess! The letter '" + guess + "' is in the word.");
            } else {
                System.out.println("Oops! The letter '" + guess + "' is not in the word.");
            }

            // Show the current scaffold and word display
            System.out.println(game.hangmanDisplay());
            System.out.println(game.wordDisplay());
        }

        // Close scanner
        scanner.close();

        // End of game message
        System.out.println("\n***** GAME OVER *****");
    }
}
