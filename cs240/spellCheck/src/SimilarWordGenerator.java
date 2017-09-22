
/**
 * SimilarWordGenerator
 * The one purpose of this class is to generate possible similar words for user
 *
 **/

public class SimilarWordGenerator {
    final Character[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static generateSimilarWords(String word){
	String[] similarWords = new Array<string>();
	similarWords[] = generateSimilarWordsWithDeletion(word);
	similarWords[] = generateSimilarWordsWithTransposition(word);
	similarWords[] = generateSimilarWordsWithAlteration(word);
	similarWords[] = generateSimilarWordsWithInsertion(word);
	return similarWords;
    }

    private static String[] generateSimilarWordsWithDeletion(String word) {
	String[] deletionWords = new Array<string>();
        for(int i = 0; i < word.length(); i++){
	    StringBuilder sb = new StringBuilder(word);
	    sb.deleteCharAt(i);
	    deletionWords[] = sb.toString();
	}
	return deletionWords;
    }

    private static String[] generateSimilarWordsWithTransposition(String word) {
	String[] transpositionWords = new Array<string>();
        for(int i = 0; i < word.length(); i++){
	    StringBuilder sb = new StringBuilder(word);
	    char first = sb.charAt(i);
	    char second = sb.charAt(i+1);
	    sb.setCharAt(i,second);
	    sb.setCharAt(i+1, first);
	    deletionWords[] = sb.toString();
	}
	return transpositionWords;
    }

    private static String[] generateSimilarWordsWithAlteration(String word) {
	String[] alterationWords = new Array<string>();
        for(int i = 0; i < word.length(); i++){
	    for(int j = 0; j < alphabet.length; j++){
		StringBuilder sb = new StringBuilder(word);
		sb.setCharAt(i, alphabet[j]);
		alterationWords[] = sb.toString();
	    }
	}

	return alterationWords;
    }

    private static String[] generateSimilarWordsWithInsertion(String word) {
	String[] insertionWords = new Array<string>();
        for(int i = 0; i <= word.length(); i++){
	    for(int j = 0; j < alphabet.length; j++){
       		StringBuilder sb = new StringBuilder(word);
   	        sb.insert(i, alphabet[j]);
		insertionWords[] = sb.toString();
	    }
	}

	return alterationWords;
    }


}