package solved.bfsdfs;

import java.util.*;
import java.io.*;

// 간단한 flood fill 문제
public class _2583 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int count = Integer.parseInt(st.nextToken());

		int[][] arr = new int[x][y];
		List<Integer> list = new ArrayList<Integer>();

		while (count-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;

			for (int i = x1; i <= x2; i++) {
				for (int j = y1; j <= y2; j++) 
					arr[j][i] = 1;
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (arr[i][j] == 0) 
					list.add(bfs(arr, i, j));
			}
		}

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for(int n : list)
			sb.append(n+" ");
		System.out.println(sb.toString());

	}

	public static int bfs(int[][] arr, int x, int y) {
		Queue<Node> q = new LinkedList<Node>();
		int[] xRange = { 1, -1, 0, 0 };
		int[] yRange = { 0, 0, 1, -1 };
		int count = 0;
		q.offer(new Node(x, y));
		arr[x][y] = 1;

		while (!q.isEmpty()) {
			Node n = q.poll();
			count++;
			int x1 = n.x;
			int y1 = n.y;
			for (int i = 0; i < 4; i++) {
				int nx = x1 + xRange[i];
				int ny = y1 + yRange[i];
				if (nx >= 0 && nx < arr.length && ny >= 0 && ny < arr[0].length) {
					if (arr[nx][ny] == 0) {
						arr[nx][ny] = 1;
						q.offer(new Node(nx, ny));
					}
				}
			}
		}
		return count;
	}
}//

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
