

public class Test {

	 public static void main(String[] args) {
		 
		 SplayTree<Integer> d = new SplayTree<Integer>();
		 try {
			 d.insert(20);
			 d.inOrden();
			 d.insert(10);
			 d.inOrden();
			 d.insert(30);
			 d.inOrden();
			 d.insert(15);
			 d.insert(25);
			 d.inOrden();
			 d.inOrden();
			 d.searchSplay(10);

			 d.inOrden();
			 d.print();
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }

	 }

}
