package solved.array.twopointer;
import java.util.*;
import java.io.*;

public class _1644 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		list.add(0);
		
		for(int i = 2; i<=n; i++) {
			int sq = (int)Math.sqrt(i);
			boolean flag = false;
			for(int j = 2; j<=sq; j++) {
				if(i % j == 0) {
					flag = true;
					break;
				}
			}
			if(!flag)
				list.add(i);
		}
		
		
		// two pointer
		int s = 0;
		int e = 0;
		int sum = 0;
		int count = 0;
		
		while(s <= e) {
			if(sum == n)
				count++;
			
			// index 제약
			if(s == e) {
				e++;
				if(e == list.size())
					break;
				sum += list.get(e);
				continue;
			}
			
			
			if(sum > n) {
				sum -= list.get(s);
				s++;
			}else {
				e++;
				if(e == list.size())
					break;
				sum += list.get(e);
			}
		}
		System.out.println(count);
	}
}
