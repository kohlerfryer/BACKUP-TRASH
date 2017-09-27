package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.util.HashMap;



public class EvilHangmanGame implements IEvilHangmanGame {

	Set<String> possibleWords = new TreeSet<String>();
	HashMap<Integer, Set<String>> wordBitMap = new  HashMap<Integer, Set<String>>();
	Set<Character> guessedLetters = new TreeSet<Character>();
	Pattern pattern;
	Integer gameWordLength;
	Integer guessLimit;

	//todo un-change abstract file
    public void startGame(File dictionary, Integer gameWordLength, Integer guessLimit){
		this.gameWordLength = gameWordLength;
		this.guessLimit = guessLimit;
		this.pattern = new Pattern(gameWordLength);
		this.initializePossibleWords(dictionary);
		this.printGuessesLeftText();
        commenceGameLoop();
    }

    private void commenceGameLoop(){
		Scanner scanner = new Scanner(System.in);
		String input;
		while(guessedLetters.size() < guessLimit) {
			input = scanner.next();
			if(!this.validateInput(input))continue;

			try{
				Character guessedLetter = input.charAt(0);
				if(!guessedLetters.add(guessedLetter))throw new GuessAlreadyMadeException();
				this.makeGuess(input.charAt(0));
				this.printGuessesLeftText();
			}catch(GuessAlreadyMadeException e){
				System.out.println("You already guessed this");
			}
		}
    }

	public boolean validateInput(String input){
		if (input.isEmpty()) {
			System.out.println("You didn't enter anything");
			return false;
		}
		if(input.length() > 1) {
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
		if(guessesLeft == 0){
			sb.append("game over");
		}
		else{
			sb.append("You have " + (guessLimit - guessedLetters.size()));
			sb.append(guessesLeft > 1 ? " guesses left" : " guess left");
			sb.append("\nUsed letters:");
			for(Character c : this.guessedLetters){
				sb.append(" " + c);
			}
			sb.append("\n");
			sb.append("Word: ");
			sb.append(pattern.toString());
		}
		System.out.println(sb.toString());
    }

	public Set<String> getSetWithBestPartition(char guess){
		// 1. Choose the group in which the letter does not appear at all.
		// 2. If each group has the guessed letter, choose the one with the
		// fewest letters.
		//****lowest bit count
		// 3. If this still has not resolved the issue, choose the one with the
		// rightmost guessed letter.
		//****lowest bit value
		// 4. If there is still more than one group, choose the one with the next
		// rightmost letter. Repeat this step (step 4) until a group is
		// chosen.

		TreeSet<Integer> bestPartitionKeySet = new TreeSet<Integer>();
		Set<String> newPossibleWords;
		//get lowest bit count
		//if two numbers exist in set at the end of comparison
		//a unique set cannot be determined and next loop must run
		
		//get list most words
		for(HashMap.Entry<Integer, Set<String>> iteration : wordBitMap.entrySet()) {
			int key = iteration.getKey();
			Set<String> wordSet = iteration.getValue();
			if(bestPartitionKeySet.isEmpty()){
				bestPartitionKeySet.add(key);
			}else if(Integer.bitCount(key) < Integer.bitCount(bestPartitionKeySet.first())){
				print(key + "<" + bestPartitionKeySet.first());
				bestPartitionKeySet.clear();
				bestPartitionKeySet.add(key);
			}else if(Integer.bitCount(key) == Integer.bitCount(bestPartitionKeySet.first())){
				print(Integer.bitCount(key) + "==" + Integer.bitCount(bestPartitionKeySet.first()));
				bestPartitionKeySet.add(key);			
			}
		}
		// System.out.print("compare" + Integer.bitCount(key) + " : " + Integer.bitCount(bestPartitionKey));
		//get lowest bit value

		//narrow down search
		if(bestPartitionKeySet.size() > 1){
			for(){
				int key = iteration.getKey();
				if(bestPartitionKeySet.isEmpty()){
					bestPartitionKeySet.add(key);
				}else if(Integer.bitCount(key) < Integer.bitCount(bestPartitionKeySet.first())){
					print(key + "<" + bestPartitionKeySet.first());
					bestPartitionKeySet.clear();
					bestPartitionKeySet.add(key);
				}else if(Integer.bitCount(key) == Integer.bitCount(bestPartitionKeySet.first())){
					print(Integer.bitCount(key) + "==" + Integer.bitCount(bestPartitionKeySet.first()));
					bestPartitionKeySet.add(key);			
				}
			}
			// print("the choice set was a duplicate :(");
			// for(HashMap.Entry<Integer, Set<String>> iteration : wordBitMap.entrySet()) {
			// 	int key = iteration.getKey();
			// 	if(bestPartitionKeySet.isEmpty()){
			// 		bestPartitionKeySet.add(key);
			// 	}else if(key < bestPartitionKeySet.first()){
			// 		print(key + "<" + bestPartitionKeySet.first());
			// 		bestPartitionKeySet.clear();
			// 		bestPartitionKeySet.add(key);	
			// 	}
			// }
		}

		newPossibleWords = wordBitMap.get(bestPartitionKeySet.first());
		pattern.addToPattern(bestPartitionKeySet.first(), guess);
		wordBitMap.clear();
		return newPossibleWords;

	//add to pattern
	//if best found is 0001 (---E) then add to pattern
	//make sure to clear bitmap and return new possible word set
	}

	//private void addToPattern();

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		//find all words with curent guesses
		for(String word : possibleWords){
			partitionWordsToBitMap(word, guess);
		}
		possibleWords = getSetWithBestPartition(guess);
		//change possible words
		return null;
    }

	public void print(String s){
		System.out.println(s);
	}




}
