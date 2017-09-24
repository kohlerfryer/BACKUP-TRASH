package src;
/**
 * 
 * 
 */
public class GameBoard {
	
	/**
	 * 
	 * 
	 */

    private String dictionaryFileName;
    private int wordLength;
    private int guessQuantity;
    private int usedGuesses;

	public GameBoard(String dictionaryFileName, int wordLength, int guessQuantity) {
        this.dictionaryFileName = dictionaryFileName;
        this.wordLength = wordLength;
        this.guessQuantity = guessQuantity;
        this.usedGuesses = 0;
	}

    public void submitGuess(String guess){

    }

    public String getGuessesText(){
        return "You have " + (guessQuantity - usedGuesses) + " guesses left";
    }

}
