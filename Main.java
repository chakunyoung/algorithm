import java.util.*;
import java.io.*;

// answer = 격자밖으로 나간 모래의 양
// arr 범위를 벗어나면, 나간 것

// 토네이도
// 2번 움직이고 이동량 증가
// 이동량 전부 소모하면, dir 변화

public class Main {

	static int[][] arr;
	static int size;
	static int answerSandAmount;

	static int[] xrange = { 0, 1, 0, -1 };
	static int[] yrange = { -1, 0, 1, 0 };

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

		while (isMoveTornado(t)) {
			
			for (int i = 0; i < size; i++) {
				System.out.println(Arrays.toString(arr[i]));
			}
			
			// 모래량 계산
			sandCalc(t);

			// 토네이도 남은 모래 update
			updateSandLoc(t);

			// dir 회전로직
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
			t.sandAmount = arr[nx][ny];
			arr[nx][ny] = 0;
			System.out.println("sand amount update" + t.sandAmount + " " + nx + " " + ny + " ");
		} else // 이동 불가능, 종료
			return false;
		return true;
	}

	// 이동하는 모래의 양 위치 * 비율
	// 한칸 밀려나는 모래의 양 현재 모래의 양 - (위치 * 비율)
	public static void sandCalc(Tornado t) {
		int dir = t.dir;

		// 움직인 이후가 기준
		int[][] sandPercent = { { -1, -1, 2, -1, -1 }, { -1, 10, 7, 1, -1 }, { 5, -1, -1, -1, -1 },
				{ -1, 10, 7, 1, -1 }, { -1, -1, 2, -1, -1 } };

		int[][] tempSand = new int[5][5];
		for (int k = 0; k < dir; k++) { // dir 에 맞게 회전
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tempSand[(size - 1) - j][i] = sandPercent[i][j];
				}
			}

			int[][] temps = tempSand;
			tempSand = sandPercent;
			sandPercent = temps;
		}

		// 모래량 계산
		int totalSandAmount = t.sandAmount;
		System.out.println(totalSandAmount);
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				int sandPercentX = i + 2;
				int sandPercentY = j + 2;

				if (sandPercent[sandPercentX][sandPercentY] == -1)
					continue;

				int arrX = t.x + i;
				int arrY = t.y + j;

				// 해당 위치의 모래량
				int locSand = t.sandAmount * sandPercent[sandPercentX][sandPercentY] / 100;

				if (arrX >= 0 && arrY >= 0 && arrX < size && arrY < size) { // 기존 모래와 합쳐짐
					arr[arrX][arrY] += locSand;
					totalSandAmount -= locSand;
				} else { // 격자 밖으로 나감
					answerSandAmount += locSand;
				}
			}
		}

		t.sandAmount = totalSandAmount;
		System.out.println(answerSandAmount);
	}

	public static void updateSandLoc(Tornado t) {
		int nx = t.x + xrange[t.dir];
		int ny = t.y + xrange[t.dir];
		if (nx >= 0 && ny >= 0 && nx < size && ny < size) {
			arr[nx][ny] = t.sandAmount;
		} else {
			answerSandAmount += t.sandAmount;
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