
import java.io.*;
import java.util.*;

public class _1654 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] arr;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ", false);

		int hasLan = Integer.parseInt(st.nextToken());
		int needLan = Integer.parseInt(st.nextToken());

		int count = hasLan;
		arr = new int[count];
		int arridx = 0;
		while (count-- > 0) {
			arr[arridx++] = Integer.parseInt(br.readLine());
		}

		System.out.println(ps(needLan));
	}

	public static long ps(int needLan) {
		long s = 1;
		long e = Integer.MAX_VALUE;
		long mid = 0;
		long cutLine = 0;

		while (s <= e) {
			mid = (s + e) / 2;
			int count = 0;
			// 잘라진 총 개수
			for (int i = 0; i < arr.length; i++)
				count += arr[i] / mid;

			if (count >= needLan) { // 개수가 맞는다. -> 올려봄
				cutLine = mid; // 개수가 맞으면 일단 저장
				s = mid + 1;
			} else
				e = mid - 1;
		}
		return cutLine;
	}
}