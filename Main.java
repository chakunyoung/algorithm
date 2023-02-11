import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
			
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
		
		int a = list.size();
		
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i) % 2== 0) {
				list.remove((Integer) i);
			}
			System.out.println(list + " " + i);
		}
		
		System.out.println(list);
		
	}
}