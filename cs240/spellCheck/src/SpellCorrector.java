package src;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class SpellCorrector implements ISpellCorrector{
	
    /**
	 * Tells this <code>SpellCorrector</code> to use the given file as its dictionary
	 * for generating suggestions.
	 * @param dictionaryFileName File containing the words to be used
	 * @throws IOException If the file cannot be read
	 */
	public void useDictionary(String dictionaryFileName) throws IOException {
            File inputFile = new File(dictionaryFileName);
            Scanner scanner = new Scanner(inputFile);
            Trie trieDictionary = new Trie();
            while (scanner.hasNext()){
               trieDictionary.add(scanner.next());
            }

            File inputFileT = new File("assets/words2.txt");
            Scanner scannerT = new Scanner(inputFileT);
            Trie trieDictionaryT = new Trie();
            while (scannerT.hasNext()){
               trieDictionaryT.add(scannerT.next());
            }

			if(trieDictionary.equals(trieDictionaryT) && trieDictionaryT.equals(trieDictionary)){
				System.out.print("equal");
			}else{
				System.out.print("not equal");
			}
			//System.out.print(trieDictionary.toString());
    }

	/**
	 * Suggest a word from the dictionary that most closely matches
	 * <code>inputWord</code>
	 * @param inputWord
	 * @return The suggestion or null if there is no similar word in the dictionary
	 */
	public String suggestSimilarWord(String inputWord){
        return "";
    }
	
}