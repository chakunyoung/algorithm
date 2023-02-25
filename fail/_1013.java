package fail;
import java.util.*;
import java.io.*;

// (100+1+ | 01)+
// 1013 contact
public class _1013 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < testCase; i++) {
			String str = br.readLine();

			int idx = 0;
			while (idx < str.length()) {
				int _v1 = v1(str, idx);
				int _v2 = v2(str, idx);
				
				if(_v1 + _v2 == 0)
					break;
				else
					idx = _v1 + _v2;
			}
			System.out.println(idx);
			if(idx == str.length()) {
				sb.append("YES").append("\n");
			}else
				sb.append("NO").append("\n");
			
		}

		System.out.println(sb.toString());
	}

	// 100로 시작, 0, 1이 오면 그 이후는 쭉 1
	public static int v1(String str, int idx) {
		if (idx + 3 > str.length() || !(str.substring(idx, idx + 3).equals("100")))
			return 0;
		int i = idx;
		boolean one = false;
		for (i = idx + 3; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == '1') {
				one = true;
			} else if (c == '0' && one) {
				return i;
			}
		}
		return i;
	}

	// 0101010 ..
	public static int v2(String str, int idx) {
		String s = "01";
		int i = idx;
		for (; i < str.length() - 2; i += 2) {
			if (!(s.equals(str.substring(i, i + 2))))
				break;
		}
		if (idx == i)
			return 0;
		return i;
	}
}