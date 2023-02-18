package solved.math;

import java.util.*;
import java.io.*;

/* TODO:
 *
 *
 *
 * */
public class _16563 {

	static int[] arr = new int[5_000_001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 에라토스 체인데, 가장 적은 소인수를 저장함
		for(int i = 2; i<arr.length; i++) {
			if(arr[i] == 0) { 
				arr[i] = i;
				for(int j = i * 2; j<arr.length; j += i) {
					if(arr[j] == 0) {
						arr[j] = i;
					}
				}
			}
		}
		
		int size = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		while(size --> 0) {
			int number = Integer.parseInt(st.nextToken());
			
			// 소인수를 추척하며 number 감소
			while(true) {
				if(arr[number] == number) {
					sb.append(number).append(" ");
					break;
				}else {
					sb.append(arr[number]).append(" ");
					number /= arr[number];
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
