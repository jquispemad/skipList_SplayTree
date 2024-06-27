public class SplayTree<E extends Comparable<E>> extends BST<E> {

    class NodeSplay extends Node<E> {
        public NodeSplay(E data) {
            super(data);
        }

        public String toString() {
            return super.toString() + " a ";
        }
    }

    public SplayTree() {
        super();
    }

    public NodeSplay parentSplay(NodeSplay hijo) {
        return (NodeSplay) parent(hijo.data);
    }

    private void splay(NodeSplay node) {
        while (node != null && node != this.getHead()) {
            NodeSplay parent = (NodeSplay) parentSplay(node);
            NodeSplay grandParent = (NodeSplay) (parent != null ? parentSplay(parent) : null);

            if (parent == null) {
                break;
            }

            if (grandParent == null) {
                if (node == parent.getLeft()) {
                    rotateSR(parent);
                } else {
                    rotateSL(parent);
                }
            } else if (node == parent.getLeft() && parent == grandParent.getLeft()) {
                rotateSR(grandParent);
                rotateSR(parent);
            } else if (node == parent.getRight() && parent == grandParent.getRight()) {
                rotateSL(grandParent);
                rotateSL(parent);
            } else if (node == parent.getRight() && parent == grandParent.getLeft()) {
                rotateSL(parent);
                rotateSR(grandParent);
            } else {
                rotateSR(parent);
                rotateSL(grandParent);
            }
        }
    }

    public void insert(E x) {
        NodeSplay newNode = new NodeSplay(x);
        if (this.isEmpty()) {
            this.setHead(newNode);
        } else {
            NodeSplay current = (NodeSplay) this.getHead();
            NodeSplay parent = null;

            while (current != null) {
                parent = current;
                if (x.compareTo(current.getData()) < 0) {
                    current = (NodeSplay) current.getLeft();
                } else {
                    current = (NodeSplay) current.getRight();
                }
            }

            newNode.setParent(parent);
            if (x.compareTo(parent.getData()) < 0) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }

            splay(newNode);
        }
    }

    public NodeSplay searchSplay(E x) {
        NodeSplay current = (NodeSplay) this.getHead();
        while (current != null) {
            int cmp = x.compareTo(current.getData());
            if (cmp == 0) {
                splay(current);
                return current;
            } else if (cmp < 0) {
                current = (NodeSplay) current.getLeft();
            } else {
                current = (NodeSplay) current.getRight();
            }
        }
        return null;
    }

    private NodeSplay rotateSL(NodeSplay node) { // izquierda
        NodeSplay son = (NodeSplay) node.getRight();
        node.setRight(son.getLeft());
        if (son.getLeft() != null) {
            son.getLeft().setParent(node);
        }
        son.setLeft(node);
        son.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setHead(son);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(son);
        } else {
            node.getParent().setRight(son);
        }
        node.setParent(son);
        return son;
    }

    private NodeSplay rotateSR(NodeSplay node) { // derecha
        NodeSplay son = (NodeSplay) node.getLeft();
        node.setLeft(son.getRight());
        if (son.getRight() != null) {
            son.getRight().setParent(node);
        }
        son.setRight(node);
        son.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setHead(son);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(son);
        } else {
            node.getParent().setRight(son);
        }
        node.setParent(son);
        return son;
    }

    public void inOrden() {
        if (this.isEmpty())
            System.out.println("Arbol esta vacio....");
        else
            inOrden((NodeSplay) this.getHead());
        System.out.println();
    }

    protected void inOrden(NodeSplay actual) {
        if (actual.getLeft() != null) inOrden((NodeSplay) actual.getLeft());
        System.out.print(actual + ", ");
        if (actual.getRight() != null) inOrden((NodeSplay) actual.getRight());
    }
}
