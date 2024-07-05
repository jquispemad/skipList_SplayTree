package trie;

import java.util.*;

public class Trie {
    private TrieNode root; //cabeza

    public Trie() {
        root = new TrieNode(); //root
    }

    //insertar
    public void insert(String word) {
        TrieNode actual = root;
        for (char c : word.toCharArray()) {
            actual.next.putIfAbsent(c, new TrieNode(c, false));//agrega si no hay alguno con la misma clave
            actual = actual.next.get(c);//ingresa al siguiente
        }
        actual.isKey = true; //en el que estamos termina la palabra "true" y aumenta 1 en frecuencia
        actual.frequency++;
    }

    //buscar
    public boolean search(String word) {
        TrieNode actual = root;
        for (char c : word.toCharArray()) {
            actual = actual.next.get(c);
            if (actual == null) {
                return false;//no se encontro
            }
        }
        return actual.isKey;//si no esta pero aparece uno dentro del string false sino true
    }

    //eliminar
    private boolean delete(TrieNode actual, String word, int index) {
        if (index == word.length()) {
            if (!actual.isKey) {
                return false;//no formo palabra
            }
            actual.frequency--;
            if (actual.frequency <= 0) {
                actual.isKey = false;
            }
            return true;
        }
        char c = word.charAt(index);
        TrieNode node = actual.next.get(c);
        if (node == null) {
            return false;//no hay palabra
        }
        boolean nextNode = delete(node, word, index + 1) && !node.isKey;
        if (nextNode) {
            return true;
        }
        return false;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }


    //remplazar
    public void replace(String anterior, String nuevo) {
        if (search(anterior)) {
            delete(anterior);
            insert(nuevo);
        }
    }

}
