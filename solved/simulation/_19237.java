package solved.simulation;

import java.util.*;
import java.io.*;
// 경쟁상태에서 우선순위가 높은 상어가 이긴다.
// 경쟁상태에서 순위 낮은 상어 제거

public class _19237 {
	static Node[][] arr;
	static List<SmellNode> smells = new LinkedList<>();
	static List<SharkNode> sharks = new ArrayList<>();

	static int k;
	static int time;
	static int finalSharkNumber = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int sharkCount = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		finalSharkNumber = sharkCount;

		// Node 배열 초기화
		arr = new Node[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = new Node(i, j);
			}
		}

		// shark input
		sharks.add(new SharkNode(0, 0, 0, 0));
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n != 0) {
					SharkNode sn = new SharkNode(i, j, n, 0);
					arr[i][j] = sn;
					sharks.add(sn);
				}
			}
		}
		
		Collections.sort(sharks, (o1, o2 ) -> o1.sharkNumber - o2.sharkNumber);
		System.out.println(sharks);
		
		StringTokenizer stDir = new StringTokenizer(br.readLine());
		for(int i = 1; i<sharks.size(); i++) 
			sharks.get(i).dir = Integer.parseInt(stDir.nextToken()) - 1;

		// shark move set
		for (int i = 1; i < sharks.size(); i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++)
					sharks.get(i).setMove(j, Integer.parseInt(st.nextToken()) - 1);
			}
		}
		// input end

		q();
		System.out.println(time);
	}

	public static void q() {
		Queue<SharkNode> q = new LinkedList<>();
		
		for(int i = 1; i<sharks.size(); i++) 
			q.offer(sharks.get(i));

		// 마지막 상어가 오면 1회전이 돈 것
		while (!q.isEmpty()) {
			SharkNode sn = q.poll();
			// 움직일 빈 공간이 있는지 확인한다.
			// 빈 공간으로 자신의 우선순위에 따라 이동한다.
			// 없다면 자신의 smell로 이동한다.
			if (!sharkMove(sn, 0))
				sharkMove(sn, 1);
			
			q.offer(sn);
			
			//System.out.println(sharks + " --");
			
			// 1회전 종료 // time++; // smell을 남긴다. // smellLifeCycle - 1
			if (sn.sharkNumber == finalSharkNumber) {
				// obs 1 --
				decrSmellNode();
				removeShark();
				createSmellNode();
				time++;
				
				System.out.println(sharks);
				
				if(sharks.size() == 2)
					return;
				
			}
		} // end q
	}
	
	public static void removeShark() {
		for(int i = 1; i<sharks.size() - 1; i++) {
			SharkNode sn1 = sharks.get(i);
			for(int j = i+1; j<sharks.size(); j++) {
				SharkNode sn2 = sharks.get(j);
				if(sn1.x == sn2.x && sn1.y == sn2.y) {
					if(sn1.sharkNumber > sn2.sharkNumber) 
						sharks.remove(sn1);
					else
						sharks.remove(sn2);
				}
			}
		}
	}
	
	
	// 상어의 현재 위치에 생성
	public static void createSmellNode() {
		for (SharkNode sn : sharks) {
			SmellNode smn = new SmellNode(sn.x, sn.y, sn.sharkNumber, k);
			arr[sn.x][sn.y] = smn;
			smells.add(smn);
		}
	}

	public static void decrSmellNode() {
		Iterator<SmellNode> it = smells.iterator();
		while (it.hasNext()) {
			SmellNode smn = (SmellNode) it.next();
			if (smn.k >= 2)
				smn.k--;
			else {
				arr[smn.x][smn.y] = new Node(smn.x, smn.y);
				it.remove();
			}
		}
	}

	public static boolean sharkMove(SharkNode sn, int check) {
		int[] xRange = { -1, 1, 0, 0 };
		int[] yRange = { 0, 0, -1, 1 };
		List<Integer> nowdir = sn.moveList.get(sn.dir);

		for (int dir : nowdir) {
			int nx = sn.x + xRange[dir];
			int ny = sn.y + yRange[dir];

			if (check == 0) {
				if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr.length) {
					if (!(arr[nx][ny] instanceof SmellNode)) {
						sn.x = nx;
						sn.y = ny;
						sn.dir = dir;
						arr[nx][ny] = sn;
						return true;
					}
				}
			} else {
				if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr.length) {
					SmellNode smn = (SmellNode) arr[nx][ny];
					if (sn.sharkNumber == smn.sharkNumber) {
						sn.x = nx;
						sn.y = ny;
						sn.dir = dir;
						arr[nx][ny] = sn;
						return true;
					}
				}
			}
		}
		return false;
	}
}

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class SharkNode extends Node {
	int sharkNumber;
	int dir;
	List<List<Integer>> moveList = new ArrayList<>();

	public SharkNode(int x, int y, int sharkNumber, int dir) {
		super(x, y);
		this.sharkNumber = sharkNumber;
		this.dir = dir;

		for (int i = 0; i < 4; i++)
			moveList.add(new ArrayList<>());
	}

	public void setMove(int row, int col) {
		moveList.get(row).add(col);
	}
	
	public String toString() {
		return (this.x+1) + "," + (this.y+1) + " sn" +sharkNumber + " " + dir;
	}
	
}

class SmellNode extends Node {
	int sharkNumber;
	int k;

	public SmellNode(int x, int y, int sharkNumber, int k) {
		super(x, y);
		this.sharkNumber = sharkNumber;
		this.k = k;
	}
}
