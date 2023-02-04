package solved.recursion.divideandconquer;

import java.util.*;
import java.io.*;

// 분할정복
public class _14719 {
	static int sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		if (w == 1) {
			System.out.println(0);
			return;
		}

		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens())
			list.add(Integer.parseInt(st.nextToken()));
		
		DandC(0, list.size()-1, list);
		System.out.println(sum);
	}
	
	public static void DandC(int s, int e, List<Integer> list) {
		if(e - s <= 1)
			return;
		
		int fMax = 0;
		int fIdx = 0;
		int sMax = 0;
		int sIdx = 0;
		
		for(int i = s; i<=e; i++) {
			if(list.get(i) > fMax) {
				fMax = list.get(i);
				fIdx = i;
			}
		}
		
		for(int i = s; i<=e; i++) {
			if(list.get(i) > sMax && fMax >= sMax && i != fIdx) {
				sMax = list.get(i);
				sIdx = i;
			}
		}
		
		int temp = 0;
		if(fIdx > sIdx) {
			temp = fIdx;
			fIdx = sIdx;
			sIdx = temp;
		}
		
		int min = Math.min(fMax, sMax);
		
		for(int i = fIdx + 1; i<sIdx; i++) {
			if(min - list.get(i) > 0)
				sum += min - list.get(i);
		}
		
		DandC(s, fIdx, list);
		DandC(sIdx, e, list);
	}
}