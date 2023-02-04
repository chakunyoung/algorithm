package solved.graph.floyd;

import java.io.*;
import java.util.*;

// **q* 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
// N^3
public class _11404 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine()) + 1;
		int[][] arr = new int[size][size];
		int line = Integer.parseInt(br.readLine());

		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr[i], 1_000_000_000);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i][i] = 0;
		}

		while (line-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[s][e] = Math.min(w, arr[s][e]);
		}

		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				for (int k = 1; k < size; k++)
					arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (arr[i][j] == 1_000_000_000)
					sb.append(0 + " ");
				else
					sb.append(arr[i][j] + " ");
			}

			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
