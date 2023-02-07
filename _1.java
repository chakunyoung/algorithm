import java.io.*;
import java.util.*;

public class _1 {
	static int count;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(br.readLine());
		hanoi(1, 2, 3, input);
		System.out.println(count);
		System.out.println(sb.toString());
	}

	public static void hanoi(int from, int mid, int to, int n) {
		if (n == 0)
			return;
		count++;
		hanoi(from, to, mid, n - 1);
		// 위로 올리며 보조, 목표 바꾸는 부분
		sb.append(from + " " + to + "\n");
		hanoi(mid, from, to, n - 1);
		// 보조에 있던걸 목표로 보내기위해 바꾸는 부분
	}
}
