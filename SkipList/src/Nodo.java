public class Nodo<T extends Comparable<T>> {
    T valor;
    Nodo<T>[] next;

    
    public Nodo(T valor, int nivel) {
        this.valor = valor;
        this.next = new Nodo[nivel + 1];
    }
}
