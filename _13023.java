import java.util.*;
import java.io.*;

// union find 같은데


public class _13023 {

	static List<List<Integer>> list = new ArrayList<>();
	static boolean[] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int node = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		v = new boolean[node];
		
		while(node --> 0) {
			list.add(new ArrayList<>());
		}
		
		while(edge --> 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.get(s).add(e);
			list.get(e).add(s);
		}
	}
	
	// bfs 거리가 4 이상이라면 존재
	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0); // 0번부터 번호가 매겨져 있다.
		
		while(!q.isEmpty()) {
			int n = q.poll();
			
			for(int node : list.get(n)) {
				if(!v[node]) {
					v[node] = true;
					q.offer(node);
					
				}
			}
		}
	}
}
