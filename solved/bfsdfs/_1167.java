package solved.bfsdfs;

import java.util.*;
import java.io.*;

public class _1167 {
	
	// 거리가 자연수이므로 둘 이상의 노드로 연결되어 있다면 최고길이 root가 아니다.
	// list.list.size() == 1
	// 시간초과
	
	// 임의 노드 하나 잡고 끝 중 하나 잡히면 그걸로 다시 DFS

	static long max = 0;
	static int maxRoot = 0;
	static boolean[] v = new boolean[100_001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int line = Integer.parseInt(br.readLine());
		List<List<Node1167>> list = new ArrayList<>();

		for (int i = 0; i <= line; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int root = Integer.parseInt(st.nextToken());

			while(true) {
				int next = Integer.parseInt(st.nextToken());
				if(next==-1)
					 break;
				int cost = Integer.parseInt(st.nextToken());
				list.get(root).add(new Node1167(next,cost));
			}
		}
		
		
		findLongestRoot(list, 1, 0);
		max = 0;
		Arrays.fill(v, false);
		findLongestRoot(list, maxRoot, 0);
		System.out.println(max);
	}

	public static void findLongestRoot(List<List<Node1167>> list, int root, long distSum) {
		v[root] = true;

		for (Node1167 n1 : list.get(root)) {
			if (!v[n1.number]) {
				findLongestRoot(list, n1.number, distSum + n1.dist);
			}
		}
		
		if (max < distSum) {
			max = distSum;
			maxRoot = root;
		}
	}
}

class Node1167 {
	int number;
	int dist;

	public Node1167(int number, int dist) {
		this.number = number;
		this.dist = dist;
	}

	public String toString() {
		return number + " " + dist;
	}
}
