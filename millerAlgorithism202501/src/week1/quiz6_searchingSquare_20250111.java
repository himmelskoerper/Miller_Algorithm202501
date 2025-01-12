package week1;

import java.util.*;

public class quiz6_searchingSquare_20250111 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력받기
        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열
        sc.nextLine(); // 버퍼 비우기

        char[][] grid = new char[N][M];

        for (int i = 0; i < N; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        int maxSquareSize = 1; // 정답 초기값

        // 최대 변의 길이 R 설정 (N과 M 중 작은 값)
        int R = Math.min(N, M);

        // 탐색 시작
        for (int size = 1; size <= R; size++) { // size: 정사각형 한 변의 길이
            for (int i = 0; i + size - 1 < N; i++) { // 시작 행
                for (int j = 0; j + size - 1 < M; j++) { // 시작 열
                    // 현재 정사각형의 꼭짓점 확인
                    char topLeft = grid[i][j];
                    char topRight = grid[i][j + size - 1];
                    char bottomLeft = grid[i + size - 1][j];
                    char bottomRight = grid[i + size - 1][j + size - 1];
										
										//탐색하면서 더 큰 정사각형을 발견하면 초기화
                    if (topLeft == topRight && topLeft == bottomLeft && topLeft == bottomRight) {
                        maxSquareSize = Math.max(maxSquareSize, size);
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(maxSquareSize * maxSquareSize);
    }
}