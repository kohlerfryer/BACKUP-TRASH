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
        this.rootNode = new Node(null);
		this.sortedDictionarySet = new TreeSet<String>();

    }

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 * 
	 * @param word The word being added to the trie
	 */
	public void add(String word){
        if(word.length() <= wordCharMinimum)return;
		sortedDictionarySet.add(word);
        rootNode.add(word);
    }
	
	/**
	 * searches the trie for the specified word
	 * 
	 * @param word The word being searched for
	 * 
	 * @return A reference to the trie node that represents the word,
	 * 			or null if the word is not in the trie
	 */
	public Node find(String word){
		return rootNode.find(word);
    }
	
	/**
	 * Returns the number of unique words in the trie
	 * 
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
		return 0;
    }

	public boolean equals(Object o){
		// if(o.getClass() != this.getClass()) return false;
		Trie trie = (Trie) o;
		// if(trie == this) return true;
		// if(trie.getNodeCount() != this.nodeCount) return false;
		// if(trie.getWordCount() != this.wordCount) return false;
		return this.rootNode.isEqualTo("", trie);
    }

	public int getNodeFrequencyFromPath(String path){
		char[] pathPieces = path.toCharArray();
		Node tempNode = this.rootNode;
		for(int i = 0; i < pathPieces.length; i++){
			Character index = pathPieces[i];
			tempNode = tempNode.getChildNode(index);
			if(tempNode == null)return -1;
		}
		return tempNode.getValue();
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

        public Node(Node parentNode){
            this.parentNode = parentNode;
            this.frequency = 0;
            this.childNodes = new HashMap<Character,Node>(26); 
			nodeCount++;

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

		public boolean isEqualTo(String path, Trie otherTrie){
			//iterate through all the child nodes
			for(Map.Entry<Character, Node> iteration : childNodes.entrySet()) {
				Character key = iteration.getKey();
				Node childNode = iteration.getValue();
				String newPath = path + key;
				System.out.print(newPath + "\n");
				//if child nodes are equal to other child nodes
				//then continue
				if(childNode.isEqualTo(newPath, otherTrie)){
					continue;
				}
				//if any of the children are different than the trees are not equal
				return false;
			}
			//if children are all equal, check if this node is equal
			System.out.print(this.frequency + ":" + otherTrie.getNodeFrequencyFromPath(path) +  "\n" );
			return (this.frequency == otherTrie.getNodeFrequencyFromPath(path));
			//return true;
		}

		public Node find(String word){
			char[] characters = word.toCharArray();
            if(characters.length <= 1){
				return this;
            }
			Character currentLetter = characters[0];
			String subword = word.substring(1);
			if(this.childNodes.containsKey(subword)){
				return this.childNodes.get(currentLetter).find(subword);
			}
			return null;
		}

		/**
		 * takes first letter of word and either creates
         * new node or passes the rest of the word to the
         * child node indexed at that first letter in hashtable
		 */

         public void add(String word){
            char[] characters = word.toCharArray();
            if(characters.length <= 0){
                this.frequency++;
				wordCount++;
                return;
            }
            Character currentLetter = characters[0];
            String subword = word.substring(1);
            this.childNodes.putIfAbsent(currentLetter, new Node(this));
            this.childNodes.get(currentLetter).add(subword);
         }

	}


}