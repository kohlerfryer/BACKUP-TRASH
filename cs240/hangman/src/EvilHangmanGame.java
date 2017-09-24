package src;
import java.util.Scanner;
import java.util.Set;
import java.util.File;


public class EvilHangmanGame implements IEvilHangmanGame {

	public EvilHangmanGame() {

	}

    //game state is stored in this function
    public void startGame(File dictionary, Integer wordLength, Integer guessLimit){
        Integer usedGuesses = 0;
        //initiate dictionary

        //begin game loop
        commenceGameLoop(usedGuesses, guessLimit);
    }

    private void commenceGameLoop(Integer usedGuesses, Integer guessLimit){
		Scanner scanner = new Scanner(System.in);
		String readString = scanner.nextLine();
		while(readString!=null) {
			System.out.println(readString);

			if (readString.isEmpty()) {
				System.out.println("Read Enter Key.");
			}

			if (scanner.hasNextLine()) {
				readString = scanner.nextLine();
			} else {
				readString = null;
			}
		}

		
    } 

    public String getGuessesText(){
        return "You have " + (guessQuantity - usedGuesses) + " guesses left";
    }

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{

    }




}