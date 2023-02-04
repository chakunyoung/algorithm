package solved.binarysearch;

import java.util.*;
import java.io.*;

// 이분탐색, 투포인터  n * log n;
public class _2110 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int[] arr = new int[h];
		int n = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		while(h-->0)
			arr[idx++] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		System.out.println(bs(arr, n-1));
	}
	
	public static int bs(int[] arr, int n) {
		int s1 = 0;
		int e1 = 1_000_000_000;
		int count = 0;
		int nn = 0;
		
		while(s1 <= e1) {
			count = 0;
			int mid = (s1+e1) / 2; 
			
			// 투 포인터
			int s = 0;
			int e = 1;
			while(e != arr.length) {
				int arrs = arr[s];
				int arre = arr[e];
				if( arre - arrs >= mid ) {
					s = e;
					e++;
					count++;
				}else {
					e++;
				}
			}
			
			if(count >= n) {
				s1 = mid + 1;
				nn = mid;
			}else 
				e1 = mid - 1;
		}
		return nn;
	}
}
