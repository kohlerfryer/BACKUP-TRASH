
/**
 * SimilarWordGenerator
 * The one purpose of this class is to generate possible similar words for user
 *
 **/

public class SimilarWordGenerator {
    final Character[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private String[] generateSimilarWordsWithDeletion(String word) {
	String[] deletionWords = new Array<string>();
        for(int i = 0; i < word.length(); i++){
	    StringBuilder sb = new StringBuilder(word);
	    sb.deleteCharAt(i);
	    deletionWords[] = sb.toString();
	}
	return deletionWords;
    }

    private String[] generateSimilarWordsWithTransposition(String word) {
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


    private String[] generateSimilarWordsWithAlteration(String word) {
	String[] alterationWords = new Array<string>();
        for(int i = 0; i < word.length(); i++){
	    StringBuilder sb = new StringBuilder(word);
	    char first = sb.charAt(i);
	    char second = sb.charAt(i+1);
	    sb.setCharAt(i,second);
	    sb.setCharAt(i+1, first);
	    deletionWords[] = sb.toString();
	}
	return alterationWords;
    }


}