package week1;

import java.util.*;

public class quiz6_searchingSquare_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력받기
        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열
        sc.nextLine(); // 버퍼 비우기

        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        int maxSquareArea = 1; // 최대 정사각형의 크기 초기값 (1x1 최소 크기)

        // 탐색 시작
        for (int size = 1; size <= Math.min(N, M); size++) { // size: 정사각형 한 변의 길이
            for (int i = 0; i + size - 1 < N; i++) { // 시작 행
                for (int j = 0; j + size - 1 < M; j++) { // 시작 열
                    // 정사각형 조건 확인
                    if (isSquare(grid, i, j, size)) {
                        maxSquareArea = Math.max(maxSquareArea, size * size);
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(maxSquareArea);
    }

    // 정사각형의 네 꼭짓점이 모두 같은지 확인하는 메서드
    private static boolean isSquare(char[][] grid, int x, int y, int size) {
        char topLeft = grid[x][y];
        char topRight = grid[x][y + size - 1];
        char bottomLeft = grid[x + size - 1][y];
        char bottomRight = grid[x + size - 1][y + size - 1];

        return topLeft == topRight && topLeft == bottomLeft && topLeft == bottomRight;
    }
}