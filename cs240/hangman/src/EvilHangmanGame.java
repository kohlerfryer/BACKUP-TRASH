package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.regex.Pattern;


public class EvilHangmanGame implements IEvilHangmanGame {

	TreeSet<String> possibleWords = new TreeSet<String>();
	HashMap<Integer, TreeSet<String>> wordBitMap = new  HashMap<Integer, TreeSet<String>>();
	Set<Character> guessedLetters = new TreeSet<Character>();
	PartitionPattern partitionPattern;
	Integer gameWordLength;
	Integer guessLimit;
	Integer usedGuesses = 0;

    public void startGame(File dictionary, int wordLength){
		this.gameWordLength = wordLength;
		this.partitionPattern = new PartitionPattern(gameWordLength);
		this.initializePossibleWords(dictionary);
    }

    public void commenceGameLoop(int guessLimit){
		this.guessLimit = guessLimit;
		Scanner scanner = new Scanner(System.in);
		String input;
		this.printGuessesLeftText();
		while( (guessLimit - usedGuesses) > 0 && !partitionPattern.complete()) {
			System.out.print("Enter guess: ");
			input = scanner.next();
			if(!this.validateInput(input))continue;

			try{
				Character guessedLetter = input.charAt(0);
				if(!guessedLetters.add(guessedLetter))throw new GuessAlreadyMadeException();
				this.makeGuess(input.charAt(0));
				this.printGuessesLeftText();
			}catch(GuessAlreadyMadeException e){
				System.out.println("You already used that letter");
			}
		}
		handleGameEnd();
    }

	public boolean validateInput(String input){
		Pattern p = Pattern.compile("[^a-z]");
		if (input.isEmpty()) {
			System.out.println("You didn't enter anything");
			return false;
		}
		if(p.matcher(input).find()){
			System.out.println("Invalid Input");
			return false;
		}
		else if(input.length() > 1) {
			System.out.println("Invalid Input");
			return false;
		}
		return true;
	}

	public void initializePossibleWords(File file){
		try{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				String word = scanner.next();
				if(word.length() == gameWordLength)
				possibleWords.add(word);
			}
		}catch(FileNotFoundException e){
			System.out.println("Invalid file input");
		}

	}

	public void partitionWordsToBitMap(String word, Character identifiedChar){
		StringBuilder sb = new StringBuilder();
		for(char c : word.toCharArray()){
			if (c == identifiedChar)
				sb.append(1);
			else
				sb.append(0);
		}
		Integer binaryWordRep = Integer.parseInt(sb.toString(), 2);
		if(wordBitMap.get(binaryWordRep) == null){
			wordBitMap.put(binaryWordRep, new TreeSet<String>());
		}
		//System.out.println("char: " + identifiedChar + " word: " + word + " bitstring: " + sb.toString() + " bitNum: " + binaryWordRep + " mapsize: " + wordBitMap.size());
		wordBitMap.get(binaryWordRep).add(word);
		//System.out.println(wordBitMap.get(binaryWordRep).size());
	}

    public void printGuessesLeftText(){
		int guessesLeft = guessLimit - guessedLetters.size();
		StringBuilder sb = new StringBuilder();
		if(guessesLeft != 0){
			sb.append("You have " + (guessLimit - usedGuesses) );
			sb.append(guessesLeft > 1 ? " guesses left" : " guess left");
			sb.append("\nUsed letters:");
			for(Character c : this.guessedLetters){
				sb.append(" " + c);
			}
			sb.append("\n");
			sb.append("Word: ");
			sb.append(partitionPattern.toString());
		}
		System.out.println(sb.toString());
    }

	public void printWordBitMap(){
		print("\nprinting curent bitmap");
		for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
			Set<String> wordSet = iteration.getValue();
			int key = iteration.getKey();
			System.out.print(Integer.toBinaryString(key) + " : ");
			for(String word : wordSet){
				System.out.print(" " + word);
			}
		}
		print("");
	}

	public TreeSet<String> getSetWithBestPartition(char guess){
		TreeSet<Integer> bestPartitionKeySet = new TreeSet<Integer>();
		TreeSet<String> newPossibleWords;
		TreeSet<Integer> keysForDeletion = new TreeSet<Integer>();
		//get lowest bit count
		//if two numbers exist in set at the end of comparison
		//a unique set cannot be determined and next loop must run
		
		//get list most words
		int biggestWordCount = 0;
		for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
			Set<String> wordSet = iteration.getValue();
			if(wordSet.size() > biggestWordCount)
				biggestWordCount = wordSet.size();
		}
		for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
			int key = iteration.getKey();
			Set<String> wordSet = iteration.getValue();
			if(wordSet.size() == biggestWordCount)
				bestPartitionKeySet.add(key);
			else
				keysForDeletion.add(key);
		}
		for(int key : keysForDeletion){
			wordBitMap.remove(key);
		}
		//if more than one group remains -- first find least amount of letters in bitmap
		//if this doesnt narrow anytihng down find the least quanitit of bitmap values
		//narrow down search
		if(bestPartitionKeySet.size() > 1){
			//print("getting least letters");
			for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
				int key = iteration.getKey();
			    if(Integer.bitCount(key) < Integer.bitCount(bestPartitionKeySet.first())){
					//print(key + "<" + bestPartitionKeySet.first());
					bestPartitionKeySet.clear();
					bestPartitionKeySet.add(key);
				}else if(Integer.bitCount(key) == Integer.bitCount(bestPartitionKeySet.first())){
					//print(Integer.bitCount(key) + "==" + Integer.bitCount(bestPartitionKeySet.first()));
					bestPartitionKeySet.add(key);			
				}
			}
		}

		if(bestPartitionKeySet.size() > 1){
			for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
				int key = iteration.getKey();
				if(key < bestPartitionKeySet.first()){
					//print(key + "<" + bestPartitionKeySet.first());
					bestPartitionKeySet.clear();
					bestPartitionKeySet.add(key);
				}
			}
		}

		newPossibleWords = new TreeSet<String>(wordBitMap.get(bestPartitionKeySet.first()));
		partitionPattern.addToPattern(bestPartitionKeySet.first(), guess);
		printResultMessage(bestPartitionKeySet.first(), guess);
		increaseGuesses(bestPartitionKeySet.first());
		wordBitMap.clear();
		return newPossibleWords;

	//add to partitionPattern
	//if best found is 0001 (---E) then add to partitionPattern
	//make sure to clear bitmap and return new possible word set
	}

	private void handleGameEnd(){
		if(partitionPattern.complete()){
			print("You win!");
		}
		else{
			print("You Lose!");
			print("The word was: " + possibleWords.first());
		}

	}

	private void increaseGuesses(int bitKey){
		if(Integer.bitCount(bitKey) < 1)usedGuesses++;
	}

	private void printResultMessage(int bitKey, char guess){
		String message = "";
		int bitCount = Integer.bitCount(bitKey);
		if(bitCount== 0){
			message = "Sorry, there are no "+guess+"'s";
		}else{
			message = bitCount > 1 ? "Yes, there are " + bitCount + " " + guess + "'s"   : "Yes, there is " + bitCount + " " + guess;
		}
		print(message + "\n");
	}

	//private void addToPattern();

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		//find all words with curent guesses
		for(String word : possibleWords){
			partitionWordsToBitMap(word, guess);
		}
		possibleWords = getSetWithBestPartition(guess);
		//change possible words
		return possibleWords;
    }

	public void print(String s){
		System.out.println(s);
	}




}
