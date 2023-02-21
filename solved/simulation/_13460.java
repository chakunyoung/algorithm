package solved.simulation;

import java.util.*;
import java.io.*;

/* TODO:
 * 10 번 초과 움직임에도 탈출 못찾으면 종료 // q에서 11번일 때 종료시키면 될듯
 * 기울이면, 그 방향으로 계속 움직이고, W, H 만나면 종료
 *
 * 
 * */
public class _13460 {

	static int[] xdir = { 1, -1, 0, 0 };
	static int[] ydir = { 0, 0, 1, -1 };
	static char[][] arr;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		arr = new char[row][col];
		RB r = new RB(0, 0);
		RB b = new RB(0, 0);

		for (int i = 0; i < row; i++) {
			String str = br.readLine();
			for (int j = 0; j < col; j++) {
				arr[i][j] = str.charAt(j);

				if (arr[i][j] == 'R') {
					r.x = i;
					r.y = j;
				} else if (arr[i][j] == 'B') {
					b.x = i;
					b.y = j;
				}
			}
		}
		q(r, b);
		System.out.println(answer);
	}

	// 움직임 10번이라, 무한루프 돌아도 상관 없다.
	public static void q(RB redins, RB blueins) {
		Queue<Map> q = new LinkedList<>();
		Map m = new Map(redins, blueins, 1);
		q.offer(m);

		while (!q.isEmpty()) {
			Map m1 = q.poll();

			RB red = m1.red;
			RB blue = m1.blue;
			int count = m1.count;

			if (count == 11) {
				answer = -1;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int prevx = red.x;
				int prevy = red.y;
				int prevx1 = blue.x;
				int prevy1 = blue.y;

				// red - blue - red - blue // 충돌이 있어도, 모두 끝까지 다밈
				// 동시에 들어가면 실패 // blue 가 true 이면 안된다.
				boolean isEscape = false;
				boolean isBlueEscape = false;
				if (move(red, i, m1)) {
					isEscape = true;
				}

				if (move(blue, i, m1)) {
					isBlueEscape = true;
				}

				if (move(red, i, m1)) {
					isEscape = true;
				}

				if (move(blue, i, m1)) {
					isBlueEscape = true;
				}

				if (isEscape && !isBlueEscape) {
					answer = count;
					return;
				}
				
				else if(!isEscape && isBlueEscape) { // blue 볼이 먼저 빠지는 경우, 가지치기
					red.x = prevx;
					red.y = prevy;
					blue.x = prevx1;
					blue.y = prevy1;
					continue;
				}

				q.offer(new Map(new RB(red.x, red.y), new RB(blue.x, blue.y), count + 1));
				red.x = prevx;
				red.y = prevy;
				blue.x = prevx1;
				blue.y = prevy1;
			}
		}
	}

	// 이동
	// true 이면 탈출
	public static boolean move(RB rb, int dir, Map m) {
		while (true) {
			int nx = rb.x + xdir[dir];
			int ny = rb.y + ydir[dir];

			if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
				// 벽
				if (arr[nx][ny] == '#')
					break;

				// 구슬이 있는 경우
				if (nx == m.red.x && ny == m.red.y || nx == m.blue.x && ny == m.blue.y) {
					break;
				}

				// 탈출이 있는 경우
				if (arr[nx][ny] == 'O') {
					rb.x = -1;
					rb.y = -1;
					return true;
				}

				// 이동
				rb.x = nx;
				rb.y = ny;
			} else
				break;
		}
		return false;
	}

	static class RB {
		int x, y;

		public RB(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Map {
		RB red;
		RB blue;
		int count;

		public Map(RB red, RB blue, int count) {
			this.red = red;
			this.blue = blue;
			this.count = count;
		}
	}
}
