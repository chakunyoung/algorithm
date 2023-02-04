package solved.bfsdfs;

import java.util.*;
import java.io.*;

public class _18352 {
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cities = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int distance = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());

		dist = new int[cities + 1];
		List<List<Integer>> list = new ArrayList<>();

		for (int i = 0; i <= cities; i++)
			list.add(new ArrayList<Integer>());

		while (edge-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			list.get(s).add(e);
		}

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startNode] = 0;
		dijk(list, startNode);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == distance)
				sb.append(i + "\n");
		}
		
		if (sb.length() == 0)
			System.out.println(-1);
		else
			System.out.println(sb.toString());

	}

	public static void dijk(List<List<Integer>> list, int startNode) {
		Queue<Dest> q = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		q.offer(new Dest(startNode, 0));

		while (!q.isEmpty()) {
			Dest mid = q.poll();

			if (mid.w > dist[mid.dest])
				continue;

			List<Integer> dests = list.get(mid.dest);
			for (int dest : dests) {
				if (dist[mid.dest] + 1 < dist[dest]) {
					dist[dest] = dist[mid.dest] + 1;
					q.offer(new Dest(dest, dist[mid.dest] + 1));
				}
			}
		}
	}
}

class Dest {
	int dest;
	int w;

	public Dest(int dest, int w) {
		this.dest = dest;
		this.w = w;
	}
}
