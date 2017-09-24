package src;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException{
		
		String dictionaryFileName = args[0];
		int wordLength = Integer.parseInt(args[1]);
		int guessQuantity = Integer.parseInt(args[2]);
		File inputFile = new File(dictionaryFileName);

		EvilHangmanGame evilHangmanGame = new EvilHangmanGame();
		evilHangmanGame.startGame(inputFile, wordLength, guessQuantity);		
	}

}
