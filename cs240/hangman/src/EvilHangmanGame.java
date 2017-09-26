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
    public void startGame(File dictionary, Integer wordLength, Integer guessLimit){
		this.wordLength = wordLength;
		this.guessLimit = guessLimit;
		this.pattern = new Pattern(wordLength);
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
				if(scanner.next().length == gameWordLength.length)
				possibleWords.add(scanner.next());
			}
		}catch(FileNotFoundException e){
			System.out.println("Invalid file input");
		}
	}

	public void partitionWordsToBitMap(String word, Character identifiedChar){
		StringBuilder sb = new StringBuilder();
		for(char c : word.toCharArray){
			if (c == identifiedChar) 
				sb.append(1);
			else
				sb.append(0);
		}
		Integer binaryWordRep = Integer.parseInt(sb.toString(), 2);
		if(wordBitMap.get(binaryWordRep) == null){
			wordBitMap.put(binaryWordRep, new TreeSet<String>());
		}
		wordBitMap.get(binaryWordRep).add(word);
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
		}
		System.out.println(sb.toString());
    }

	public Set<String> getSetWithBestPartition(){
		// 1. Choose the group in which the letter does not appear at all.
		// 2. If each group has the guessed letter, choose the one with the
		// fewest letters.
		// 3. If this still has not resolved the issue, choose the one with the
		// rightmost guessed letter.
		// 4. If there is still more than one group, choose the one with the next
		// rightmost letter. Repeat this step (step 4) until a group is
		// chosen.
		Integer binaryKey;
		Set<String> newPossibleWords;
		if(wordBitMap.containsValue(0)){
			binaryKey = 0;
			newPossibleWords = wordBitMap.get(0);
		}else if(){
			loopthatSHiz
		}else if(){
			loopthatshiz
		}

		pattern.add(Integer.toBinaryString(binaryKey));
		wordBitMap.clear();
		return newPossibleWords;

	//add to pattern
	//if best found is 0001 (---E) then add to pattern
	//make sure to clear bitmap and return new possible word set
	}

	private void addToPattern()

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		//find all words with curent guesses
		for(String word : possibleWords){
			partitionWordsToBitMap(guess, word);
		}
		possibleWords = getSetWithBestPartition();
		//change possible words
		return null;
    }




}