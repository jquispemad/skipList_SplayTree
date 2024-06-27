import java.util.Random;

public class SkipList<T extends Comparable<T>> {
    private static final int MAX_NIVEL = 6;
    private final Nodo<T> cabeza = new Nodo<>(null, MAX_NIVEL);
    private final Random rand = new Random();
    private int nivelActual = 0;

    

    public void insertar(T valor) {
        Nodo<T>[] actualizacion = new Nodo[MAX_NIVEL + 1];
        Nodo<T> actual = cabeza;
        for (int i = nivelActual; i >= 0; i--) {
            while (actual.next[i] != null && actual.next[i].valor.compareTo(valor) < 0) {
                actual = actual.next[i];
            }
            actualizacion[i] = actual;
        }
        int nivelNuevo = nivelAleatorio();
        if (nivelNuevo > nivelActual) {
            for (int i = nivelActual + 1; i <= nivelNuevo; i++) {
                actualizacion[i] = cabeza;
            }
            nivelActual = nivelNuevo;
        }
        Nodo<T> nuevo = new Nodo<>(valor, nivelNuevo);
        for (int i = 0; i <= nivelNuevo; i++) {
            nuevo.next[i] = actualizacion[i].next[i];
            actualizacion[i].next[i] = nuevo;
        }
    }

    public boolean buscar(T valor) {
        Nodo<T> actual = cabeza;
        for (int i = nivelActual; i >= 0; i--) {
            while (actual.next[i] != null && actual.next[i].valor.compareTo(valor) < 0) {
                actual = actual.next[i];
            }
        }
        actual = actual.next[0];
        return actual != null && actual.valor.compareTo(valor) == 0;
    }

    public void eliminar(T valor) {
        Nodo<T>[] actualizacion = new Nodo[MAX_NIVEL + 1];
        Nodo<T> actual = cabeza;
        for (int i = nivelActual; i >= 0; i--) {
            while (actual.next[i] != null && actual.next[i].valor.compareTo(valor) < 0) {
                actual = actual.next[i];
            }
            actualizacion[i] = actual;
        }
        actual = actual.next[0];
        if (actual != null && actual.valor.compareTo(valor) == 0) {
            for (int i = 0; i <= nivelActual; i++) {
                if (actualizacion[i].next[i] != actual) break;
                actualizacion[i].next[i] = actual.next[i];
            }
            while (nivelActual > 0 && cabeza.next[nivelActual] == null) {
                nivelActual--;
            }
        }
    }

    private int nivelAleatorio() {
        int nivel = 0;
        while (rand.nextInt(2) == 0 && nivel < MAX_NIVEL) {
            nivel++;
        }
        return nivel;
    }

    public void imprimirLista() {
        System.out.println("Lista de Salto:");
        for (int i = nivelActual; i >= 0; i--) {
            Nodo<T> nodo = cabeza.next[i];
            System.out.print("Nivel " + i + " => ");
            while (nodo != null) {
                System.out.print(nodo.valor + " ");
                nodo = nodo.next[i];
            }
            System.out.println();
        }
    }
}
