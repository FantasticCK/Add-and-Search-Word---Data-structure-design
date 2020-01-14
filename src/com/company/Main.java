package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class WordDictionary {
    class TrieNode{
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
            word = "";
        }
    }

    class Trie{
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            int i = 0;
            TrieNode curr = root;
            while (i < s.length()) {
                if (curr.children[s.charAt(i) - 'a'] == null) {
                    curr.children[s.charAt(i) - 'a'] = new TrieNode();
                }
                curr = curr.children[s.charAt(i) - 'a'];
                i++;
            }
            curr.word = s;
        }

        public boolean match(char[] chs, int k, TrieNode node, String word) {
            if (k == chs.length)
                return !node.word.equals("");
            if (chs[k] != '.') {
                return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a'], word);
            }

            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    if (match(chs, k + 1, node.children[i], word)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    Trie trie;
    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        trie.insert(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return trie.match(word.toCharArray(), 0, trie.root, word);
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */