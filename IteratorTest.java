import java.util.*;
import java.io.*;
 
class IteratorTest{
    public static void main(String args[]) throws Exception{
        
    	List<Integer> list = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    	
    	
    	Iterator<Integer> it1 = list.iterator();
    	Iterator<Integer> it2 = list.iterator();
    	
    	
    	while(it1.hasNext()) {
    		int n1 = it1.next();
    		while(it2.hasNext()) {
    			int n2 = it2.next();
    			System.out.println(n1 + " " + n2);
    			
    		}
    	}
    	System.out.println(list);
    }
}