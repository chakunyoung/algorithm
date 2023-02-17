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
		
		
		// Iterator
		List<Integer> list1 = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    	Iterator<Integer> it1 = list1.iterator();
    	
    	while(it1.hasNext()) {
    		int n1 = it1.next();
    		if(n1 == 3)
    			it1.remove();
    		Iterator<Integer> it2 = list1.iterator();
    		while(it2.hasNext()) {
    			int n2 = it2.next();
    			System.out.println(n1 + " " + n2);
    			
    		}
    	}
    	System.out.println(list1);
		
		
	}
}
/*
[1, 2, 3, 4, 5, 6, 7]
[1, 3, 4, 5, 6, 7]
[1, 3, 5, 6, 7]
[1, 3, 5, 7]
*/