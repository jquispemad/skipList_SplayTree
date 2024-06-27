public class Main {
    public static void main(String[] args) {
        SkipList<Integer> lista = new SkipList<>();
        lista.insertar(2);
        lista.insertar(4);
        lista.insertar(7);
        lista.insertar(9);
        lista.insertar(11);
        lista.insertar(19);
        lista.insertar(14);
        lista.insertar(29);
        lista.insertar(21);
        lista.insertar(23);

        lista.imprimirLista();

        System.out.println("Buscar 19: " + lista.buscar(19));
        System.out.println("Buscar 15: " + lista.buscar(15));

        lista.eliminar(19);
        lista.imprimirLista();
    }
}
