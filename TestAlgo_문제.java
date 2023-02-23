import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 알고리즘 퀴즈 [input] 5 3 1 2 3 4 5
 * 
 * 5P3 순열 : 60개 5π3 중복순열 : 125개 5C3 조합 : 10개 5H3 중복조합 : 35개 2^5 부분집합 : 32개 피보나치
 * 5항 : 5 하노이5개회수: 31회
 * 
 * @author 서민규
 * @since 2023.02.22
 */
public class TestAlgo_문제 {
	private static int N;
	private static int R;
	private static int[] arr; // 사용자 입력받은 숫자 배열
	private static int[] numbers; // 순열에서 뽑은 숫자 담을 배열
	private static boolean[] isSelected; // 사용한 숫자인지 체크할 플래그 배열
	private static int total;

	public static void main(String[] args) {
		System.out.println("알고리즘 연습");
		input(); // 사용할 데이터를 입력받아서 저장하는 메서드
		System.out.println("입력하신 숫자 배열 : " + Arrays.toString(arr));

//		System.out.println("■■■01. 순열 3P3 : 반복문 사용, 배열원소는 {1,2,3} ■■■");
//		permutationLoop1();
//
//		System.out.println("■■■02. 중복순열 3π3 : 반복문 사용, 배열원소는 {1,2,3} ■■■");
//		permutationLoop2();
//
//		System.out.println("■■■03. 순열 nPr: selected 배열사용 ■■■");
//		total = 0;
//		permutationSelected1(0);
//		System.out.println("개수 : " + total);
//
//		System.out.println("■■■04. 순열 nPr: selected 배열 대신 flag 비트마스킹 사용 ■■■");
//		total = 0;
//		permutationSelectedBitMaking(0, 0);
//		System.out.println("개수 : " + total);
//
//		System.out.println("■■■05. 중복순열 nπr : selected 배열사용 ■■■");
//		total = 0;
//		permutationSelected2(0);
//		System.out.println("개수 : " + total);

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

//		System.out.println("■■■06. 조합 5C3 : 반복문 사용, 배열원소는 {1,2,3,4,5} ■■■");
//		combinationLoop1();
//
//		System.out.println("■■■07. 중복조합 5H3 : 반복문 사용, 배열원소는 {1,2,3,4,5} ■■■");
//		combinationLoop2();

		System.out.println("■■■08. 조합 : nCr = n-1Cr + n-1Cr-1 사용 ■■■");
		total = 0;
		combination1(N, R);
		System.out.println("개수 : " + total);

//		System.out.println("■■■09. 조합 : nCr 다음 시작 위치 start 사용 ■■■");
//		total = 0;
//		combination2(0, 0);
//		System.out.println("개수 : " + total);
//
//		System.out.println("■■■10. 중복조합 : nHr 다음 시작 위치 start 사용 ■■■");
//		total = 0;
//		combination3(0, 0);
//		System.out.println("개수 : " + total);

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

//		System.out.println("■■■11. 순열 nPn : NextPermutation 사용 ■■■");
//		permutation_NextPermutation1();
//
//		System.out.println("■■■12. 순열 nPr : NextPermutation 사용 ■■■");
//		total = 0;
//		permutation_NextPermutation2();
//		System.out.println("개수 : " + total);
//
//		System.out.println("■■■13. 조합 nCr : NextPermutation 사용 ■■■");
//		total = 0;
//		combination_NextPermutation();
//		System.out.println("개수 : " + total);
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

//		System.out.println("■■■14. 부분집합 : 반복문 사용, 배열원소는 {1,2,3} ■■■");
//		subSetLoop();

//		System.out.println("■■■15. 부분집합 : 재귀 사용 ■■■");
//		total = 0;
//		subSet(0); 
//		System.out.println("개수 : " + total);

//		System.out.println("■■■16. 부분집합 : BinaryCounting 사용 ■■■");
//		total = 0;
//		subSetBinaryCounting();
//		System.out.println("개수 : " + total);

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	} // end of main

	/** 사용할 데이터를 입력받아서 저장하는 메서드 */
	public static void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("■■■ N, R, N개의 숫자배열을 입력해주세요 ■■■");
		System.out.print("N : ");
		N = sc.nextInt();
		System.out.print("R : ");
		R = sc.nextInt();

		System.out.println("N개의 숫자배열 : ");
		arr = new int[N]; // 사용자 입력받은 숫자 배열
		numbers = new int[R]; // 순열에서 뽑은 숫자 담을 배열
		isSelected = new boolean[N]; // 사용한 숫자인지 체크할 플래그 배열
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt(); // 사용자 입력받은 숫자 배열
		}
		sc.close();
	}

	/**
	 * 순열 nPr: selected 배열 대신 flag 비트마스킹 사용, index 위치에 사용하지 않은 숫자를 하나씩 넣어보는 재귀함수 N,
	 * R, total : 전역변수, input배열에서 선택한 값을 numbers배열에 저장, 만든 순열 마지막에 출력
	 */
	public static void permutationSelectedBitMaking(int index, int flag) {
		if (index == R) {
			// System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if ((flag & 1 << i) != (1 << i)) {
				flag |= (1 << i);
				numbers[index] = arr[i];
				permutationSelectedBitMaking(index + 1, flag);
				flag &= ~(1 << i);
			}
		}
	}

	/**
	 * 중복순열 nπr : selected 배열사용, index 위치에 사용하지 않은 숫자를 하나씩 넣어보는 재귀함수 N, R, total :
	 * 전역변수, input배열에서 선택한 값을 numbers배열에 저장, 만든 중복순열 마지막에 출력
	 */
	public static void permutationSelected2(int index) {
		if (index == R) {
			// System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			numbers[index] = arr[i];
			permutationSelected2(index + 1);
		}
	}

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/**
	 * 조합 : nCr = n-1Cr + n-1Cr-1 사용 N, R, total : 매개변수로 받음, input배열에서 선택한 값을 하나를
	 * 포함해 뽑은 경우 n-1Cr-1, 하나를 포함하지 않고 뽑은 경우 n-1Cr
	 * 
	 * numbers배열에 저장, 만든 조합 마지막에 출력
	 */
	public static void combination1(int n, int r) {
		if (r == 0 || n == r) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		numbers[r - 1] = n;
		combination1(n - 1, r - 1);
		numbers[r - 1] = n;
		combination1(n - 1, r);
	}

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/**
	 * 매개변수 arr 배열의 사전순 다음 순열을 만들어주는 메서드, 순열의 마지막이라 다음이 없으면 false 리턴 / 있으면 true 리턴
	 */
	public static boolean nextPermutation(int[] arr) {

		System.out.println(Arrays.toString(arr));
		total++;
		// 꼭대기 앞 수를 찾는다.
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0)
			return false;

		// 뒤부터, j가 크면 종료
		int j = arr.length - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		swap(arr, i - 1, j);

		// 꼭대기 뒤를 오름차순으로 바꾸기 ( 내림차순임 )
		// 초기화 역할
		j = arr.length - 1;
		while (i < j) {
			swap(arr, i++, j--);
		}
		return true;
	}

	/** arr 배열의 i, j 인덱스 위치의 값을 교환 */
	public static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 순열 nPn : NextPermutation 사용, 만든 순열 출력 input : 전역변수
	 */
	public static void permutation_NextPermutation1() {
		while (nextPermutation(arr)) {

		}
		System.out.println(total);
		total = 0;

	}

	/**
	 * 순열 nPr : NextPermutation 사용, 만든 순열 출력 input, N, R, total : 전역변수
	 */

	// 뒤를 내림차순으로 만들어둔다.
	public static void permutation_NextPermutation2() {
		Arrays.sort(arr);

		do {
			for (int i = 0; i < 3; i++)
				System.out.print(arr[i] + " ");
			System.out.println();

			int i2 = N - 1;
			int i1 = R;
			while (i1 < i2) {
				swap(arr, i1++, i2--);
			}

		} while (nextPermutation(arr));
	}

	/**
	 * 조합 nCr : NextPermutation 사용, 만든 조합 출력 input, N, R, total : 전역변수
	 */

	// 정렬된 input은 고정시키고, 1 과 0 로 선택한다.
	public static void combination_NextPermutation() {
		Arrays.sort(arr);

		int[] arr11 = new int[N];
		for (int i = R; i < N; i++) {
			arr11[i] = 1;
		}

		do {
			for (int i = 0; i < arr11.length; i++) {
				if (arr11[i] == 0)
					System.out.print(arr[i] + " ");
			}
			System.out.println();
		} while (nextPermutation(arr11));
	}

	String str = "";

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/** 부분집합 : 반복문 사용, 배열원소는 {1,2,3} */
	public static void subSetLoop() {
		int[] arr = { 1, 2, 3 };

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					System.out.print(i == 0 ? "" : arr[0]);
					System.out.print(j == 0 ? "" : arr[1]);
					System.out.print(k == 0 ? "" : arr[2]);
					System.out.println();
				}
			}
		}
	}

	/**
	 * 부분집합 : BinaryCounting 사용 input, N, total : 전역변수
	 */
	public static void subSetBinaryCounting() {
		Arrays.sort(arr);
		int v = 0;

		while ((v & (1 << N)) != (1 << N)) {
			for (int i = 0; i < N; i++) {
				if ((v & (1 << i)) == (1 << i)) {
					System.out.print(arr[i] + " ");
				}
			}
			v++;
			System.out.println();
			total++;
		}
	}
} // end of class
