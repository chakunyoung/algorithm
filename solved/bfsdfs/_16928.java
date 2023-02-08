package solved.bfsdfs;

import java.util.*;
import java.io.*;

// bfs 조건 문제
public class _16928 {
	static int min = Integer.MAX_VALUE;
	static Map<Integer, Integer> m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int lines = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		m = new HashMap<>();

		while (lines-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			m.put(s, e);
		}
		
		dice();
		System.out.println(min);
	}

	public static void dice() {
		int[] minCount = new int[110];
		Arrays.fill(minCount, Integer.MAX_VALUE);

		Queue<NodeDice> q = new LinkedList<>();
		for (int i = 1; i <= 6; i++) {
			q.offer(new NodeDice(1, 0));
		}

		while (!q.isEmpty()) {
			NodeDice n = q.poll();
			int dis = n.dis;
			int count = n.count;
			if (dis >= 100) {
				if (dis == 100)
					min = Math.min(min, count);
				continue;
			}
			count++;

			for (int i = 1; i <= 6; i++) {
				// *** minCount가 되면 사용 아니면 사용하지 않는다.
				if (minCount[i + dis] <= count)
					continue;

				// 뱀, 사다리 만나는 경우
				if (m.containsKey(i + dis)) {
					int dis1 = m.get(i + dis);
					q.offer(new NodeDice(dis1, count));
					minCount[i + dis] = count;
					continue;
				}
		
				// 정상
				q.offer(new NodeDice(i + dis, count));
				minCount[i + dis] = count;
			}
		}
	}
}

class NodeDice {
	int dis, count;

	public NodeDice(int dis, int count) {
		this.dis = dis;
		this.count = count;
	}
}
