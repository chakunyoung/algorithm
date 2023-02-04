package solved.array.twopointer;


import java.util.*;
import java.io.*;

// 투포인터
// index 관리에 유의
public class _2230 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 차이가 M 이상이면서 제일 작은 경우
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int n1 = n;
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		while(n1-->0)
			list.add(Integer.parseInt(br.readLine()));
		Collections.sort(list);
		
		if(n == 1) {
			System.out.println(0);
			return;
		}
		
		int s = 0;
		int e = 1;
		int gap = Integer.MAX_VALUE;
		
		while(e != list.size()) {
			if(s == e) {
				if(e != list.size() -1) {
					e++;
				}else if(s != list.size() -1)
					s++;
				else
					break;
				continue;
			}  
			
			int sv = list.get(s);
			int ev = list.get(e);
			
			if(ev - sv < m) {
				e++;
			}else if(ev - sv >= m) {
				gap = Math.min(gap, ev - sv);
				s++;
			}
		}
		System.out.println(gap);
	}
}
