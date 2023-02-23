import java.util.*;
import java.io.*;

/* TODO:
 * 
 2^n 끼리 합쳐짐 (같은 수 끼리 합쳐짐)
 1time 에 한번 합쳐진다.
 
 5번 이동시켜서 가장 큰 블록을 출력
 
 dir 4번 재귀
 재귀 종료시 위치 초기화
 
 * */
public class _2048 {

	static int[] xdir = {-1, 0, 1, 0};
	static int[] ydir = {0, 1, 0, -1};
	
	// 이동 방향 벽과 가장 가까운 부분이 우선순위
	// dir 0 => 위에서 아래로 탐색
	// dir 1 => 오른쪽에서 왼쪽으로 탐색
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		
		
		
	}
}
