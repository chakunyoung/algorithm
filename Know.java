
public class Know {
	public static void main(String[] args) {
		int n = 5;
		int[] arr = { 1, 2, 3, 4, 5 };
		int count = 0;
		for (int i = 0; i < (1 << n); i++) {
			System.out.print(count + "번째 : ");
			count++;
			for (int j = 0; j < n; j++) { // 증가하는 i 로 배열 선택을 판단.
				if ((i & 1 << j) != 0) {
					System.out.print(arr[j] + " ");
				}
			}
			System.out.println();
		}
	}
}
