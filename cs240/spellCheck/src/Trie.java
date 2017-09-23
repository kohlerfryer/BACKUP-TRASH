package src;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map;
import java.lang.ClassCastException;

public class Trie implements ITrie{

    private Node rootNode;
    private static int wordCharMinimum = 2;
	private int nodeCount = 0;
	private int wordCount = 0;
	private TreeSet<String> sortedDictionarySet;

    public Trie(){
        this.rootNode = new Node(null,null);
		this.sortedDictionarySet = new TreeSet<String>();
    }

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 * @param word The word being added to the trie
	 */
	public void add(String word){
        if(word.length() <= wordCharMinimum)return;
		sortedDictionarySet.add(word);
        rootNode.add(word);
    }
	
	/**
	 * searches the trie for the specified word
	 * @param word The word being searched for
	 * @return A reference to the trie node that represents the word,
	 * 			or null if the word is not in the trie
	 */
	public Node find(String word){
		try{
			return rootNode.find(word);
		}catch(NullPointerException e){
			return null;
		}
    }	
	
	/**
	 * Returns the number of unique words in the trie
	 * @return The number of unique words in the trie
	 */
	public int getWordCount(){
		return wordCount;
    }
	
	/**
	 * Returns the number of nodes in the trie
	 * 
	 * @return The number of nodes in the trie
	 */
	public int getNodeCount(){
		return nodeCount;

    }
	
	/**
	 * The toString specification is as follows:
	 * For each word, in alphabetical order:
	 * <word>\n
	 */
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
		Trie trie = (Trie) o;
		if(trie == this) return true;
		if(trie.getNodeCount() != this.nodeCount) return false;
		if(trie.getWordCount() != this.wordCount) return false;
		return (Trie.isEqualTo(this.rootNode, "", trie) && Trie.isEqualTo(trie.rootNode, "", this));
    }

	//iterate through all the child nodes
	//if child nodes are equal to other child nodes
	//then continue
	//if any of the children are different than the trees are not equal
	//if children are all equal, check if this node is equal
	static boolean isEqualTo(Node node, String path, Trie otherTrie){
		for(Map.Entry<Character, Node> iteration : node.getChildNodes().entrySet()) {
			Character key = iteration.getKey();
			Node childNode = iteration.getValue();
			String newPath = path + key;
			if(isEqualTo(childNode, newPath, otherTrie)){
				continue;
			}
			return false;
		}
		return (node.frequency == otherTrie.getNodeFrequencyFromPath(path));
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
        else if(node1.toString().compareTo(node2.toString()) > 0) return node2;
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


	/**
  	 * Your trie node class should implement the ITrie.INode interface
  	 */
  	public class Node implements ITrie.INode {
		/**
		* allows for nice letter key indexing
		*/
		private HashMap<Character,Node> childNodes;
		private Node parentNode;
		private int frequency;
		private Character currentLetter;

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
  		/**
  		 * Returns the frequency count for the word represented by the node
  		 *
  		 * @return The frequency count for the word represented by the node
  		 */
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
			//System.out.println("*" + nextLetter);
			return this.childNodes.get(nextLetter).find(subWord);
		}
		/**
		* takes first letter of word and either creates
		* new node or passes the rest of the word to the
		* child node indexed at that first letter in hashmap
		*/
		public void add(String word){
			if(word.length() == 0){
				this.frequency++;
				return;
			}
			char[] characters = word.toCharArray();
			Character currentLetter = characters[0];
			String subword = word.substring(1);
			this.childNodes.putIfAbsent(currentLetter, new Node(this,currentLetter));
			this.childNodes.get(currentLetter).add(subword);
		}

  	}

}
