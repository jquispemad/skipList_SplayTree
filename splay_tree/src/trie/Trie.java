package trie;

import java.util.*;
import java.util.Map.Entry;

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
    //crear un mapa de frecuencia
    public Map<String, Integer> getFrecuenciaMap() {
        Map<String, Integer> frecuenciaMap = new HashMap<>(); //crea mapa para guardar las frecuencias
        mapFrecuencyPush(root, "", frecuenciaMap);//comienza a llenar el mapa
        return frecuenciaMap;
    }

    //llenar el mapa de frecuencia
    private void mapFrecuencyPush(TrieNode node, String prefix, Map<String, Integer> frecuenciaMap) {
        if (node.isKey) {//aumenta en el mapa la palabra si hay 3 o mas compara el menor lo borra y luego lo reemplaza por otro caso contrario no hace nada
            if (frecuenciaMap.size() >= 3) {
                String keyMin = null;
                Integer dataMin = Integer.MAX_VALUE;
                for (Entry<String, Integer> frec : frecuenciaMap.entrySet()) {
                    if (dataMin > frec.getValue()) {
                        dataMin = frec.getValue();
                        keyMin  = frec.getKey();
                    }
                }
                if (keyMin != null && frecuenciaMap.get(keyMin) < node.frequency) {//si cumple reemplaza
                    frecuenciaMap.remove(keyMin);//eliminar el valor minimo
                    frecuenciaMap.put(prefix, node.frequency);//agregar el nuevo valor
                }
            }else{//solo aumenta al mapa de 3
                frecuenciaMap.put(prefix, node.frequency);
            }  
        }

        //recorre el mapa
        for (Map.Entry<Character, TrieNode> a : node.next.entrySet()) {
            mapFrecuencyPush(a.getValue(), prefix + a.getKey(), frecuenciaMap);
        }
    }

    //imprimir el mapa
    public void printFrequency() {
        Map<String, Integer> frecuencyMap = getFrecuenciaMap();
        
        for (Entry<String, Integer> entry : frecuencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
