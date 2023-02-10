package solved.graph.unionfind;

import java.util.*;
import java.io.*;

// TODO : n solve

// String을 이용한 union-find
public class _4195 {
	static Map<String, String> m;
	static Map<String, Integer> mCount;
	static String pp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 같은 집합일 경우가 있다.

		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			int lines = Integer.parseInt(br.readLine());
			m = new HashMap<>();
			mCount = new HashMap<>();

			// 입력부
			List<String> list = new ArrayList<>();
			while (lines-- > 0) {
				st = new StringTokenizer(br.readLine());
				String p1 = st.nextToken();
				String p2 = st.nextToken();
				m.put(p1, p1);
				m.put(p2, p2);
				mCount.put(p1, 1);
				mCount.put(p2, 1);
				list.add(p1 + " " + p2);
			}

			for (int s = 0; s < list.size(); s++) {
				st = new StringTokenizer(list.get(s));
				String p1 = st.nextToken();
				String p2 = st.nextToken();

				union(p1, p2);
				//System.out.println(m);
				sb.append(mCount.get(find(p1))).append("\n");
			}
		} // end tc
		System.out.println(sb.toString());
	}

	public static String find(String str) {
		if (m.get(str).equals(str)) 
			return str;
		return find(m.get(str));
	}

	public static boolean union(String str1, String str2) {
		String p1 = m.get(find(str1));
		String p2 = m.get(find(str2));
		if (p1.equals(p2)) {
			pp = p1;
			return false;
		}
			
		if (p1.compareTo(p2) <= -1) {
			pp = p1;
			m.put(p2, p1);
			mCount.put(p1, mCount.get(p1) + mCount.get(p2));
		} else {
			pp = p2;
			m.put(p1, p2);
			mCount.put(p2, mCount.get(p1) + mCount.get(p2));
		}
		return true;
	}

}
