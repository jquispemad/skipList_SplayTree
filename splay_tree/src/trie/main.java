package trie;

import java.util.List;

public class main {
    public static void main(String [] args) {
        Trie trie = new Trie();

        trie.insert("uno");//3
        trie.insert("uno");
        trie.insert("dos");//3
        trie.insert("uno");
        trie.insert("veinte");//1
        trie.insert("unomas");//1
        trie.insert("dos");
        trie.insert("dos");
        

        System.out.println(trie.search("dosmas"));
        System.out.println(trie.search("cuatro"));
        System.out.println(trie.search("unomas"));

        trie.replace("dos", "cuatro");
        System.out.println(trie.search("dos"));// 3 -> 2
        System.out.println(trie.search("cuatro"));// 0 -> 1

        trie.printFrequency();
    }
}
