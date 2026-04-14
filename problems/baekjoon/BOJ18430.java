import java.util.*;
import java.io.*;

class BOJ18430 {
    static int N, M, maxSum = 0;
    static int[][] grid;
    static boolean[][] visited;

    // 부메랑 종류 
    static int[][][] shapes = {
        {{0, -1}, {1, 0}},
        {{-1, 0}, {0, -1}},
        {{-1, 0}, {0, 1}},
        {{0, 1}, {1, 0}}
    };
    
    static boolean inBounds(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        grid = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0);
        System.out.println(maxSum);
    }

    // 칸 확인
    static void solve(int index, int currentSum) {
        if (index == N * M) {
            maxSum = Math.max(maxSum, currentSum);
            return;
        }

        int r = index / M;
        int c = index % M;

        // 부메랑놓을 경우
        if (!visited[r][c]) {
            for (int i = 0; i < 4; i++) {
                int r1 = r + shapes[i][0][0];
                int c1 = c + shapes[i][0][1];
                int r2 = r + shapes[i][1][0];
                int c2 = c + shapes[i][1][1];

                if (inBounds(r1, c1) && inBounds(r2, c2) && !visited[r1][c1] && !visited[r2][c2]) {
                    // 부메랑 배치
                    visited[r][c] = visited[r1][c1] = visited[r2][c2] = true;
                    int score = (grid[r][c] * 2) + grid[r1][c1] + grid[r2][c2];
                    solve(index + 1, currentSum + score);
                    // 복구
                    visited[r][c] = visited[r1][c1] = visited[r2][c2] = false;
                }
            }
        }
        // 부메랑 안 놓을 경우
        solve(index + 1, currentSum);
    }
}