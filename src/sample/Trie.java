/**
 * Created by Phil on 12/10/2016.
 */
package sample;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie {
    //the root only serves to anchor the trie.
    public class TrieNode {

        public char value;
        boolean word = false;
        TrieNode[] next = new TrieNode[256];
        private int nextLength = 0;

        public TrieNode(char c) {
            value = c;
        }

        void setChild(char c, TrieNode node){
            next[c]=node;
            nextLength++;
        }
        void clearNext() {
            next = new TrieNode[256];
            nextLength = 0;
        }

        boolean nextIsEmpty(){
            return nextLength == 0;
        }

        int nextSize(){
            return nextLength;
        }
    }
    public boolean isPrefix(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            n = n.next[c];
            if (null == n) {
                return false;
            }
        }
        return !n.nextIsEmpty();
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }//constructor

    /******************************************************************
     * Function: addWord
     * @param word
     * @return boolean
     *
     * Description: Insert the given word into the trie. If the word
     *   already exists, return false; else return true.
     *
     * Technical Details: A word is added to a trie one letter at a time.
     *
     *    0] Start with the root of the trie as the current node n.
     *    1] for each character c
     *    2]    if c is not among the children of n
     *    3]       add c to the children of n
     *    4]    set n to the node representing c.
     *    5] At this point, n represent the last char in the word,
     *       so if n is marked return false since word already exists
     *       else mark n as word and return true.
     ****************************************************************/
    public boolean addWord(String word) {
        TrieNode n = root, tmp;
        for (char c : word.toCharArray()) {
            tmp = n.next[c];
            if (tmp == null) {
                tmp = new TrieNode(c);
                n.setChild(c, tmp);
            }
            n = tmp;
        }
        if (n.word) {
            return !n.word;
        }
        n.word = true;
        return n.word;
    }//addWord
}