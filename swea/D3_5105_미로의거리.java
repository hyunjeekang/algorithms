import java.util.*;
import java.io.*;

public class D3_5105_미로의거리 {

    final static int[] drs = {0, 0, -1, 1};
    final static int[] dcs = {-1, 1, 0, 0};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){

            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int sr = 0, sc = 0;

            for(int r = 0; r < N; r++){
                String str = br.readLine();
                for(int c = 0 ; c < N; c++){
                    map[r][c] = str.charAt(c) - '0';
                    if(map[r][c] == 2){
                        sr = r;
                        sc = c;
                    }
                }
            }
            
            sb.append("#").append(t).append(" ").append(bfs(N, new int[]{sr, sc}, map)).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int N, int[] s, int[][] map){
        
        Queue<int[]> q = new LinkedList<>();
        
        map[s[0]][s[1]] = 1;
        q.add(new int[]{s[0], s[1], 0}); // {r, c, move}

        while(!q.isEmpty()){
            
            int[] c = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nr = c[0] + drs[i];
                int nc = c[1] + dcs[i];

                if(inBounds(nr, nc, N)){
                    if(map[nr][nc] == 3) return c[2];
                    
                    if(map[nr][nc] == 0){
                        map[nr][nc] = 1;
                        q.add(new int[]{nr, nc, c[2]+1});
                    }
                }
            }
        }
        return 0;
    }

    private static boolean inBounds(int r, int c, int N){
        return 0 <= r && r < N && 0 <= c && c < N;       
    }
}
