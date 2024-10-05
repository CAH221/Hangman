//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: This class represents the Hangman   *//
//*              game logic, including managing      *//
//*              the list of guessed letters,        *//
//*              selecting random words, and         *//
//*              displaying game progress.           *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    // Variables for game state
    private List<Character> guessedLetters = new ArrayList<>();
    private int wrongGuesses = 0;
    private String secretWord;

    // List of five-letter words
    private static final String[] wordList = {
            "seven", "world", "about", "again", "heart", "pizza", "water",
            "happy", "sixty", "board", "month", "angel", "death", "green",
            "music", "fifty", "three", "party", "piano", "mouth", "woman",
            "sugar", "amber", "dream", "apple", "laugh", "tiger", "faith",
            "earth", "river", "money", "peace", "forty", "words", "smile",
            "house", "alone", "watch", "lemon", "south", "anime", "after",
            "santa", "women", "china"
    };

    // Scaffolds for display
    private static final String[] emptyScaffold = {
            "     _______",
            "    |/",
            "    |",
            "    |",
            "    |",
            "    |",
            "    |",
            " ___|___"
    };

    private static final String[] fullScaffold = {
            "     _______",
            "    |/      |",
            "    |      (_)",
            "    |      \\|/",
            "    |       |",
            "    |      / \\",
            "    |",
            " ___|___"
    };

    // Constructor: initialize the game and pick a random word
    public Hangman() {
        Random random = new Random();
        secretWord = wordList[random.nextInt(wordList.length)];
    }

    // Method to display the scaffold based on number of wrong guesses
    public String hangmanDisplay() {
        StringBuilder display = new StringBuilder();

        // Add parts of the scaffold based on wrong guesses
        for (int i = 0; i <= wrongGuesses && i < fullScaffold.length; i++) {
            display.append(fullScaffold[i]).append("\n");
        }

        // Add remaining parts of the empty scaffold
        for (int i = wrongGuesses + 2; i < emptyScaffold.length; i++) {
            display.append(emptyScaffold[i]).append("\n");
        }

        return display.toString();
    }

    // Method to display the current word with underscores for letters not guessed
    public String wordDisplay() {
        StringBuilder display = new StringBuilder();

        // Loop through secret word
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(" ").append(c);
            } else {
                display.append(" _");
            }
        }
        return display.toString();
    }

    // Check if the player has won by guessing all letters
    public boolean checkForWin() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Check if the player has lost by reaching the maximum wrong guesses
    public boolean checkForLoss() {
        return wrongGuesses >= 5;
    }

    // Method to process a guess from the player
    public boolean guessLetter(char guess) {
        if (guessedLetters.contains(guess)) {
            return false; // Already guessed this letter
        }

        guessedLetters.add(guess);

        if (secretWord.contains(String.valueOf(guess))) {
            return true; // Correct guess
        } else {
            wrongGuesses++; // Wrong guess, increment wrong guesses
            return false;
        }
    }

    // Getter for wrong guesses
    public int getWrongGuesses() {
        return wrongGuesses;
    }

    // Getter for secret word (used when game ends)
    public String getSecretWord() {
        return secretWord;
    }

    // Introduction text
    public static String getIntroduction() {
        return """
        *******************************
        * This is the classic Hangman *
        * game. You have to guess all *
        * the letters in a five       *
        * letter word.                *
        *                             *
        * You only get five wrong     *
        * guesses so make every guess *
        * count.                      *
        *******************************
        """;
    }
}
