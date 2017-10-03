package spell;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map;
import java.lang.ClassCastException;
import java.util.regex.Pattern;

public class Trie implements ITrie{

  private Node rootNode;
  private static int wordCharMinimum = 1;
	private int nodeCount;
	private int wordCount;
	private TreeSet<String> sortedDictionarySet;
  static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


    public Trie(){
        this.nodeCount = 0;
        this.wordCount = 0;
        this.rootNode = new Node(null,null);
		    this.sortedDictionarySet = new TreeSet<String>();
    }

	public void add(String word){
		sortedDictionarySet.add(word.toLowerCase());
    rootNode.add(word.toLowerCase());
    }

	public Node find(String word){
		try{
			return rootNode.find(word);
		}catch(NullPointerException e){
			return null;
		}
    }

	public int getWordCount(){
		return wordCount;
    }

	public int getNodeCount(){
		return nodeCount;
    }

	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = this.sortedDictionarySet.iterator();
		while(iterator.hasNext()) {
			stringBuilder.append(iterator.next());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
    }

	public int hashCode(){
		return wordCount * 16 + nodeCount * 19;
    }

	public boolean equals(Object o){
    if ((o instanceof Trie) == false)return false;
		Trie trie = (Trie) o;
		if(trie == this) return true;
		if(trie.getNodeCount() != this.nodeCount) return false;
		if(trie.getWordCount() != this.wordCount) return false;
    return Trie.equalNodes(this.rootNode, trie.rootNode);
    }

  static boolean equalNodes(Node node1, Node node2){
      if(node1 == null && node2 != null)return false;
      if(node1 != null && node2 == null)return false;
      if(node1 == null && node2 == null)return true;
      if(node1.getValue() != node2.getValue())return false;
      for(char letter : Trie.alphabet){
        boolean childNodesEqual = equalNodes(node1.childNodes.get(letter), node2.childNodes.get(letter));
        if(childNodesEqual == false){
          return false;
        }
      }
      return true;
  }

	public int getNodeFrequencyFromPath(String path){
		Node node = this.find(path);
		if(node == null)return -1;
		return node.getValue();
	}

    public Node getMostSimilarWord(Node node1, Node node2){
        if(node1 == null && node2 == null)return null;
        if(node1 == null) return node2;
        if(node2 == null) return node1;
        if(node2.getValue() > node1.getValue())return node2;
        else if(node1.getValue() > node2.getValue()) return node1;
        else if(node1.toString().compareTo(node2.toString()) < 0) return node2;
        else return node1;
    }

	static String nodeToString(Node node){
		String parentNodeString = "";
		if(node.parentNode != null)
			parentNodeString += Trie.nodeToString(node.parentNode);
		if(node.currentLetter != null)
			parentNodeString += node.currentLetter;

		return parentNodeString;
	}

  	public class Node implements ITrie.INode {

		private HashMap<Character,Node> childNodes;
		private Node parentNode;
		private int frequency;
		public Character currentLetter;

		public Node(Node parentNode,Character currentLetter){
			this.parentNode = parentNode;
			this.frequency = 0;
			this.childNodes = new HashMap<Character,Node>(26);
			this.currentLetter = currentLetter;
			nodeCount++;
		}

		public HashMap<Character,Node> getChildNodes(){
			return childNodes;
		}

  		public int getValue(){
              return this.frequency;
      	}

  		public Node getChildNode(Character index){
  			return childNodes.get(index);
  		}

		public Node find(String word){
			if(word.length() == 0 && this.getValue() > 0)return this;
			if(word.length() == 0 && this.getValue() <= 0)return null;
			Character nextLetter = word.toCharArray()[0];
			String subWord = new String(word.substring(1));
			return this.childNodes.get(nextLetter).find(subWord);
		}

		public void add(String word){
			if(word.length() == 0){
				this.frequency++;
				return;
			}
			char[] characters = word.toCharArray();
			Character currentLetter = characters[0];
			String subword = word.substring(1);
      if(this.childNodes.get(currentLetter) == null){
        this.childNodes.put(currentLetter, new Node(this,currentLetter));
      }
			this.childNodes.get(currentLetter).add(subword);
		}

  	}

}
