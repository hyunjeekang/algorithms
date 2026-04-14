import java.io.*;
import java.util.*;

public class BOJ16985 {

    static int[][][][] board = new int[5][4][5][5]; //[index][rotation][row][col]
    static int[] order = new int[5]; // 순서 
    static boolean[] visited = new boolean[5];
    static int[] rotations = new int[5];	// 회전 
    static int minPath = Integer.MAX_VALUE;

    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 
        for (int i = 0; i < 5; i++) {
            for (int r = 0; r < 5; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 5; c++) {
                    board[i][0][r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
        // 회전 미리 계산 -> grid[r][c] -> grid[c][n - 1 - r]
            for (int rot = 1; rot < 4; rot++) {
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        board[i][rot][c][5 - 1 - r] = board[i][rot - 1][r][c];
                    }
                }
            }
        }
        
        // 계산 & 결과 
        permutePlates(0);
        System.out.println(minPath == Integer.MAX_VALUE ? -1 : minPath);
    
    }

    // 순서 결정
    public static void permutePlates(int depth) {
        
        if (depth == 5) {
            rotatePlates(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                permutePlates(depth + 1);
                visited[i] = false;
            }
        }
    }

    // 회전 횟수 결정 
    public static void rotatePlates(int depth) {
        
        if (depth == 5) {
            bfs(); // bfs 시작 
            return;
        }

        for (int i = 0; i < 4; i++) {
            rotations[depth] = i;
            rotatePlates(depth + 1);
        }
    }

    // 최단 거리 찾기 
    public static void bfs() {

    	// 입구 출구가 막혀 있는 경우  
        if (board[order[0]][rotations[0]][0][0] == 0 || 
            board[order[4]][rotations[4]][4][4] == 0) {
            return;
        }

        // plate 합치기 
        int[][][] map = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            map[i] = board[order[i]][rotations[i]];
        }
        
        // visited, queue
        boolean[][][] bfsVisited = new boolean[5][5][5];
        Queue<int[]> q = new ArrayDeque<>();
        
        // q -> {z, y, x, distance}
        q.add(new int[]{0, 0, 0, 0}); 
        bfsVisited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int z = curr[0];
            int y = curr[1];
            int x = curr[2];
            int dist = curr[3];

            // 목적지  
            if (z == 4 && y == 4 && x == 4) {
                minPath = Math.min(minPath, dist);
                return;
            }

            if (dist >= minPath) continue; 

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nz >= 0 && nz < 5 && ny >= 0 && ny < 5 && nx >= 0 && nx < 5) {
                    if (map[nz][ny][nx] == 1 && !bfsVisited[nz][ny][nx]) {
                        bfsVisited[nz][ny][nx] = true;
                        q.add(new int[]{nz, ny, nx, dist + 1});
                    }
                }
            }
        }
    }
}