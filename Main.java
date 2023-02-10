import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;
// 경쟁상태에서 우선순위가 높은 상어가 이긴다.

// 냄새가 없는 칸을 가장 우선순위
// 다 냄새가 있다면 냄새가 있는 칸(자신의 냄새)를 사용함.
// 자신의 냄새가 없다면 원래 우선순위 사용

// 냄새는 k번 이동시 알아서 소멸

// 1. 움직임 우선순위를 구현. // 객체마다 이중 리스트로 구현
// 2. 냄새의 소멸을 구현. // 시간이 지나면 없어지는

// 2차원 배열을 노드로 만들고
// 큐에 노드를 x, y, time, shark number 를 이용해서 해당 위치를 update
public class Main {

	static Node[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int sharks = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());
		
		arr = new Node[size][size];
		
		for(int i = 0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<size; j++) {
				
				
			}
		}
		
		
		
		
	}
}

class Node {
	int x;
	int y;
	int time;
	boolean isShark;
	List<List<Integer>> move;

	public Node(int x, int y, int time, boolean isShark) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.isShark = isShark;

		if(isShark) {
			move = new ArrayList<>();
			for (int i = 0; i < 4; i++)
				move.add(new ArrayList<>());
		}
	}

	public void moveSet(int m, int m1) {
		move.get(m).add(m1);
	}
}