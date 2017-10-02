package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.regex.Pattern;


public class EvilHangmanGame implements IEvilHangmanGame {

	String[] possibleWords;
	String[][] bitMappedDictionary;
	//HashMap<Integer, TreeSet<String>> wordBitMap = new  HashMap<Integer, TreeSet<String>>();
	Set<Character> guessedLetters = new TreeSet<Character>();
	PartitionPattern partitionPattern;
	Integer gameWordLength;
	Integer guessLimit;
	Integer usedGuesses = 0;
	int arrayLength;

    public void startGame(File dictionary, int wordLength){
		arrayLength = (int)Math.pow(wordLength, wordLength+1);
		bitMappedDictionary = new String[arrayLength][arrayLength];
		possibleWords = new String[arrayLength];
		System.out.println(arrayLength);
		this.gameWordLength = wordLength;
		this.partitionPattern = new PartitionPattern(gameWordLength);
		this.initializePossibleWords(dictionary);
    }

    public void commenceGameLoop(int guessLimit) throws  GuessAlreadyMadeException{
		this.guessLimit = guessLimit;
		Scanner scanner = new Scanner(System.in);
		String input;
		this.printGuessesLeftText();
		while(GameHasNotEnded()) {
			System.out.print("Enter guess: ");
			input = scanner.next();

			if(this.isValidInput(input) == false)
				continue;
	
			Character guessedLetter = input.charAt(0);
			if(!guessedLetters.add(guessedLetter))throw new GuessAlreadyMadeException();
			this.makeGuess(input.charAt(0));
			this.printGuessesLeftText();
			System.out.println("You already used that letter");
			
		}
		printEndGameMessage();

    }

	private boolean GameHasNotEnded(){
		return (guessLimit - usedGuesses) > 0 && partitionPattern.incomplete();
	}

	public boolean isValidInput(String input){
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
			int i = 0;
			while(scanner.hasNext()){
				String word = scanner.next();
				if(word.length() == gameWordLength)
				possibleWords[i++] = word;
				// System.out.println(word);
			}
		}catch(FileNotFoundException e){
			System.out.println("Invalid file input");
		}

	}

	public int getArraysNextUnusedIndex(Object[] arr){
		int i = 0;
		for(int j = 0; j < arr.length; j++){
			if(arr[j] == null)return j;
		}
		return i;
	}

	public void partitionWordsToBitmapDictionary(String word, Character identifiedChar){
		StringBuilder sb = new StringBuilder();
		for(char c : word.toCharArray()){
			if (c == identifiedChar)
				sb.append(1);
			else
				sb.append(0);
		}
		Integer binaryWordRep = Integer.parseInt(sb.toString(), 2);
		int secondIndex =  getArraysNextUnusedIndex(bitMappedDictionary[binaryWordRep]);
		// bitMappedDictionary[binaryWordRep].length;
		bitMappedDictionary[binaryWordRep][secondIndex] = word;
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
		for(int i = 0; i < getBitMappedDictionaryNonNullLength(); i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			//System.out.print(Integer.toBinaryString(i) + " : ");
			for(int j = 0; j < getArraysNextUnusedIndex(bitMappedDictionary[i]); j++) {
				System.out.print("*" + bitMappedDictionary[i][j]);
			}
		}
		print("");
	}

	//rightMostLetter will be smallest index
	private void removeAllGroupWithLeftMostLetters(){
		int biggestWordCount = 0;
		//get biggest set size
		for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			removeAllButOneIndexesFromBinaryDicionary(i);
			return;
			// if(bitMappedDictionary[i].size() > biggestWordCount)
			// 	biggestWordCount = bitMappedDictionary[i].size();
		}
		//delete all sets that are not the biggest or equal to the biggest
		// for(int i = 0; i < bitMappedDictionary.length-1; i++) {
		// 	if(bitMappedDictionary[i] == null)continue;
		// 	if((bitMappedDictionary[i].size() != biggestWordCount)
		// 		bitMappedDictionary[i] = null;
		// }
	}



	//count the ammount of 1's which represent
	//the guessed letter and if a set can be found
	//that the least amount of 1s in entire dinctionary
	//delete all other sets
	private void removeAllGroupsWithoutFewestGuessedLetters(){
		//get lowest letter guess index
		int lowestLetterGuessIndex = 100;
		int lowestLetterGuessIndexSetCount = 0;
		for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			if(Integer.bitCount(i) < lowestLetterGuessIndex)
				lowestLetterGuessIndex = Integer.bitCount(i);
		}
		//count how many sets exist with such guess index
		for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			if(Integer.bitCount(i) == lowestLetterGuessIndex)
				lowestLetterGuessIndexSetCount += 1;
		}
		//if only one set exists with that number, delete all others
		for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			if(Integer.bitCount(i) == lowestLetterGuessIndex)
				removeAllButOneIndexesFromBinaryDicionary(i);
		}
	}

	private void removeAllButOneIndexesFromBinaryDicionary(int desiredIndex){
			for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
			if(i != desiredIndex)
				bitMappedDictionary[i] = new String[arrayLength];
		}	
	}

	// private void getBitMappedDictionaryNonNullLength(){
	// 	for(HashMap.Entry<Integer, TreeSet<String>> iteration : wordBitMap.entrySet()) {
	// 		int key = iteration.getKey();
	// 		if(key < bestPartitionKeySet.first()){
	// 			bestPartitionKeySet.clear();
	// 			bestPartitionKeySet.add(key);
	// 		}
	// 	}
	// }

	private int getBitMappedDictionaryNonNullLength(){
		int count = 0;
		for (String[] wordGroup : bitMappedDictionary) {
			if ( wordGroup != null ) count++;
		}
		return count;
  	}

	public int getFirstNonNullDictionarySetIndex(){
		for(int i = 0; i < bitMappedDictionary.length-1; i++) {
			if(bitMappedDictionary[i][0] == null)continue;
				return i;
		}
		return 0;
	}

	public void partitionDictionaryAndSetNewPossibleWords(char guess){

		printWordBitMap();
		removeAllGroupsWithoutFewestGuessedLetters();
		if(getBitMappedDictionaryNonNullLength() > 1){
			removeAllGroupsWithoutFewestGuessedLetters();
		}
		if(getBitMappedDictionaryNonNullLength() > 1){
			removeAllGroupWithLeftMostLetters();
		}

		int newPossibleWordsIndex = getFirstNonNullDictionarySetIndex();
		partitionPattern.addToPattern(bitMappedDictionary[newPossibleWordsIndex], guess);
		printResultMessage(newPossibleWordsIndex, guess);
		increaseGuesses(newPossibleWordsIndex);
		possibleWords = bitMappedDictionary[newPossibleWordsIndex];
		bitMappedDictionary = new String[arrayLength][arrayLength];
	}

	private void printEndGameMessage(){
		if(partitionPattern.incomplete() == true){
			print("You win!");
		}
		else{
			print("You Lose!");
			print("The word was: " + possibleWords[0]);
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

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
		Set<String> pointlessSet = new TreeSet<String>();
		for(int i = 0; i < getArraysNextUnusedIndex(possibleWords); i++){
			partitionWordsToBitmapDictionary(possibleWords[i], guess);
		}
		partitionDictionaryAndSetNewPossibleWords(guess);
		for(int i = 0; i < getArraysNextUnusedIndex(possibleWords); i++){
			pointlessSet.add(possibleWords[i]);
		}
		return pointlessSet;
    }

	public void print(String s){
		System.out.println(s);
	}




}
