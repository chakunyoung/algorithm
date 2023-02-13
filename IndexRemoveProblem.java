import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class IndexRemoveProblem {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		// for문안에서 동적으로 변화함
		for (int i = 0; i < list.size();) {
			if (list.get(i) % 2 == 0) {
				list.remove(list.get(i));
				continue;
			}
			System.out.println(list);
			i++;
		}
	}
}
/*
[1, 2, 3, 4, 5, 6, 7]
[1, 3, 4, 5, 6, 7]
[1, 3, 5, 6, 7]
[1, 3, 5, 7]
*/