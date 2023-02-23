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
	private static int[] input; // 사용자 입력받은 숫자 배열
	private static int[] numbers; // 순열에서 뽑은 숫자 담을 배열
	private static boolean[] isSelected; // 사용한 숫자인지 체크할 플래그 배열
	private static int total;

	public static void main(String[] args) {
		System.out.println("알고리즘 연습");
		input(); // 사용할 데이터를 입력받아서 저장하는 메서드
		System.out.println("입력하신 숫자 배열 : " + Arrays.toString(input));

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

		System.out.println("■■■11. 순열 nPn : NextPermutation 사용 ■■■");
		permutation_NextPermutation1();

		System.out.println("■■■12. 순열 nPr : NextPermutation 사용 ■■■");
		total = 0;
		permutation_NextPermutation2();
		System.out.println("개수 : " + total);

		System.out.println("■■■13. 조합 nCr : NextPermutation 사용 ■■■");
		total = 0;
		combination_NextPermutation();
		System.out.println("개수 : " + total);
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

//		System.out.println("■■■14. 부분집합 : 반복문 사용, 배열원소는 {1,2,3} ■■■");
//		subSetLoop(); 
//
//		System.out.println("■■■15. 부분집합 : 재귀 사용 ■■■");
//		total = 0;
//		subSet(0); 
//		System.out.println("개수 : " + total);
//
//		System.out.println("■■■16. 부분집합 : BinaryCounting 사용 ■■■");
//		total = 0;
//		subSetBinaryCounting();
//		System.out.println("개수 : " + total);

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

//		System.out.println("■■■17. 피보나치 N ■■■");
//		System.out.println(N + "항 : " + fibo(N));
//		
//		System.out.println("■■■18. 하노이 재귀 N ■■■");
//		total = 0;
//		hanoi(N, 1, 2, 3);
//		System.out.println("회수 : " + total);
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
		input = new int[N]; // 사용자 입력받은 숫자 배열
		numbers = new int[R]; // 순열에서 뽑은 숫자 담을 배열
		isSelected = new boolean[N]; // 사용한 숫자인지 체크할 플래그 배열
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt(); // 사용자 입력받은 숫자 배열
		}
		sc.close();
	}

	/** 순열 3P3 : 반복문 사용, 배열원소는 {1,2,3}, 만든 순열 출력 */
	public static void permutationLoop1() {
		int[] arr = new int[3];

		for (int i = 0; i < 3; i++) {
			arr[0] = i + 1;
			for (int j = 0; j < 3; j++) {
				if (i == j)
					continue;
				arr[1] = j + 1;
				for (int k = 0; k < 3; k++) {
					if (i == k && i == k)
						continue;
					arr[2] = k + 1;
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}

	/** 중복순열 3π3 : 반복문 사용, 배열원소는 {1,2,3}, 만든 중복순열 출력 */
	public static void permutationLoop2() {
		int[] arr = new int[3];

		for (int i = 0; i < 3; i++) {
			arr[0] = i + 1;
			for (int j = 0; j < 3; j++) {
				arr[1] = j + 1;
				for (int k = 0; k < 3; k++) {
					arr[2] = k + 1;
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}

	/**
	 * 순열 nPr: selected 배열사용, index 위치에 사용하지 않은 숫자를 하나씩 넣어보는 재귀함수 N, R, total :
	 * 전역변수, input배열에서 선택한 값을 numbers배열에 저장, 만든 순열 마지막에 출력
	 */
	public static void permutationSelected1(int index) {
		if (index == R) {
			// System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				numbers[index] = input[i];
				permutationSelected1(index + 1);
				isSelected[i] = false;
			}
		}
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

		for (int i = 0; i < input.length; i++) {
			if ((flag & 1 << i) != (1 << i)) {
				flag |= (1 << i);
				numbers[index] = input[i];
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

		for (int i = 0; i < input.length; i++) {
			numbers[index] = input[i];
			permutationSelected2(index + 1);
		}
	}

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/** 조합 5C3 : 반복문 사용, 배열원소는 {1,2,3,4,5}, 만든 조합 출력 */
	public static void combinationLoop1() {
		int[] arr = new int[3];

		for (int i = 0; i < 5; i++) {
			arr[0] = i + 1;
			for (int j = i + 1; j < 5; j++) {
				arr[1] = j + 1;
				for (int k = j + 1; k < 5; k++) {
					arr[2] = k + 1;
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}

	/** 중복조합 5H3 : 반복문 사용, 배열원소는 {1,2,3,4,5}, 만든 중복조합 출력 */
	public static void combinationLoop2() {
		int[] arr = new int[3];

		for (int i = 0; i < 5; i++) {
			arr[0] = i + 1;
			for (int j = i; j < 5; j++) {
				arr[1] = j + 1;
				for (int k = j; k < 5; k++) {
					arr[2] = k + 1;
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}

	/**
	 * 조합 : nCr = n-1Cr + n-1Cr-1 사용 N, R, total : 매개변수로 받음, input배열에서 선택한 값을
	 * numbers배열에 저장, 만든 조합 마지막에 출력
	 */
	public static void combination1(int n, int r) {

	}

	/**
	 * 조합 : nCr 다음 시작 위치 start 사용 N, R, total : 사용자 입력받은 값(전역변수), input배열에서 선택한 값을
	 * numbers배열에 저장, 만든 조합 마지막에 출력
	 */
	public static void combination2(int index, int start) {
		if (start == R) {
			System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = index; i < N; i++) {
			numbers[start] = i;
			combination2(i + 1, start + 1);
		}
	}

	/**
	 * 중복조합 : nHr 다음 시작 위치 start 사용 N, R, total : 사용자 입력받은 값(전역변수), input배열에서 선택한 값을
	 * numbers배열에 저장, 만든 중복조합 마지막에 출력
	 */
	public static void combination3(int index, int start) {
		if (start == R) {
			System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = index; i < N; i++) {
			numbers[start] = i;
			combination3(i, start + 1);
		}
	}

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/**
	 * 매개변수 arr 배열의 사전순 다음 순열을 만들어주는 메서드, 순열의 마지막이라 다음이 없으면 false 리턴 / 있으면 true 리턴
	 */
	public static boolean nextPermutation(int arr[]) {

		System.out.println(Arrays.toString(arr));
		total++;
		// 꼭대기 앞 수를 찾는다.
		int i = arr.length - 1;
		while (i > 0 && input[i - 1] >= input[i])
			i--;

		if (i == 0)
			return false;

		// 뒤부터, j가 크면 종료
		int j = arr.length - 1;
		while (input[i - 1] >= input[j])
			j--;

		swap(input, i - 1, j);

		// 꼭대기 뒤를 오름차순으로 바꾸기 ( 내림차순임 )
		j = arr.length - 1;
		while (i < j) {
			swap(input, i++, j--);
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
		while (nextPermutation(input)) {

		}
		System.out.println(total);
		total = 0;

	}

	/**
	 * 순열 nPr : NextPermutation 사용, 만든 순열 출력 input, N, R, total : 전역변수
	 */
	
	// 뒤를 내림차순으로 만들어둔다.
	public static void permutation_NextPermutation2() {
		Arrays.sort(input);

		do {
			for (int i = 0; i < 3; i++)
				System.out.print(input[i] + " ");
			System.out.println();

			int i2 = N-1;
			int i1 = R;
			while (i1 < i2) {
				swap(input, i1++, i2--);
			}

		} while (nextPermutation(input));
	}

	/**
	 * 조합 nCr : NextPermutation 사용, 만든 조합 출력 input, N, R, total : 전역변수
	 */
	
	// 
	public static void combination_NextPermutation() {
		Arrays.sort(input);
		
		int[] arr = new int[N];
		for(int i = R; i<N; i++) {
			arr[i] = 1;
		}
		
		do {
			for (int i = 0; i < input.length; i++) {
				if(arr[i] == 0)
					System.out.print(input[i] + " ");
			}
			System.out.println();

			int i2 = N-1;
			int i1 = R;
			while (i1 < i2) {
				swap(input, i1++, i2--);
			}
			
		} while (nextPermutation(arr));
	}
	
	String str = "";

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/** 부분집합 : 반복문 사용, 배열원소는 {1,2,3} */
	public static void subSetLoop() {
		int[] arr;

		for (int i = 0; i <= 3; i++) {
			arr = new int[i];

			if (i == 0) {
				System.out.println(Arrays.toString(arr));
			} else if (i == 1) {
				for (int j = 0; j < 3; j++) {
					arr[0] = j + 1;
					System.out.println(Arrays.toString(arr));
				}

			} else if (i == 2) {
				for (int j = 0; j < 3; j++) {
					arr[0] = j + 1;
					for (int k = j + 1; k < 3; k++) {
						arr[1] = k + 1;
						System.out.println(Arrays.toString(arr));
					}
				}

			} else if (i == 3) {
				for (int j = 0; j < 3; j++) {
					arr[0] = j + 1;
					for (int k = j + 1; k < 3; k++) {
						arr[1] = k + 1;
						for (int q = k + 1; q < 3; q++) {
							arr[2] = q + 1;
							System.out.println(Arrays.toString(arr));
						}
					}
				}
			}
		}
	}

	/**
	 * 부분집합 : 재귀 사용 isSelected, input, N, total : 전역변수
	 */
	public static void subSet(int index) {
		if (index == R) {
			total++;
			for (int i = 0; i < input.length; i++) {
				if (isSelected[i])
					System.out.print(input[i] + " ");
			}
			System.out.println();
			return;
		}

		subSet(index + 1);
		isSelected[index] = true;
		subSet(index + 1);
		isSelected[index] = false;
	}

	/**
	 * 부분집합 : BinaryCounting 사용 input, N, total : 전역변수
	 */
	public static void subSetBinaryCounting() {
		Arrays.sort(input);
		int v = 0;

		while ((v & (1 << N)) != (1 << N)) {
			for (int i = 0; i < N; i++) {
				if ((v & (1 << i)) == (1 << i)) {
					System.out.print(input[i] + " ");
				}
			}
			v++;
			System.out.println();
			total++;
		}
	}

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/**
	 * 피보나치 n항을 계산하여 리턴 n : 매개변수 원반의 개수
	 */
	public static long fibo(int n) {
		if (n == 1)
			return 0;
		else if (n == 2)
			return 1;
		return fibo(n - 1) + fibo(n - 2);
	}

	/**
	 * 하노이탑, from 기둥에 쌓여있는 원반들을 to 기둥으로 이동시키는 과정을 출력 조건, 작은 원반 위에 큰 원반이 올라갈 수 없다.
	 * 매개변수 : index-최초 원반갯수, from-시작 기둥, temp-임시 기둥, to-도착 기둥
	 */
	private static void hanoi(int index, int from, int temp, int to) {
		if (index == 1)
			return;

		hanoi(index - 1, from, to, temp);
		total++;
		hanoi(index - 1, temp, from, to);
	}
} // end of class
