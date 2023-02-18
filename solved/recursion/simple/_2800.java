package solved.recursion.simple;


import java.util.*;
import java.io.*;

/* TODO:
 * 괄호 인덱스를 
 * 선택하거나 안하거나
 *
 * */
public class _2800 {
	static String str;
	static boolean[] v = new boolean[11];
	static StringBuilder sb = new StringBuilder();
	
	static List<List<Integer>> list;
	static Set<String> strList = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		str = br.readLine();

		// stack 으로 pair 만들어서 저장
		list = new ArrayList<>();
		Stack<Integer> s = new Stack<>();
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '(') {
				s.add(i);
			}else if(str.charAt(i) == ')') {
				List<Integer> l = new ArrayList<>();
				list.add(l);
				l.add(s.pop());
				l.add(i);
			}
		}
		
		rec(list.size(), 0);
		
		List<String>strList1 = new ArrayList<>(strList); // 중복 방지 (((문자)))
		Collections.sort(strList1);
		
		for(int i = 1; i<strList.size(); i++) // 0번 제외
			sb.append(strList1.get(i)).append("\n");
		System.out.println(sb.toString());
	}

	// 없애는 경우, 살리는 경우
	public static void rec(int limit, int n) {
		if (limit == n) {
			boolean[] vvv = new boolean[str.length()];
			
			for(int i = 0; i < list.size(); i++) {
				if(v[i]) { // true를 지운다.
					List<Integer> rl = list.get(i);
					vvv[rl.get(0)] = true;
					vvv[rl.get(1)] = true;
				}
			}
			
			for(int i = 0; i<str.length(); i++) {
				if(!vvv[i])
					sb.append(str.charAt(i));
			}
			
			strList.add(sb.toString());
			sb.setLength(0);
			return;
		}
		
		v[n] = false;
		rec(limit, n + 1);
		v[n] = true;
		rec(limit, n + 1);
	}
}

