package week2;

import java.util.Scanner;

public class quiz12_InsertingOperators_20250119 {
    // 전역 변수 선언
    static int N; // 숫자의 개수
    static int[] numbers; // 입력받은 숫자 배열
    static int[] operators = new int[4]; // 연산자의 개수 저장 (덧셈, 뺄셈, 곱셈, 나눗셈 순)
    static int maxValue = Integer.MIN_VALUE; // 최댓값
    static int minValue = Integer.MAX_VALUE; // 최솟값

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 입력 받기

        N = sc.nextInt(); // 숫자의 개수 입력
        numbers = new int[N]; // 숫자 배열 크기 설정
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt(); // 숫자 입력
        }
        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt(); // 연산자 개수 입력 (덧셈, 뺄셈, 곱셈, 나눗셈)
        }

        // 백트래킹 시작 (첫 번째 숫자와 함께 시작)
        backtrack(1, numbers[0]);

        // 결과 출력 (최댓값과 최솟값)
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    /**
     * 백트래킹 함수: 모든 가능한 연산 순서를 탐색하며 최댓값과 최솟값을 계산
     * @param idx 현재 연산 중인 숫자의 인덱스
     * @param current 현재까지의 계산 결과
     */
    public static void backtrack(int idx, int current) {
        // 모든 숫자를 사용한 경우
        if (idx == N) {
            // 현재 계산 결과로 최댓값과 최솟값을 갱신
            maxValue = Math.max(maxValue, current);
            minValue = Math.min(minValue, current);
            return; // 재귀 종료
        }

        // 가능한 모든 연산자를 사용하여 탐색
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) { // 사용할 수 있는 연산자가 남아있는 경우
                operators[i]--; // 해당 연산자를 사용
                int next = calculate(current, numbers[idx], i); // 현재 숫자와 연산 수행
                backtrack(idx + 1, next); // 다음 숫자로 진행 (재귀 호출)
                operators[i]++; // 연산자 복구 (백트래킹)
            }
        }
    }

    /**
     * 두 숫자와 연산자를 사용해 계산하는 함수
     * @param a 첫 번째 숫자 (이전까지의 계산 결과)
     * @param b 두 번째 숫자 (현재 계산할 숫자)
     * @param operator 연산자 종류 (0: 덧셈, 1: 뺄셈, 2: 곱셈, 3: 나눗셈)
     * @return 계산 결과
     */
    public static int calculate(int a, int b, int operator) {
        switch (operator) {
            case 0: return a + b; // 덧셈 연산
            case 1: return a - b; // 뺄셈 연산
            case 2: return a * b; // 곱셈 연산
            case 3: return a / b; // 나눗셈 연산 (정수 나눗셈)
            default: return 0; // 잘못된 연산자의 경우 기본값 반환 (정상적인 입력만 주어진다고 가정)
        }
    }
}

