import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206{
    final static int[] drs = {0, 0, -1, 1};
    final static int[] dcs = {-1, 1, 0, 0};
    
    static int[][] grid;
    static int N, M;
    
    static class Node {
        int r, c, broken, dist;
        public Node(int r, int c, int broken, int dist) {
            this.r = r;
            this.c = c;
            this.broken = broken;
            this.dist = dist;
        }
    }
    
    public static boolean inBounds(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
    
    public static int bfs() {
        // visited[r][c][0]: 벽 안 부수고 방문 / visited[r][c][1]: 벽 부수고 방문
        boolean[][][] visited = new boolean[N][M][2];
        Queue<Node> q = new LinkedList<>();
        
        q.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            // 목적지 도착
            if(cur.r == N - 1 && cur.c == M - 1) return cur.dist;
            
            for(int i = 0; i < 4; i++) {
                int nr = cur.r + drs[i];
                int nc = cur.c + dcs[i];
                
                if(!inBounds(nr, nc)) continue;

                if(grid[nr][nc] == 0) { // 벽이 아닐 때
                    if(!visited[nr][nc][cur.broken]) {
                        visited[nr][nc][cur.broken] = true;
                        q.add(new Node(nr, nc, cur.broken, cur.dist + 1));
                    }
                } else { // 벽일 때
                    if(cur.broken == 0 && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        q.add(new Node(nr, nc, 1, cur.dist + 1));
                    }
                }
            }
        }
        return -1; // 도달 불가능
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        grid = new int[N][M];
        for(int r = 0; r < N; r++) {
            String str = br.readLine();
            for(int c = 0; c < M; c++) {
                grid[r][c] = str.charAt(c) - '0';
            }
        }
        
        System.out.println(bfs());
    }
}