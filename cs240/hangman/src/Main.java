package src;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;


public class Main {
	public static void main(String[] args) throws IOException{

		// String[][] a = new String[3][3];
		// System.out.println(a[0] == null);

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
		try{
			evilHangmanGame.commenceGameLoop(guessQuantity);

		}catch(IEvilHangmanGame.GuessAlreadyMadeException e){
			System.out.println("You already used that letter");
		}
	
	}

}
