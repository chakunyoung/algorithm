import java.util.*;
import java.io.*;
 
class Solution_SWEA_1289_원재의메모리복구하기_D3_차건영_100ms{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
 
        for(int i =1; i<=count; i++) {
            char n = '0'; 
            String arr = br.readLine();
            int amend = 0;
 
            for(int j = 0; j<arr.length(); j++){
                if(!(arr.charAt(j) == n)){
                	amend++;
                    if(n == '0')
                        n = '1';
                    else
                        n = '0';
                }
            }
            System.out.println("#" + i +  " " +amend);
        }
    }
}