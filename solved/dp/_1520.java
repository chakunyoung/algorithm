package solved.dp;
import java.util.*;
import java.io.*;

public class _1520 {

	static int[][] arr;
	static int[][] passArr;

	static int[] xdir = { 1, -1, 0, 0 };
	static int[] ydir = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		arr = new int[row][col];
		passArr = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < row; i++) {
			Arrays.fill(passArr[i], -1);
		}
		
		int answer = dfs(0, 0);

		//for (int i = 0; i < row; i++)
		//	System.out.println(Arrays.toString(arr[i]));
		//System.out.println();
		//for (int i = 0; i < row; i++)
		//	System.out.println(Arrays.toString(passArr[i]));

		System.out.println(answer == -1 ? 0 : answer);
	}

	// 경로 memoizaion 필요
	public static int dfs(int x, int y) {
		if (x == arr.length - 1 && y == arr[0].length - 1) { // 도착
			passArr[x][y] = 1;
			return 1;
		}

		// 한번이라도 가본 경로
		// 0이거나 합치거나
		if(passArr[x][y] != -1) {
			return passArr[x][y];
		}
		
		passArr[x][y] = 0; // 진입 가능시 초기화
		
		for (int i = 0; i < 4; i++) {
			int nx = x + xdir[i];
			int ny = y + ydir[i];

			if (!(nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length))
				continue;
			
			// 여기서 자꾸 1을 만들어서
			if(arr[x][y] > arr[nx][ny])
				passArr[x][y] += dfs(nx, ny);
			
		}
		return passArr[x][y];
	}
}
