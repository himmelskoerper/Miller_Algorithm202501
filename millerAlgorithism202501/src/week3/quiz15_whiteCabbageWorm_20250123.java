package week3;

import java.util.*;

public class quiz15_whiteCabbageWorm_20250123 {
    // 배추밭을 나타내는 2차원 배열
    static int[][] field;
    // 방문 여부를 확인하기 위한 배열
    static boolean[][] visited;
    // 상하좌우 방향 이동을 위한 배열
    static int[] dx = {0, 0, -1, 1}; // x축: 상(0), 하(0), 좌(-1), 우(+1)
    static int[] dy = {-1, 1, 0, 0}; // y축: 상(-1), 하(+1), 좌(0), 우(0)
    static int M, N; // 배추밭의 가로(M), 세로(N)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배추밭의 개수 입력
        int T = sc.nextInt();

        // 각 배추밭 처리
        for (int t = 0; t < T; t++) {
            // 배추밭의 가로길이(M), 세로길이(N), 배추 개수(K) 입력
            M = sc.nextInt(); // 가로길이
            N = sc.nextInt(); // 세로길이
            int K = sc.nextInt(); // 배추가 심어진 위치 개수

            // 배추밭 초기화
            field = new int[M][N];
            visited = new boolean[M][N];

            // 배추 위치 입력 및 설정
            for (int i = 0; i < K; i++) {
                int x = sc.nextInt(); // 배추의 x좌표
                int y = sc.nextInt(); // 배추의 y좌표
                field[x][y] = 1; // 해당 위치에 배추 표시 (1)
            }

            int wormCount = 0; // 필요한 배추흰지렁이의 수

            // 배추밭을 순회하며 지렁이 수 계산
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    // 배추가 있고, 아직 방문하지 않은 경우
                    if (field[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j); // DFS를 통해 연결된 배추 그룹 탐색
                        wormCount++; // 새로운 그룹 발견 시 지렁이 수 증가
                    }
                }
            }

            // 결과 출력: 현재 배추밭의 지렁이 수
            System.out.println(wormCount);
        }

        sc.close(); // Scanner 자원 해제
    }

    // DFS(깊이 우선 탐색)를 사용하여 연결된 배추 그룹 탐색
    static void dfs(int x, int y) {
        visited[x][y] = true; // 현재 위치를 방문 처리

        // 상하좌우 네 방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 이동한 x좌표
            int ny = y + dy[i]; // 이동한 y좌표

            // 이동한 좌표가 배추밭 내부에 있는지 확인
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                // 해당 위치에 배추가 있고, 방문하지 않았다면
                if (field[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs(nx, ny); // 재귀적으로 DFS 수행
                }
            }
        }
    }
}
