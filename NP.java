
import java.util.Arrays;
import java.util.Scanner;

public class NP {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		// 전처리 : 오름차순 정렬
		Arrays.sort(input);

		do {
			System.out.println(Arrays.toString(input));
		} while (np(input));

	}

	public static boolean np(int[] input) {
		int n = input.length;

		// step1. 뒤쪽부터 꼭대기를 찾는다. (꼭대기 바로 앞이 교환할 자리)
		// 현재값보다 앞 값이 작으면 교환할 자리
		int i = n - 1;
		while (i > 0 && input[i - 1] >= input[i])
			i--;
		if (i == 0)
			return false;

		// step2. 꼭대기 바로 앞(i-1)자리에 교환할 값을 뒤쪽부터 찾는다.
		// j가 크면 종료
		int j = n - 1;
		while (input[i - 1] >= input[j])
			j--;

		// step3. 꼭대기 바로 앞(i-1)자리와 그 자리값과 한단계 큰 자리(j) 수와 교환
		// i j  교환
		swap(input, i - 1, j);

		// step4. "꼭대기"부터 맨 뒤까지 오름차순으로 정렬
		j = n - 1;
		while (i < j) {
			swap(input, i++, j--);
		}
		return true;
	}

	public static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}
