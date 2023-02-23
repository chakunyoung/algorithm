import java.util.*;
import java.io.*;

/*
 * 1. 조합으로 사다리 위치 -
 * 2. 사다리 배치할 수 있는지 확인.
 * 3. 배치한 사다리 col 개수만큼 col N -> N 으로 가는지 확인
 * 4. 된다면 출력, 3개까지가 안된다면 -1
 * 
 * 
 * 
 */

public class _15684 {
	static int[] ladderPos;
	static int[] temp;
	
	static int row;
	static int col;

	static boolean[][] ladder;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		col = Integer.parseInt(st.nextToken()) ;
		int ladderCount = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken()) ;

		ladderPos = new int[row * col]; // 조합에 활용
		ladder = new boolean[row + 1][col + 1];

		while (ladderCount-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			ladder[r][c] = true;
		}

		for (int i = 1; i <= 3; i++) {
			temp = new int[i];
			comb(i, 0, 0);
		}

	}

	public static void comb(int limit, int n, int idx) {
		if (limit == n) {
			for (int i = 0; i < limit; i++) {
				int tempRowLadder = temp[i] / col + 1;
				int tempColLadder = temp[i] % col + 1;
				
				// 사다리가 접한다.
				if (ladder[tempRowLadder][tempColLadder])
					return;
				
				// 사다리가 연속된다.
				if (ladder[tempRowLadder][tempColLadder - 1] || (col > tempColLadder && ladder[tempRowLadder][tempColLadder + 1])) {
					return;
				}
			}
			
			for (int i = 0; i < limit; i++) {
				int tempRowLadder = temp[i] / col + 1;
				int tempColLadder = temp[i] % col + 1;
				ladder[tempRowLadder][tempColLadder] = true;
			}
			
			for(int i = 0; i<ladder.length; i++) {
				System.out.println(Arrays.toString(ladder[i]));
			}
			
			// col 번호 출발 -> 도착 로직
			
			
			
			
			
			
			// 모두 자신의 번호로 도착하면 답
			
			
			
			for (int i = 0; i < limit; i++) {
				int tempRowLadder = temp[i] / col + 1;
				int tempColLadder = temp[i] % col + 1;
				ladder[tempRowLadder][tempColLadder] = false;
			}
			
			return;
		}

		for (int i = idx; i < ladderPos.length; i++) {

			temp[n] = i;
			comb(limit, n + 1, i + 1);

		}
	}
}
