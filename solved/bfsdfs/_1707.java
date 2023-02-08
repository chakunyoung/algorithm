package solved.bfsdfs;

import java.util.*;
import java.io.*;

// bfs
// 사용된 노드만 검사
public class _1707 {
	static List<List<Integer>> list;
	static boolean[] v;
	static int[] colors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeCount = Integer.parseInt(st.nextToken());

			// blue1 , red2,
			colors = new int[nodeCount + 1];
			v = new boolean[nodeCount + 1];
			colors[1] = 1; // blue
			list = new ArrayList<>();

			for (int nn = 0; nn <= nodeCount; nn++)
				list.add(new ArrayList<>());
			int edgeCount = Integer.parseInt(st.nextToken());
			Set<Integer> usedNode = new HashSet<>();

			while (edgeCount-- > 0) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				usedNode.add(s);
				usedNode.add(s);

				list.get(s).add(e);
				list.get(e).add(s);
			}

			List<Integer> un = new ArrayList<>(usedNode);
			for (int i1 = 0; i1 < un.size(); i1++) {
				if (colors[un.get(i1)] == 0)
					coloring(un.get(i1));
			}

			Arrays.fill(v, false);

			boolean check = true;
			for (int i1 = 0; i1 < un.size(); i1++) {
				if (!v[un.get(i1)] && !bfs(un.get(i1)))
					check = false;
			}

			sb.append(check ? "YES" : "NO").append("\n");
		} // end tc
		System.out.println(sb.toString());

	}

	public static void coloring(int startNode) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode); // 1 blue, 2 red;
		v[startNode] = true;
		colors[startNode] = 1;

		while (!q.isEmpty()) {
			int n = q.poll();

			for (int nn : list.get(n)) {
				if (!v[nn]) {
					v[nn] = true;
					colors[nn] = colors[n] == 1 ? 2 : 1;
					q.offer(nn);
				}
			}
		}
	}

	public static boolean bfs(int startNode) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode); // 1 blue, 2 red;
		v[startNode] = true;

		while (!q.isEmpty()) {
			int n = q.poll();
			for (int nn : list.get(n)) {
				if (colors[nn] == colors[n])
					return false;
				if (!v[nn]) {
					v[n] = true;
					q.offer(nn);
				}
			}
		}
		return true;
	}
}
