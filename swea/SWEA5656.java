import java.io.*;
import java.util.*;

public class SWEA5656 {
    static int N, W, H, minBricks, totalBricks;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] grid = new int[H][W];
            totalBricks = 0;
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    if (grid[r][c] > 0) totalBricks++;
                }
            }

            minBricks = totalBricks;
            solve(0, totalBricks, grid);
            System.out.println("#" + t + " " + minBricks);
        }
    }

    static void solve(int count, int remainBricks, int[][] grid) {
        // 기저
        if (remainBricks == 0) {
            minBricks = 0;
            return;
        }
        if (count == N) {
            minBricks = Math.min(minBricks, remainBricks);
            return;
        }

        int[][] nextGrid = new int[H][W];
        for (int c = 0; c < W; c++) {
            // 해당 열에서 가장 위에 있는 벽돌 찾기
            int r = 0;
            while (r < H && grid[r][c] == 0) r++;

            if (r == H) { // 해당 열에 벽돌이 없으면 다음 열로
                solve(count + 1, remainBricks, grid); 
                continue;
            }

            // 배열 복사 및 연쇄 폭발
            copy(grid, nextGrid);
            int removed = bfs(nextGrid, r, c);
            applyGravity(nextGrid);
            
            solve(count + 1, remainBricks - removed, nextGrid);
            
            if (minBricks == 0) return; // 이미 다 깼으면 종료
        }
    }

    static int bfs(int[][] grid, int r, int c) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        
        if (grid[r][c] > 0) {
            queue.offer(new int[]{r, c, grid[r][c]});
            grid[r][c] = 0;
            count++;
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int range = curr[2];

            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < range; i++) {
                    int nr = curr[0] + dr[d] * i;
                    int nc = curr[1] + dc[d] * i;

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && grid[nr][nc] > 0) {
                        if (grid[nr][nc] > 1) {
                            queue.offer(new int[]{nr, nc, grid[nr][nc]});
                        }
                        grid[nr][nc] = 0;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    static void applyGravity(int[][] grid) {
        for (int c = 0; c < W; c++) {
            int targetR = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (grid[r][c] > 0) {
                    int temp = grid[r][c];
                    grid[r][c] = 0;
                    grid[targetR--][c] = temp;
                }
            }
        }
    }

    static void copy(int[][] from, int[][] to) {
        for (int i = 0; i < H; i++) {
            System.arraycopy(from[i], 0, to[i], 0, W);
        }
    }
}