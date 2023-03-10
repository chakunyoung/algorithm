package solved.simulation;
import java.util.*;
import java.io.*;

/*
 * T초간
 *  - 미세먼지가 확산된다.
 * 		-> 해당 위치에 미세먼지가 있다면 합산된다.
 * 
 *  - 공기청정기가 작동한다.
 * 		-> 둘레를 다 지운다.
 */

public class 미세먼지안녕 {

	static int row;
	static int col;
	static int time;

	static int arr[][];

	static Queue<Dust> q = new ArrayDeque<>();
	static List<AirCondition> acList = new ArrayList<>();

	// 1부터 시계 방향
	static int[] xdir = { -1, 0, 1, 0 };
	static int[] ydir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		arr = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == -1) { // 공기 청정기
					arr[i][j] = -1;
					acList.add(new AirCondition(i, j));
				} else if (n != 0) { // 미세먼지
					arr[i][j] = n;
				}
			}
		}

		// proc
		while (time-- > 0) {
			mergeDust();
			dustMove();
			airConditionMove();
		}

		
		//debug();
		// answer
		int answer = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (arr[i][j] == -1)
					continue;
				answer += arr[i][j];
			}
		}
		System.out.println(answer);
	}

	// Q -> Map
	public static void dustMove() {
		while (!q.isEmpty()) {
			Dust d = q.poll();
			int minusAmount = 0;

			for (int i = 0; i < 4; i++) {
				int nx = d.x + xdir[i];
				int ny = d.y + ydir[i];

				if (!(nx >= 0 && ny >= 0 && nx < row && ny < col) || arr[nx][ny] == -1)
					continue;

				arr[nx][ny] += d.amount / 5;
				minusAmount += d.amount / 5;
			}

			arr[d.x][d.y] += d.amount - minusAmount;
		}
	}

	// Map -> Q
	// 미세먼지 4이하는 저장할 필요 없다.
	public static void mergeDust() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (arr[i][j] >= 5) { // 4이하는 퍼지지 않는다.
					q.offer(new Dust(i, j, arr[i][j]));
					arr[i][j] = 0;
				}
			}
		}
	}

	// 미세먼지의 바람대로 한칸씩 이동
	// 공기청정기에 닿이면 없어진다.
	public static void airConditionMove() {
		int dir = 1;
		int prevTemp = 0;
		int temp = 0;
		int x = 0;
		int y = 0;

		// 윗 방향
		AirCondition ac1 = acList.get(0);

		dir = 1; // 오, 위, 왼, 아래
		x = ac1.x;
		y = 1;
		while (arr[x][y] != -1) {
			temp = arr[x][y];
			arr[x][y] = prevTemp;

			int nx = x + xdir[dir];
			int ny = y + ydir[dir];
			if (!(nx >= 0 && ny >= 0 && nx < row && ny < col)) {
				dir = (4 + (dir - 1)) % 4;

				nx = x + xdir[dir];
				ny = y + ydir[dir];
			}

			prevTemp = temp;

			x = nx;
			y = ny;
		}

		// 아랫 방향
		AirCondition ac2 = acList.get(1);
		prevTemp = 0;
		temp = 0;
		dir = 1; // 오, 아, 왼, 위 ++
		x = ac2.x;
		y = 1;
		while (arr[x][y] != -1) {
			temp = arr[x][y];
			arr[x][y] = prevTemp;

			int nx = x + xdir[dir];
			int ny = y + ydir[dir];
			if (!(nx >= 0 && ny >= 0 && nx < row && ny < col)) {
				dir = ((dir + 1)) % 4;

				nx = x + xdir[dir];
				ny = y + ydir[dir];
			}

			prevTemp = temp;

			x = nx;
			y = ny;
		}
	}

	static void debug() {
		for (int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	static class AirCondition {
		int x, y;

		public AirCondition(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Dust {
		int x, y, amount;

		public Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;

		}
	}
}
