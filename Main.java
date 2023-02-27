
import java.util.*;
import java.io.*;

/* TODO
 * 
 * 
 */

public class Main {

	static char[][] arr = new char[9][9]; // 윗면들
	static char[][] bottom = new char[3][3]; // 바닥면

	static char[][] temp = new char[3][3];
	static char[][] arrTemp = new char[9][9];
	static char[] threeTemp = new char[3];

	/*
	 * U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면 + 시계 방향 (그 면을 바라봤을 때가 기준),
	 * -반시계 방향
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int i = 0; i < testCase; i++) {
			recolor();
			br.readLine();
			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				char c = s.charAt(0);

				if (c == 'L' || c == 'R')
					lr(s);
				else if (c == 'F' || c == 'B')
					fb(s);
				else if (c == 'U' || c == 'D')
					ud(s);

			}

			// debug
			for (int i1 = 0; i1 < arr.length; i1++)
				System.out.println(Arrays.toString(arr[i1]));

		}

	}

	// 앞면 뒷면 // 구현
	public static void fb(String s) {

	}

	// 왼쪽 오른쪽
	public static void lr(String s) {
		char lr = s.charAt(0);
		char clock = s.charAt(1);
		int col;

		if (lr == 'L')
			col = 3;
		else
			col = 5;

		if (clock == '+') { // 아래로
			for (int i = 0; i < 3; i++)
				threeTemp[i] = arr[i + 6][col];

			for (int i = 0; i < 6; i++)
				arr[i + 3][col] = arr[i][col];

			for (int i = 0; i < 3; i++)
				arr[i][col] = threeTemp[i];

			sideRotation(3, 0, clock);
		} else {
			for (int i = 0; i < 3; i++)
				threeTemp[i] = arr[i][col];

			for (int i = 0; i < 6; i++)
				arr[i][col] = arr[i + 3][col];

			for (int i = 0; i < 3; i++)
				arr[i + 6][col] = threeTemp[i];

			sideRotation(3, 6, clock);
		}
	}

	// 윗면 아랫면
	public static void ud(String s) {
		char ud = s.charAt(0);
		char clock = s.charAt(1);

		if (ud == 'U') {
			if (clock == '+') {
				for (int i = 2; i <= 6; i++) {
					for (int j = 2; j <= 6; j++) {
						arrTemp[j][9 - 1 - i] = arr[i][j];
					}
				}
				for (int i = 2; i <= 6; i++) {
					for (int j = 2; j <= 6; j++) {
						arr[i][j] = arrTemp[i][j];
					}
				}
			} else {
				for (int i = 2; i <= 6; i++) {
					for (int j = 2; j <= 6; j++) {
						arrTemp[9 - 1 - j][i] = arr[i][j];
					}
				}
				for (int i = 2; i <= 6; i++) {
					for (int j = 2; j <= 6; j++) {
						arr[i][j] = arrTemp[i][j];
					}
				}
			}
		} else {
			if (clock == '+') {
				for (int i = 0; i < 3; i++)
					threeTemp[i] = arr[0][3 + i];

				for (int i = 0; i < 3; i++)
					arr[0][5 - i] = arr[i + 3][0];

				for (int i = 0; i < 3; i++)
					arr[3 + i][0] = arr[8][3 + i];

				for (int i = 0; i < 3; i++)
					arr[8][3 + i] = arr[5 - i][8];

				for (int i = 0; i < 3; i++)
					arr[3 + i][8] = threeTemp[i];

			} else {
				for (int i = 0; i < 3; i++)
					threeTemp[i] = arr[0][3 + i];

				for (int i = 0; i < 3; i++)
					arr[0][i+3] = arr[i+3][8];

				for (int i = 0; i < 3; i++)
					arr[i+3][8] = arr[8][5-i];

				for (int i = 0; i < 3; i++)
					arr[8][5-i] = arr[5-i][0];

				for (int i = 0; i < 3; i++)
					arr[5 - i][0] = threeTemp[i];
			}
			bottomRotation(clock);
		}
	}

	// 옆면도 도는거 생각
	public static void sideRotation(int row, int col, char clock) {
		if (clock == '+') { // 시계
			for (int i = row, ti = 0; i < row + 3; i++, ti++) {
				for (int j = col, tj = 0; j < col + 3; j++, tj++)
					temp[ti][tj] = arr[3 - j - 1][i];
			}
		} else {
			for (int i = row, ti = 0; i < row + 3; i++, ti++) {
				for (int j = col, tj = 0; j < col + 3; j++, tj++)
					temp[ti][tj] = arr[j][3 - i - 1];
			}
		}
		for (int i = row, ti = 0; i < row + 3; i++, ti++) {
			for (int j = col, tj = 0; j < col + 3; j++, tj++)
				arr[i][j] = temp[ti][tj];
		}
	}

	public static void bottomRotation(char clock) {
		if (clock == '+') { // 시계
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					temp[i][j] = bottom[3 - j - 1][i];
			}
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					temp[i][j] = bottom[j][3 - i - 1];
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				bottom[i][j] = temp[i][j];
		}
	}

	public static void recolor() {
		for (int i = 0; i < 3; i++)
			Arrays.fill(bottom[i], 'y');

		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++)
				arr[i][j] = 'w';
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 3; j < 6; j++)
				arr[i][j] = 'o';
		}

		for (int i = 3; i < 6; i++) {
			for (int j = 6; j < 9; j++)
				arr[i][j] = 'b';
		}

		for (int i = 6; i < 9; i++) {
			for (int j = 3; j < 6; j++)
				arr[i][j] = 'r';
		}

		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 3; j++)
				arr[i][j] = 'g';
		}
	}
}
