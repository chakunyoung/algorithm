package solved.recursion.backtracking;

import java.util.*;
import java.io.*;

public class _2580 {
	static int[][] arr;
	static List<NodeXY> list;
	static List<NodeXY> temp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 빈공간 node x y
		// 해당공간의 스도쿠검증, boolean 3개
		// false 인곳들을 배열로 넘겨준다.  // 재귀 종료시 0으로 원위치

		arr = new int[9][9];
		list = new ArrayList<NodeXY>(); // 확인해야하는 곳

		for (int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				int number = Integer.parseInt(st.nextToken());
				if (number == 0) {
					list.add(new NodeXY(i, j));
				} else
					arr[i][j] = number;
			}
		}

		bt(0, list.size());
		System.out.println(sb.toString());
	}

	public static void bt(int n, int limit) {
		if (sb.length() != 0) 
			return;

		if (n == limit) {
			for(int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++) 
					sb.append(arr[i][j] + " ");
				sb.append("\n");
			}
			return;
		}
		
		NodeXY node = list.get(n);
		boolean[] barr = check(node.x, node.y); 

		for (int i = 1; i < 10; i++) {
			if (sb.length() != 0)
				return;
			
			if(!barr[i]) {
				arr[node.x][node.y] = i;
				bt(n + 1, limit);
				arr[node.x][node.y] = 0;
			}
		}
	}

	// 스도쿠 검증
	public static boolean[] check(int x, int y) {
		boolean[] row = new boolean[10];
		boolean[] col = new boolean[10];
		boolean[] box = new boolean[10];

		for (int i = 0; i < 9; i++) {
			if (arr[x][i] != 0)
				row[arr[x][i]] = true;
			if (arr[i][y] != 0)
				col[arr[i][y]] = true;
		}

		// 0, 3, 6
		int xa = x / 3 * 3;
		int ya = y / 3 * 3;

		for (int i = xa; i < xa + 3; i++) {
			for (int j = ya; j < ya + 3; j++) {
				if (arr[i][j] != 0)
					box[arr[i][j]] = true;
			}
		}

		boolean[] b = new boolean[10];
		for (int i = 1; i < b.length; i++) {
			if (row[i] || col[i] || box[i])
				b[i] = true;
		}
		return b;
	}
}

class NodeXY {
	int x, y;

	public NodeXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x + " " + y;
	}
}