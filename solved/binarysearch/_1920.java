package solved.binarysearch;

import java.util.*;
import java.io.*;

// 간단한 이분탐색 문제
public class _1920 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());
		int[] arr = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = 0;
		while (st.hasMoreTokens())
			arr[idx++] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		br.readLine();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			n = bs(arr, n);
			sb.append(n).append("\n");
		}
		System.out.println(sb.toString());

	}

	public static int bs(int[] arr, int find){
		int s = 0;
		int e = arr.length - 1;
		
		while(s <= e) {
			int mid = (s+e)/2;
			if(arr[mid] == find)
				return 1; 
			else if(find > arr[mid]) {
				s = mid + 1;
			}else
				e = mid - 1;
		}
		return 0;
	}
}