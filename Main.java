
import java.util.*;
import java.io.*;

/* TODO
 * 최단 경로를 찾아야하므로 bfs 
 * 
 * Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
 *
 */

public class Main {

	// 위 오 아 왼
	static int[] xdir = { -1, 0, 1, 0 };
	static int[] ydir = { 0, 1, 0, -1 };

	static int[][] arr;
	static int minDistCount = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		arr = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		// 출발 지점과 바라보는 방향
		st = new StringTokenizer(br.readLine());
		int rx = Integer.parseInt(st.nextToken()) - 1;
		int ry = Integer.parseInt(st.nextToken()) - 1;
		int dir = dirInit(Integer.parseInt(st.nextToken()));
		Robot initRobot = new Robot(rx, ry, dir, 0);

		// 도착지
		st = new StringTokenizer(br.readLine());
		int locx = Integer.parseInt(st.nextToken()) - 1;
		int locy = Integer.parseInt(st.nextToken()) - 1;
		int locdir = dirInit(Integer.parseInt(st.nextToken()));
		Robot arrRobot = new Robot(locx, locy, locdir, 0);

		bfs(initRobot, arrRobot);
		System.out.println(minDistCount);
	}

	public static void bfs(Robot initRobot, Robot arrRobot) {
		Queue<Robot> q = new ArrayDeque<>();
		q.offer(initRobot);
		arr[initRobot.x][initRobot.y] = 1;

		while (!q.isEmpty()) {
			Robot r = q.poll();

			// 도착
			if (r.x == arrRobot.x && r.y == arrRobot.y) {
				minDistCount += Math.abs(arrRobot.dir - r.dir) + r.moveDist;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = r.x + xdir[i];
				int ny = r.y + ydir[i];

				if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length && arr[nx][ny] == 0) {
					if (r.dir == i) { // 전진
						forward(r, q);

					} else if ((4 + r.dir - 1) % 4 == i) { // 왼쪽
						q.offer(new Robot(r.x, r.y, (4 + r.dir - 1) % 4, r.moveDist + 1));

					} else if ((r.dir + 1) % 4 == i) { // 오른쪽
						q.offer(new Robot(r.x, r.y, (r.dir + 1) % 4, r.moveDist + 1));

					} else if ((r.dir + 2) % 4 == i) { // 뒤
						q.offer(new Robot(r.x, r.y, (r.dir + 2) % 4, r.moveDist + 2));

					}
				}
			}
			for (int i = 0; i < arr.length; i++)
				System.out.println(Arrays.toString(arr[i]));
			System.out.println();
		}
	}

	public static void forward(Robot r, Queue<Robot> q) {
		for (int i = 1; i <= 3; i++) {
			int nx = r.x + xdir[r.dir] * i;
			int ny = r.y + ydir[r.dir] * i;
			if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length && arr[nx][ny] == 0) {
				Robot rn = new Robot(nx, ny, r.dir, r.moveDist + 1);
				arr[nx][ny] = 1;
				q.offer(rn);
			} else
				break;
		}
	}

	public static int dirInit(int dir) {
		switch (dir) {
		case 4:
			return 0;
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return 0;
	}

	static class Robot {
		int x, y, dir, moveDist;

		public Robot(int x, int y, int dir, int moveDist) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.moveDist = moveDist;
		}
	}
}
