package solved.graph.tree;

import java.util.*;
import java.io.*;

/* TODO:
 * 이진 트리라는 얘기는 없다.
 * 입력에서 누가 부모, 자식인지는 명시되지 않았다. 
 * 
 * 
 * */
public class _11437 {
	static List<TreeNode> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int nodes = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<= nodes; i++) 
			list.add(new TreeNode());
		
		for(int i = 0; i<nodes - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.get(c).node = c;
			list.get(p).node = p;
			
			list.get(p).children.add(list.get(c));
			list.get(c).children.add(list.get(p));
			
		}	
		
		setDepth(1,0);
		
		int answer = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(answer -->0) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			sb.append(findLCA(n1, n2)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void setDepth(int root, int prev) {
		for(TreeNode child : list.get(root).children) {
			if(child.node != prev) {
				child.depth = list.get(root).depth + 1;
				child.parent = list.get(root).node;
				setDepth(child.node, root);
			}
		}
	}
	
	public static int findLCA(int node1, int node2) {
		TreeNode tn1 = list.get(node1);
		TreeNode tn2 = list.get(node2);
		
		// depth 맞추기
		while(tn1.depth != tn2.depth) {
			if(tn1.depth > tn2.depth) {
				tn1 = list.get(tn1.parent);
			}else if(tn1.depth < tn2.depth){
				tn2 = list.get(tn2.parent);
			}
		}
		
		// depth 같이 증가
		while(tn1 != tn2) {
			tn1 = list.get(tn1.parent);
			tn2 = list.get(tn2.parent);
		}
		
		return tn1.node;
	}
	
	static class TreeNode{
		int node;
		int depth;
		int parent;
		List<TreeNode> children = new ArrayList<>();
		
		public String toString() {
			return this.node + " " + this.depth;
		}
	}
}

