package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    protected char ch;
    protected boolean isKey;
    protected Map<Character, TrieNode> next;
    protected int frequency;

    public TrieNode(char ch, boolean isKey) {
        this.ch = ch;
        this.isKey = isKey;
        this.next = new HashMap<>();
    }

    public TrieNode() {
        this.ch = '\0';
        this.isKey = false;
        this.next = new HashMap<>();
    }
    public String inOrden(){
        
        return "";
    }

    public String i(){
        
        return "";
    }
}
