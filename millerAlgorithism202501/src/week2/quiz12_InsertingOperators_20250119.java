package week2;

import java.util.Scanner;

public class quiz12_InsertingOperators_20250119 {
    static int N;
    static int[] numbers;
    static int[] operators = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈 개수
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        N = sc.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();
        }

        // 백트래킹 시작
        backtrack(1, numbers[0]);

        // 결과 출력
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    // 백트래킹 함수
    public static void backtrack(int idx, int current) {
        if (idx == N) { // 모든 숫자를 사용한 경우
            maxValue = Math.max(maxValue, current);
            minValue = Math.min(minValue, current);
            return;
        }

        for (int i = 0; i < 4; i++) { // 각 연산자를 시도
            if (operators[i] > 0) {
                operators[i]--; // 연산자 사용
                int next = calculate(current, numbers[idx], i);
                backtrack(idx + 1, next);
                operators[i]++; // 연산자 복구
            }
        }
    }

    // 연산을 수행하는 함수
    public static int calculate(int a, int b, int operator) {
        switch (operator) {
            case 0: return a + b; // 덧셈
            case 1: return a - b; // 뺄셈
            case 2: return a * b; // 곱셈
            case 3: return a / b; // 나눗셈
            default: return 0;
        }
    }
}
