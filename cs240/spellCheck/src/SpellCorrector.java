package src;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector{
	
    private Trie trieDictionary;
	public SpellCorrector(){
		trieDictionary = new Trie();
	}

	public void useDictionary(String dictionaryFileName) throws IOException {
		File inputFile = new File(dictionaryFileName);
		Scanner scanner = new Scanner(inputFile);
		while (scanner.hasNext()){
			trieDictionary.add(scanner.next());
		}
    }

	public String suggestSimilarWord(String word){
		TreeSet<String> similarWords = SimilarWordGenerator.generateSimilarWords(word);
		Trie.Node mostFrequentNode = getSuggestWordFromSet(similarWords);

		if(mostFrequentNode == null){
			TreeSet<String> moreSimilarWords = new TreeSet<String>();
			for(String similarWord : similarWords) {
				moreSimilarWords.addAll(SimilarWordGenerator.generateSimilarWords(similarWord));
			}
			mostFrequentNode = getSuggestWordFromSet(moreSimilarWords);
		}

		if(mostFrequentNode == null)return null;
	    return trieDictionary.nodeToString(mostFrequentNode);
    }

	public Trie.Node getSuggestWordFromSet(TreeSet<String> possibleSimilarWords){
		Trie.Node mostFrequentNode = null;
		for(String similarWord : possibleSimilarWords){
			Trie.Node tempNode = trieDictionary.find(similarWord);
			mostFrequentNode = trieDictionary.getMostSimilarWord(mostFrequentNode, tempNode);
		}
		return mostFrequentNode;
	}
	
}