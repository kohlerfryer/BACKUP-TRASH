package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;


public class EvilHangmanGame implements IEvilHangmanGame {

	public EvilHangmanGame() {

	}

    //game state is stored in this function
	//todo un-change abstract file
    public void startGame(File dictionary, Integer wordLength, Integer guessLimit){
		// Set<String> words = this.getWords(dictionary);
		// File inputFile = new File(dictionaryFileName);
		// Scanner scanner = new Scanner(inputFile);

        Integer usedGuesses = 0;
		System.out.println(this.getGuessesText(guessLimit, usedGuesses));
        //initiate dictionary

        //begin game loop
        commenceGameLoop(usedGuesses, guessLimit);


    }

    private void commenceGameLoop(Integer usedGuesses, Integer guessLimit){
		Scanner scanner = new Scanner(System.in);
		String input;
		while(usedGuesses < guessLimit) {
			input = scanner.nextLine();
			if (input.isEmpty()) {
				System.out.println("You didn't enter anything'");
			}
			usedGuesses++;
		}

    } 

    public String getGuessesText(Integer guessLimit, Integer usedGuesses){
        return "You have " + (guessLimit - usedGuesses) + " guesses left";
    }

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		return null;
    }




}