import java.io.*;
import java.util.*;

public class D3_22683_나무베기 {
    
    final static int[][] move = { // 북 동 남 서
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };
    static int T, N, K;
    static int sr, sc, er, ec;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int t = 1; t < T+1; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            char[][] map = new char[N][N];
            for(int r = 0; r < N; r++){
                String s = br.readLine();
                for(int c = 0 ; c < N; c++){
                    map[r][c] = s.charAt(c);
                    if(map[r][c] == 'X'){
                        sr = r;
                        sc = c;
                    }
                    else if(map[r][c] == 'Y'){
                        er = r;
                        ec = c;
                    }
                }
            }
            sb.append('#').append(t).append(' ').append(bfs(map)).append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(char[][] map) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][N][4][K + 1]; // [r][c][dir][cut] <= 10*10*4*5

        q.add(new int[]{sr, sc, 0, 0, 0}); 
        visited[sr][sc][0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1], dir = cur[2], cut = cur[3], cost = cur[4];

            if(cr == er && cc == ec) return cost;

            // 1. 전진
            int nr = cr + move[dir][0];
            int nc = cc + move[dir][1];
            if(inBounds(nr, nc)) {
                // 땅이거나 도착지/출발지인 경우 (벌목 X)
                if(map[nr][nc] == 'G' || map[nr][nc] == 'Y' || map[nr][nc] == 'X') {
                    if(!visited[nr][nc][dir][cut]) {
                        visited[nr][nc][dir][cut] = true;
                        q.add(new int[]{nr, nc, dir, cut, cost + 1});
                    }
                } 
                // 나무인 경우
                else if(map[nr][nc] == 'T') { 
                    if(cut < K && !visited[nr][nc][dir][cut + 1]) {
                        visited[nr][nc][dir][cut + 1] = true;
                        q.add(new int[]{nr, nc, dir, cut + 1, cost + 1});
                    }
                }
            }

            // 2. 우회전
            int rightDir = (dir + 1) % 4;
            if(!visited[cr][cc][rightDir][cut]) {
                visited[cr][cc][rightDir][cut] = true;
                q.add(new int[]{cr, cc, rightDir, cut, cost + 1});
            }

            // 3. 좌회전
            int leftDir = (dir - 1 + 4) % 4;
            if(!visited[cr][cc][leftDir][cut]) {
                visited[cr][cc][leftDir][cut] = true;
                q.add(new int[]{cr, cc, leftDir, cut, cost + 1});
            }
        }
        return -1;
    }

    private static boolean inBounds(int r, int c){
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
