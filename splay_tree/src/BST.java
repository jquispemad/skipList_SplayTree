
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class BST<T extends Comparable<T>> {
    private Node<T> head;

    public Node<T> getHead (){return this.head;}
    public void setHead (Node<T> head){this.head = head;}

    public BST(){
        this.head = null;
    }

    public T search(T x){
        Node<T> res = searchNode(x, head);
        return res.data;
    }
    protected Node<T> searchNode(T x, Node<T> n){
        if (n == null) return null; //el nodo sea null (no encontro)
        
        int com = n.data.compareTo(x); //verifica si es menor o mayor o igual
        if (com < 0) return searchNode(x, n.right); //     n < x
        else if (com > 0) return searchNode(x, n.left); // n > x
        
        return n; // n = x  (encontro)
        
    }
    public T getMin(T x){
        if (head == null)   return null;

        Node<T> aux = head;
        while (aux.left != null) {
            aux = aux.left;
        }
        return aux.data;
    }
    
    public T getMax(T x){
        if (head == null)   return null;
        
        Node<T> aux = head;
        while (aux.right != null) {
            aux = aux.right;
        }
        return aux.data;
    }
    public Node<T> parent(T data){
        if (head == null || data.compareTo((T)head.data) == 0) {
            return null;
        }
        return searParentNode(data, head);
    }
    protected Node<T> searParentNode(T data, Node<T> node){
        if (node.left != null && data.compareTo(node.left.data) == 0 || node.right != null && data.compareTo(node.right.data) == 0) {
            return node;//verifica que sea el hijo del padre y retorna el padre
        }
        if (node.data.compareTo(data) > 0 && node.left != null) { //va a la izquierda si no es null
            return searParentNode(data, node.left);
        } else if (node.data.compareTo(data) < 0 && node.right != null) { //va a la derecha si no es null
            return searParentNode(data, node.right);
        }
        return null;

    }

    public boolean son(T data){ //verifica si tiene hijos
        Node<T> res = searchNode(data, head);
        if(res == null)
            return false;
        return true;
    }

    public void insert(T data) {
        head = insertNode(head, data);
    }
    
    private Node<T> insertNode(Node<T> node, T data) {
        if (node == null)  return new Node<>(data);

        int com = data.compareTo(node.data);
        if (com < 0) node.left = insertNode(node.left, data);
        else if (com > 0) node.right = insertNode(node.right, data);
        
        return node;
    }

    public boolean isEmpty(){
        return head==null;
    }






    //--------------una funcion para visualizar el arbol
    public void print() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("BST");
        
        // Agregar estilo al grafo
        graph.setAttribute("ui.stylesheet", "node {text-size: 30px;}");

        
        // Añadir nodos y aristas al grafo
        addNodeGraph(graph, head, null);

        graph.display();
    }

    private void addNodeGraph (Graph graph, Node<T> node, Node<T> parent) {
        if (node == null) return; //detiene la recursion

        String nodeId = node.data.toString();//agrega un nodo
        graph.addNode(nodeId).setAttribute("ui.label", nodeId);

        if (parent != null) {//empareja el nodo
            String parentId = parent.data.toString();
            graph.addEdge(parentId + "-" + nodeId, parentId, nodeId, true);
        }

        addNodeGraph(graph, node.left, node);
        addNodeGraph(graph, node.right, node);
    }
}