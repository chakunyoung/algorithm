
public class CloneTest {
	public static void main(String[] args) {
		CloneNode nc = new CloneNode();
		
		nc.x = 500;
		
		CloneNode newNode = nc.cloning();
		
		System.out.println(newNode.x);
		
		System.out.println(nc == newNode);
		
		
	}

	public static class Node implements Cloneable {
		public int x;

		public Node clone() {
			Object obj = null;
			try {
				obj = super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return (Node) obj;
		}
		
		
		
	}
	
	public static class CloneNode{
		int x;
		
		public CloneNode cloning() {
			CloneNode newNode = new CloneNode();
			newNode.x = x;
			return newNode;
		}
	}
}
