package solved.simulation;

import java.util.*;
import java.io.*;

// answer = 격자밖으로 나간 모래의 양 ( arr 범위를 벗어나면, 나간 것)

//  - 토네이도
// 2번 움직이고 이동량 증가
// 이동량 전부 소모하면, dir 변화

// 시간 소요된 곳 -> 두군데서 모두 처리해줘야 하는 부분을 if else로 나눠, 한군데에서만 처리해 디버깅이 오래걸림

public class _20057 {

	static int[][] arr;
	static int size;
	static int answerSandAmount;

	static int[] xrange = { 0, 1, 0, -1 };
	static int[] yrange = { -1, 0, 1, 0 };
	static int[][] tempSand = new int[5][5];
	
	static final int[][] sandPercentCopy = { { -1, -1, 2, -1, -1 }, { -1, 10, 7, 1, -1 }, { 5, -1, -1, -1, -1 },
			{ -1, 10, 7, 1, -1 }, { -1, -1, 2, -1, -1 }, };
	static int[][] sandPercent = { { -1, -1, 2, -1, -1 }, { -1, 10, 7, 1, -1 }, { 5, -1, -1, -1, -1 },
			{ -1, 10, 7, 1, -1 }, { -1, -1, 2, -1, -1 }, };
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		arr = new int[size][size];

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		torna();
		System.out.println(answerSandAmount);
	}

	public static void torna() {
		Tornado t = new Tornado(size / 2, size / 2, 0, 0);

		// 2번 이동 -> 이동량 증가
		int turn = 2;
		int mvdis = 1;
		int mvdistemp = mvdis;

		while (isMoveTornado(t)) { // 이동하고 해당위치의 모래 흡수
			// 모래량 계산
			sandCalc(t);

			// 토네이도 회전 로직
			mvdistemp--;
			if (mvdistemp == 0) {
				turn--;
				if (turn == 0) {
					turn = 2;
					mvdis++;
				}
				mvdistemp = mvdis;
				t.dir = (t.dir + 1) % 4;
			}
		}
	}

	// 토네이도 움직이는 로직
	public static boolean isMoveTornado(Tornado t) {
		int nx = t.x + xrange[t.dir];
		int ny = t.y + yrange[t.dir];

		if (nx >= 0 && ny >= 0 && nx < size && ny < size) { // 이동 가능
			t.x = nx;
			t.y = ny;
			t.sandAmount = arr[nx][ny]; // 해당 위치의 모래 가져오기
			arr[nx][ny] = 0;
		} else // 이동 불가능, 종료
			return false;
		return true;
	}

	// 움직인 이후 모래 계산
	public static void sandCalc(Tornado t) {
		// 움직인 이후가 기준
		for (int i = 0; i < 5; i++)
			System.arraycopy(sandPercentCopy[i], 0, sandPercent[i], 0, 5);

		for (int k = 0; k < t.dir; k++) { // dir 에 맞게 회전
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tempSand[(5 - 1) - j][i] = sandPercent[i][j];
				}
			}

			int[][] temps = tempSand;
			tempSand = sandPercent;
			sandPercent = temps;
		}

		// 모래량 계산
		int totalSandAmount = t.sandAmount;
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				int sandPercentX = i + 2; // sand index 맞추기
				int sandPercentY = j + 2;

				if (sandPercent[sandPercentX][sandPercentY] == -1)
					continue;

				// 흩날리는 모래량
				int locSand = (int) (t.sandAmount * (sandPercent[sandPercentX][sandPercentY] * 0.01));

				// 토네이도 위치를 기준으로 조정
				int arrX = t.x + i;
				int arrY = t.y + j;

				totalSandAmount -= locSand;
				if (arrX >= 0 && arrY >= 0 && arrX < size && arrY < size) { // 기존 모래와 합쳐짐
					arr[arrX][arrY] += locSand; // 여기서 업데이트를 너무 쌔게한다.
				} else { // 격자 밖으로 나가는 경우
					answerSandAmount += locSand;
				}
			}
		}

		// 남아있는 모래량 놓아주기
		int nx = t.x + xrange[t.dir];
		int ny = t.y + yrange[t.dir];
		if (nx >= 0 && ny >= 0 && nx < size && ny < size) {
			arr[nx][ny] += totalSandAmount;
		} else {
			answerSandAmount += totalSandAmount;
		}
	}
}

class Tornado {
	int x, y, dir, sandAmount;

	public Tornado(int x, int y, int dir, int prevSandAmount) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.sandAmount = prevSandAmount;
	}
}