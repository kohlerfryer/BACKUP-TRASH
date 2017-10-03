package spell;
import java.util.TreeSet;

/**
 * SimilarWordGenerator
 * The one purpose of this class is to generate possible similar words for user
 *
 **/

public class SimilarWordGenerator {
	static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	static TreeSet<String> generateSimilarWords(String word){
			TreeSet<String> similarWords = new TreeSet<String>();
			generateSimilarWordsWithDeletion(word, similarWords);
			generateSimilarWordsWithTransposition(word, similarWords);
			generateSimilarWordsWithAlteration(word, similarWords);
			generateSimilarWordsWithInsertion(word, similarWords);
			return similarWords;
		}

	private static void generateSimilarWordsWithDeletion(String word, TreeSet<String> similarWords) {
		for(int i = 0; i < word.length(); i++){
			StringBuilder sb = new StringBuilder(word);
			sb.deleteCharAt(i);
			similarWords.add(sb.toString());
		}
	}

    private static void generateSimilarWordsWithTransposition(String word, TreeSet<String> similarWords) {
        for(int i = 0; i < word.length()-1; i++){
			StringBuilder sb = new StringBuilder(word);
			char first = sb.charAt(i);
			char second = sb.charAt(i+1);
			sb.setCharAt(i,second);
			sb.setCharAt(i+1, first);
			similarWords.add(sb.toString());
		}
    }

    private static void generateSimilarWordsWithAlteration(String word, TreeSet<String> similarWords) {
        for(int i = 0; i < word.length(); i++){
	    	for(int j = 0; j < alphabet.length; j++){
				StringBuilder sb = new StringBuilder(word);
				sb.setCharAt(i, alphabet[j]);
    			if(!sb.toString().equals(word))
				similarWords.add(sb.toString());
	    	}
		}

    }

    private static void generateSimilarWordsWithInsertion(String word, TreeSet<String> similarWords) {
        for(int i = 0; i <= word.length(); i++){
	    	for(int j = 0; j < alphabet.length; j++){
       			StringBuilder sb = new StringBuilder(word);
   	        	sb.insert(i, alphabet[j]);
				similarWords.add(sb.toString());
	    	}
		}
    }


}
