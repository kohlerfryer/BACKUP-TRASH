package src;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {
	public static void main(String[] args){

		Pattern notNumberPattern = Pattern.compile("[^0-9]");
		if(args.length != 3){
			System.exit(0);
		}else if(notNumberPattern.matcher(args[1]).find()){
			System.exit(0);
		}else if(notNumberPattern.matcher(args[2]).find()){
			System.exit(0);
		}

		String dictionaryPath = args[0];
		int wordLength = Integer.parseInt(args[1]);
		int numberOfGuesses = Integer.parseInt(args[2]);
		File inputFile = new File(dictionaryPath);
		
		EvilHangmanGame game = new EvilHangmanGame();
		game.startGame(inputFile, wordLength);
		try{
			game.initiateGameLoop(numberOfGuesses);
		}catch(IEvilHangmanGame.GuessAlreadyMadeException e){
			System.out.println("you already guessed that");
		}
	
	}
}
