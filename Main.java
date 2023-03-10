
import java.util.*;
import java.io.*;

/*
 * 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번 타자부터 타석에 선다.
 *  -- 큐에 입력 처음에 다 넣어놓고, 9명 꺼내서 순열
 *  -- 2,3,4 넣고 1번을 4번째로
 * 
 * 한 이닝에 3아웃이 발생하면 이닝이 종료
 *  -- 3out이면, 9개까지 다 빼줘야 한다. 
 *  
 * 1번 선수를 4번 타자로 미리 결정했다. 
 * 
 * 9번 타자까지 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다. 
 */

public class Main {
	
	static Queue<Integer> base = new ArrayDeque<>();
	
	static int[][] arr;
	static int[] temp = new int[9];
	static boolean[] v = new boolean[10];
	
	static int[][] temps;

	static int row;
	static int answerPoint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int turn = Integer.parseInt(br.readLine());

		row = turn;
		arr = new int[turn][9];
		
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		v[1] = true;
		temp[3] = 1;
		permutation(9, 0);

		System.out.println(answerPoint);
	}

	public static void game() {
		int outCount = 0;
		int inning = 0;
		int point = 0;

		int idx = 0;
		while (inning != row) {
			if (arr[inning][temp[idx] - 1] == 0) {
				outCount++;
			} else 
				point += basePointCalc(arr[inning][temp[idx] - 1]);
				
			if (outCount == 3) {
				inning++;
				outCount = 0;
				base.clear();
			}
			idx = (idx++) % 9;
		}
		answerPoint = Math.max(answerPoint, point);
	}

	public static int basePointCalc(int playerPoint) {
		int point = 0;
		base.offer(1);
		for (int i = 1; i < playerPoint; i++)
			base.offer(0);

		while (base.size() >= 4) {
			if (base.poll() == 1)
				point++;
		}
		return point;
	}

	public static void permutation(int limit, int n) {
		if (limit == n) {
			game();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (n == 3) {
				permutation(limit, n + 1);
				continue;
			}

			if (!v[i]) {
				v[i] = true;
				temp[n] = i;
				permutation(limit, n + 1);
				v[i] = false;
			}
		}
	}
}
