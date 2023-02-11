package solved.simulation;

import java.util.*;
import java.io.*;

/*
  1. 구현해야할 액션(메서드) 정하기
  2. 메서드 순서 정하기
 */
public class _19237 {
	static Node[][] arr;
	static List<Node> smells = new LinkedList<>();
	static List<SharkNode> sharks = new LinkedList<>();

	static int setTime;
	static int answerTime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int sharkCount = Integer.parseInt(st.nextToken());
		setTime = Integer.parseInt(st.nextToken());

		arr = new Node[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				arr[i][j] = new Node(i, j);
		}

		sharks.add(new SharkNode(-1, -1, -1, -1)); // index 0번 사용 안함

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				int sharkNumber = Integer.parseInt(st.nextToken());
				if (sharkNumber != 0)
					sharks.add(new SharkNode(i, j, sharkNumber, 0));
			}
		}
		Collections.sort(sharks, (o1, o2) -> o1.sharkNumber - o2.sharkNumber);

		StringTokenizer startSharkDir = new StringTokenizer(br.readLine());
		for (int i = 1; i < sharks.size(); i++)
			sharks.get(i).dir = Integer.parseInt(startSharkDir.nextToken()) - 1;

		// shark set move
		for (int i = 1; i < sharks.size(); i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++)
					sharks.get(i).setMoveList(j, Integer.parseInt(st.nextToken()) - 1);
			}
		}

		q();

		System.out.println(answerTime);
	}

	public static void q() {
		Queue<SharkNode> q = new LinkedList<>();

		// 초기 조건
		for (int i = 2; i < sharks.size(); i++)
			q.offer(sharks.get(i));
		q.offer(sharks.get(1));
		createSmellNode();

		while (!q.isEmpty()) {
			SharkNode shark = q.poll();
			if (!shark.survive)
				continue;

			// 상어 이동
			if (!sharkMove(shark, 0))
				sharkMove(shark, 1);

			q.offer(shark);

			// 1번 상어가 큐의 가장 마지막, 1회전 종료 조건
			if (shark.sharkNumber == 1) {
				answerTime++;
				decreaseSmellNode(); // 냄새 시간 감소
				removeDeadShark(); // 상어 제거
				// ->
				createSmellNode(); // 현재 위치 냄새 생성

//				for (Node[] nn : arr) {
//					System.out.println(Arrays.toString(nn));
//				}
//				System.out.println(smells);
//				System.out.println();
//
//				System.out.println(answerTime);

				if (answerTime > 1000) {
					answerTime = -1;
					return;
				}

				if (sharks.size() == 2)
					return;
			}
		}
	}

	// 상어의 현재 위치에 smell 생성, 있다면 재사용
	public static void createSmellNode() {
		for (int i = 1; i < sharks.size(); i++) {
			SharkNode shark = sharks.get(i);
			Node node = arr[shark.x][shark.y];

			if (node.sharkNumber == 0)
				smells.add(node);

			node.sharkNumber = shark.sharkNumber;
			node.time = setTime;
		}
	}

	public static void decreaseSmellNode() {
		Iterator<Node> it = smells.iterator();
		while (it.hasNext()) {
			Node node = it.next();

			if (node.time > 0)
				node.time--;

			if (node.time == 0) {
				node.sharkNumber = 0;
				it.remove();
			}
		}
	}

	// -- index remove 문제
	public static void removeDeadShark() {
		List<SharkNode> removeList = new ArrayList<>();
		for (int i = 1; i < sharks.size() - 1; i++) {
			for (int j = i + 1; j < sharks.size(); j++) {
				SharkNode shark1 = sharks.get(i);
				SharkNode shark2 = sharks.get(j);
				if (shark1.x == shark2.x && shark1.y == shark2.y) {
					removeList.add(shark2);
					shark2.survive = false;
				}
			}
		}
		sharks.removeAll(removeList);
	}

	public static boolean sharkMove(SharkNode shark, int flag) {
		int[] xRange = { -1, 1, 0, 0 };
		int[] yRange = { 0, 0, -1, 1 };
		List<Integer> dirList = shark.moveList.get(shark.dir);

		for (int dir : dirList) {
			int nx = shark.x + xRange[dir];
			int ny = shark.y + yRange[dir];

			if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr.length) {
				if (flag == 0 && arr[nx][ny].sharkNumber == 0) {
					shark.x = nx;
					shark.y = ny;
					shark.dir = dir;
					return true;
				} else if (flag == 1 && arr[nx][ny].sharkNumber == shark.sharkNumber) {
					shark.x = nx;
					shark.y = ny;
					shark.dir = dir;
					return true;
				}
			}
		}
		return false;
	}
}

class Node {
	int x, y;
	int sharkNumber;
	int time;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, int sharkNumber, int time) {
		this.x = x;
		this.y = y;
		this.sharkNumber = sharkNumber;
		this.time = time;
	}

	public String toString() {
		return sharkNumber + "-" + time;
	}
}

class SharkNode extends Node {
	int sharkNumber;
	int dir;
	boolean survive;
	List<List<Integer>> moveList = new ArrayList<>();

	public SharkNode(int x, int y, int sharkNumber, int dir) {
		super(x, y);
		this.sharkNumber = sharkNumber;
		this.dir = dir;
		this.survive = true;

		for (int i = 0; i < 4; i++)
			moveList.add(new ArrayList<>());
	}

	public void setMoveList(int row, int col) {
		moveList.get(row).add(col);
	}
	
	public String toString() {
		return sharkNumber + " ";
	}
}

// 1000 문제인줄 알았는데,
// shark remove가 제대로 안되는 문제

