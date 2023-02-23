import java.util.*;
import java.io.*;

public class _ABCDE {

	static List<List<Integer>> list = new ArrayList<>();
	static boolean[] v;
	static int maxDepth;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int node = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());

		for (int i = 0; i < node; i++)
			list.add(new ArrayList<>());

		v = new boolean[node];

		while (edge-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			list.get(s).add(e);
			list.get(e).add(s);
		}

		for (int i = 0; i < node; i++) {
			if (maxDepth == 4)
				break;
			v[i] = true; // 하..
			dfs(i, 0);
			v[i] = false;
			Arrays.fill(v, false);
		}
		System.out.println(maxDepth == 4 ? 1 : 0);
	}

	public static void dfs(int s, int depth) {
		if (depth == 4) {
			maxDepth = 4;
			return;
		}

		for (int e : list.get(s)) {
			if (!v[e]) {
				v[e] = true;
				dfs(e, depth + 1);
				v[e] = false;
			}
		}
	}
}
