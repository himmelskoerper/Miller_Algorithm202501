package week2;

import java.util.Scanner;

public class quiz12_InsertingOperators_3 {
    static int N; // 숫자의 개수
    static int[] numbers; // 숫자 배열
    static int[] operators = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈의 개수
    static int maxValue = Integer.MIN_VALUE; // 최댓값 저장
    static int minValue = Integer.MAX_VALUE; // 최솟값 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        N = sc.nextInt(); // 숫자의 개수
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt(); // 각 숫자 입력
        }
        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt(); // 연산자 개수 입력
        }

        // 백트래킹 시작
        backtrack(1, numbers[0]);

        // 결과 출력
        System.out.println(maxValue); // 최댓값 출력
        System.out.println(minValue); // 최솟값 출력
    }

    /**
     * 백트래킹 함수
     * @param idx 현재 처리 중인 숫자의 인덱스
     * @param current 현재까지의 계산 결과
     */
    public static void backtrack(int idx, int current) {
        // 모든 숫자를 사용한 경우 결과 업데이트
        if (idx == N) {
            updateMinMax(current);
            return;
        }

        // 연산자 사용하여 다음 단계로 진행
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) { // 사용 가능한 연산자가 있을 경우
                operators[i]--; // 연산자 사용
                int next = calculate(current, numbers[idx], i); // 현재 숫자와 연산 수행
                backtrack(idx + 1, next); // 다음 숫자로 이동
                operators[i]++; // 연산자 복구 (백트래킹)
            }
        }
    }

    /**
     * 최댓값과 최솟값을 갱신하는 함수
     * @param value 현재 계산 결과
     */
    public static void updateMinMax(int value) {
        maxValue = Math.max(maxValue, value); // 최댓값 갱신
        minValue = Math.min(minValue, value); // 최솟값 갱신
    }

    /**
     * 두 숫자와 연산자를 사용해 계산하는 함수
     * @param a 첫 번째 숫자
     * @param b 두 번째 숫자
     * @param operator 연산자 (0: 덧셈, 1: 뺄셈, 2: 곱셈, 3: 나눗셈)
     * @return 계산 결과
     */
    public static int calculate(int a, int b, int operator) {
        switch (operator) {
            case 0: return a + b; // 덧셈
            case 1: return a - b; // 뺄셈
            case 2: return a * b; // 곱셈
            case 3: return divide(a, b); // 나눗셈
            default: throw new IllegalArgumentException("Invalid operator"); // 잘못된 연산자 처리
        }
    }

    /**
     * 나눗셈을 수행하는 함수 (0으로 나누기 방지)
     * @param a 첫 번째 숫자 (피제수)
     * @param b 두 번째 숫자 (제수)
     * @return 정수 나눗셈 결과
     */
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero"); // 0으로 나누는 경우 예외 발생
        }
        return a / b; // 정수 나눗셈 수행
    }
}
