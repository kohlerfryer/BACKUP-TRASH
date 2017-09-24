package src;

import java.io.IOException;
import java.util.Scanner;
/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args){
		
		String dictionaryFileName = args[0];
		int wordLength = Integer.parseInt(args[1]);
		int guessQuantity = Integer.parseInt(args[2]);

		EvilHangmanGame evilHangmanGame = new EvilHangmanGame();
		System.out.println(gameBoard.getGuessesText());
		
	}

}
