package src;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class EvilHangmanGame implements IEvilHangmanGame {

	int guessMax = 0;
	int wordLength = 0;
	int wrongGuesses = 0;
	Set<Character> guessedLetters = new TreeSet<Character>();
	TreeSet<String> currentActiveWords = new TreeSet<String>();	
	HashMap<Integer, TreeSet<String>> binaryPartitionMap = new HashMap<Integer, TreeSet<String>>();
	PartitionPattern partitionPattern;

	public void startGame(File dictionary, int wordLength){
		this.wordLength = wordLength;
		this.partitionPattern = new PartitionPattern(wordLength);
		initializeCurrentActiveWords(dictionary, wordLength);
	}

	public void initiateGameLoop(int guessMax) throws GuessAlreadyMadeException{
		Scanner scanner = new Scanner(System.in);
		while(wrongGuesses < guessMax && partitionPattern.patternIncomplete()){
			System.out.println("You have " + (guessMax - wrongGuesses) + " guesses left");
			System.out.print("Guesses : ");
			for(char c : guessedLetters){
				System.out.print(" " + c);
			}
			System.out.println("Word : " + partitionPattern.toString());
			System.out.print("Enter Guess ");
			String input = scanner.next();
			if(!inputValid(input)){
				System.out.println("Invalid input \n");
				continue;
			}
			makeGuess(input.charAt(0));
		}

		if(partitionPattern.patternIncomplete()){
			System.out.println("You lose!");
		}else{
			System.out.println("You Win!");
		}

		System.out.println("The word was " + currentActiveWords.first());
	}

	public boolean inputValid(String input){
		Pattern p = Pattern.compile("[^a-z]");
		if(input.length() != 1){
			return false;
		}
		if(p.matcher(input).find()){
			return false;
		}
		return true;
	}

	public void initializeCurrentActiveWords(File dictionary, int wordLength){
		try{
			Scanner scanner = new Scanner(dictionary);
			while(scanner.hasNext()){
				String word = scanner.next();
				if(word.length() == wordLength) 
					currentActiveWords.add(word);
			}
		}catch(FileNotFoundException e){

		}
	}

	public TreeSet<String> getBestMatchFromPartitionMap(char guess){
		TreeSet<Integer> bestSetIndexes = new TreeSet<Integer>();
		for(HashMap.Entry<Integer, TreeSet<String>> iteration : binaryPartitionMap.entrySet()) {
			Set<String> iterSet = iteration.getValue();
			Integer iterKey = iteration.getKey();
			if(bestSetIndexes.size() == 0 ||  iterSet.size() > binaryPartitionMap.get(bestSetIndexes.first()).size()){
				bestSetIndexes.clear();
				bestSetIndexes.add(iterKey);
			}else if(iterSet.size() == binaryPartitionMap.get(bestSetIndexes.first()).size()){
				bestSetIndexes.add(iterKey);
			}
		}

		if(bestSetIndexes.size() > 1){
			for(HashMap.Entry<Integer, TreeSet<String>> iteration : binaryPartitionMap.entrySet()) {
				Integer iterKey = iteration.getKey();
				if(bestSetIndexes.size() == 0 ||  Integer.bitCount(iterKey) < Integer.bitCount(bestSetIndexes.first()) ){
					bestSetIndexes.clear();
					bestSetIndexes.add(iterKey);
				}else if(Integer.bitCount(iterKey) == Integer.bitCount(bestSetIndexes.first()) ){
					bestSetIndexes.add(iterKey);
				}	
			}
		}

		if(bestSetIndexes.size() > 1){
			for(HashMap.Entry<Integer, TreeSet<String>> iteration : binaryPartitionMap.entrySet()) {
				Integer iterKey = iteration.getKey();
				if(bestSetIndexes.size() == 0 ||  iterKey < bestSetIndexes.first()){
					bestSetIndexes.clear();
					bestSetIndexes.add(iterKey);
				}else if(iterKey == bestSetIndexes.first()){
					bestSetIndexes.add(iterKey);
				}
			}
		}

		if(bestSetIndexes.first() == 0) wrongGuesses++;
		partitionPattern.addPattern(bestSetIndexes.first(), guess);
		return binaryPartitionMap.get(bestSetIndexes.first());
	}

	public void addWordToBinaryPartitionMap(String word, char guess){
		char[] wordArray = word.toCharArray();
		String binaryRepString = "";
		for(char c : wordArray){
			if(c == guess) binaryRepString += "1";
			else binaryRepString += "0";
		}
		int binaryRepNumber = Integer.parseInt(binaryRepString, 2);

		binaryPartitionMap.putIfAbsent(binaryRepNumber, new TreeSet<String>());
		binaryPartitionMap.get(binaryRepNumber).add(word);
	}

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		guessedLetters.add(guess); 
		for(String word : currentActiveWords){
			addWordToBinaryPartitionMap(word, guess);
		}
		currentActiveWords = getBestMatchFromPartitionMap(guess);
		// for(String word : currentActiveWords){
		// 	System.out.println(word);
		// }
		binaryPartitionMap.clear();
		return currentActiveWords;
	}


}
