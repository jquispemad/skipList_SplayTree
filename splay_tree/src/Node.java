public class Node<T> {
    protected T data;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> parent;

        public Node(T data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node<T> getLeft (){return this.left ;}
        public Node<T> getRight(){return this.right;}
        public    T    getData (){return this.data ;}

        public void setLeft (Node<T> left ){this.left  = left ;}
        public void setRight(Node<T> right){this.right = right;}
        public void setData (     T data  ){this.data  = data ;}
        
        public String toString(){
            return data.toString();
        }
        public Node<T> getParent() {
            return parent;
        }
    
        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
}