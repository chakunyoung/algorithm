
import java.util.*;
import java.io.*;

public class Main {
	static int[] arr;
	static int need;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int line = Integer.parseInt(st.nextToken());
		need = Integer.parseInt(st.nextToken());
		
		arr = new int[line];
		
		int idx= 0;
		while(line -- > 0) 
			arr[idx++] = Integer.parseInt(br.readLine());
		
		System.out.println( ps(1, Integer.MAX_VALUE ) );
		
	}
	
	public static int ps(long s, long e) {
		int answer = 0;	
		
		while(s <= e) {
			long mid = (int) ((s + e) / 2);
			
			// 기준 mid로 잘라보기
			int count = 0;
			for(int line : arr) {
				count += line / mid; 
			}
			
			if(count >= need) { // 많다.
				answer = (int) mid; // 현재까지 최적이므로, 저장
				s = mid + 1; // 최적의 cm를 찾아야 하므로 더 올려본다.
				
				// ** mid가 Integer.MAX_VALUE일 때 -2147483648 로 넘어간다.  
				
			}else if(count < need) { // 부족.
				e = mid - 1; // e 값을 내려본다.
				
			} 
		}
		
		return answer;
	}
}
