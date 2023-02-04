package solved.graph.tree;

import java.util.*;
import java.io.*;

public class _1068 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 50 이하
		int nodeSize = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int removeNode = Integer.parseInt(br.readLine());

		int[] childToParent = new int[51];
		Arrays.fill(childToParent, -1);

		List<List<Integer>> parentToChildren = new ArrayList<List<Integer>>();
		for (int i = 0; i <= 50; i++)
			parentToChildren.add(new ArrayList<>());

		int idx = 0;
		int pn = 0;
		while (st.hasMoreTokens()) { // 입력이 부모.
			int parentNumber = Integer.parseInt(st.nextToken());
			childToParent[idx] = parentNumber;
			if (parentNumber == -1 || idx == removeNode) {
				idx++;
				continue;
			}
			parentToChildren.get(parentNumber).add(idx);
			idx++;
			pn = parentNumber;
		}

		parentToChildren.get(removeNode).clear();
		int root = findRootNode(childToParent, pn);
		if (removeNode == root) // edge case 루트를 지울 경우
			System.out.println(0);
		else
			System.out.println(countLeafNode(parentToChildren, root));
	}

	// 자식 -> 부모
	public static int findRootNode(int[] childToParent, int idx) {
		if (childToParent[idx] == -1)
			return idx;
		return findRootNode(childToParent, childToParent[idx]);
	}

	// 부모 -> 자식
	public static int countLeafNode(List<List<Integer>> parentToChildren, int root) {
		Queue<Integer> q = new LinkedList<>();
		int count = 0;
		q.offer(root);

		while (!q.isEmpty()) {
			int nodeNumber = q.poll();
			List<Integer> list = parentToChildren.get(nodeNumber);
			if (list.size() == 0) {// && nodeNumber != root) {
				count++;
				continue;
			}
			for (int n : list) {
				q.offer(n);
			}
		}
		return count;
	}
}//
