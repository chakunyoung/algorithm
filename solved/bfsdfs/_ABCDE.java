package solved.bfsdfs;
import java.util.*;
import java.io.*;

/* TODO
 * 
 * 모든 노드 DFS하기 싫어서
 * 트리의 지름으로 접근했으나,
 * 서킷때문에 불가능해서 다시 품
 * 
 */ 

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
			dfs(i, 0);
			Arrays.fill(v, false);
		}
		System.out.println(maxDepth == 4 ? 1 : 0);
	}

	public static void dfs(int s, int depth) {
		if (depth == 4) {
			maxDepth = 4;
			return;
		}
        v[s] = true;
		for (int e : list.get(s)) {
			if (!v[e]) {
				dfs(e, depth + 1);
			}
		}
        v[s] = false;
	}
}
