package src;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;

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

		// System.out.println(Integer.toBinaryString(1));
		// System.out.println(Integer.parseInt("100", 2));

		if(args.length != 3){
			System.out.println("Invalid Input");
			System.exit(0);
		}

		Pattern p = Pattern.compile("[^0-9]");
		if(p.matcher(args[1]).find() || p.matcher(args[2]).find()){
			System.out.println("Invalid Input");
			System.exit(0);
		}

		String dictionaryFileName = args[0];
		int wordLength = Integer.parseInt(args[1]);
		int guessQuantity = Integer.parseInt(args[2]);
		File inputFile = new File(dictionaryFileName);

		EvilHangmanGame evilHangmanGame = new EvilHangmanGame();
		evilHangmanGame.startGame(inputFile, wordLength);
		evilHangmanGame.setRestOfGame(guessQuantity);
		evilHangmanGame.guessLimit = guessQuantity;
		evilHangmanGame.commenceGameLoop();
	
	}

}
