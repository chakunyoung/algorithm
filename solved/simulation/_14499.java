package solved.simulation;

import java.util.*;
import java.io.*;

/* TODO:
 * 
 *
 * */
public class _14499 {

	static int[] diceNumber = new int[7];
	static int[][] diceIdx = { { 0, 2, 0 }, { 4, 1, 3 }, { 0, 5, 0 } };
	static int bottomIdx = 6;

	static int[][] arr;
	static int[] diceDirArr;

	static int[] xdir = { 0, 0, 0, -1, 1 };
	static int[] ydir = { 0, 1, -1, 0, 0 };

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int diceLocRow = Integer.parseInt(st.nextToken());
		int diceLocCol = Integer.parseInt(st.nextToken());
		int roll = Integer.parseInt(st.nextToken());

		arr = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		diceDirArr = new int[roll];
		for (int i = 0; i < roll; i++)
			diceDirArr[i] = Integer.parseInt(st.nextToken());

		moveDice(diceLocRow, diceLocCol);

		System.out.println(sb.toString());
	}

	// 이동한 칸이 0이면 주사위 바닥 수가 칸에 복사
	// 0이 아니면 해당 수가 주사위 바닥에 복사, 해당칸은 0이 된다.
	// 주사위를 먼저 굴려서 주사위 바닥을 확인 해야 한다. bottomIdx
	public static void moveDice(int row, int col) {
		for (int i = 0; i < diceDirArr.length; i++) {
			// 주사위 이동
			int nx = row + xdir[diceDirArr[i]];
			int ny = col + ydir[diceDirArr[i]];

			if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
				// 주사위 굴리기
				rollDice(diceDirArr[i]);
				
				row = nx;
				col = ny;

				if (arr[row][col] == 0) {
					arr[row][col] = diceNumber[bottomIdx];
				} else {
					diceNumber[bottomIdx] = arr[row][col];
					arr[row][col] = 0;
				}

				sb.append(diceNumber[diceIdx[1][1]]).append("\n");
			}
		}
	}

	public static void rollDice(int dir) {
		for (int i = 1; i < 5; i++) {
			if (dir != i)
				continue;

			// 주사위 dir에 맞게 diceIdx 변화
			int tempBottomIdx = bottomIdx;
			bottomIdx = diceIdx[1 + xdir[dir]][1 + ydir[dir]]; // 바닥 idx 저장

			diceIdx[1 + xdir[dir]][1 + ydir[dir]] = diceIdx[1][1]; // 중간 수 옮기기
			diceIdx[1][1] = diceIdx[1 - xdir[dir]][1 - ydir[dir]];

			diceIdx[1 - xdir[dir]][1 - ydir[dir]] = tempBottomIdx; // 이전 바닥 idx를 뒤로 붙여준다.
		}
	}
}

