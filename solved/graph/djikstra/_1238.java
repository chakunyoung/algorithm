package solved.graph.djikstra;

import java.util.*;
import java.io.*;

// 다익스트라 두번
// 1000^3
public class _1238 {
	static int[] toDest;
	static List<List<Node1238>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int node = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		int destNode = Integer.parseInt(st.nextToken());

		toDest = new int[node + 1];
		list = new ArrayList<>();
		for (int i = 0; i <= node; i++)
			list.add(new ArrayList<>());

		while (edges-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list.get(s).add(new Node1238(e, w));
		}

		int[] _sTofd = new int[node + 1];
		int[] _fdTos = new int[node + 1];

		for (int i = 1; i <= node; i++) {
			// 시작 -> 목적지
			Arrays.fill(toDest, Integer.MAX_VALUE);
			toDest[i] = 0;
			_sTofd[i] = dijk(i, destNode);
			
			// 목적지 -> 시작
			Arrays.fill(toDest, Integer.MAX_VALUE);
			toDest[destNode] = 0;
			_fdTos[i] = dijk(destNode, i);
		}

		int max = 0;
		for(int i = 1 ; i<=node; i++) 
			max = Math.max(max, _sTofd[i] + _fdTos[i]);
		System.out.println(max);
	}

	public static int dijk(int startNode, int destNode) {
		Queue<Node1238> q = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		q.offer(new Node1238(startNode, 0));

		while (!q.isEmpty()) {
			Node1238 mid = q.poll();

			if (mid.w > toDest[mid.dest])
				continue;

			for (Node1238 d : list.get(mid.dest)) {
				if (toDest[mid.dest] + d.w < toDest[d.dest]) {
					toDest[d.dest] = toDest[mid.dest] + d.w;
					q.offer(new Node1238(d.dest, toDest[d.dest]));
				}
			}
		}

		return toDest[destNode];
	}
}

class Node1238 {
	int dest, w;

	public Node1238(int dest, int w) {
		this.dest = dest;
		this.w = w;
	}
}