import java.io.*;
import java.util.*;

public class D4_1226_미로1 {

    static final int[] drs = {-1, 1, 0, 0};
    static final int[] dcs = {0, 0, -1, 1};
    static final int SIZE = 16;
    
    static int sr, sc, er, ec;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= 10; t++ ){
            int[][] maze = new int[SIZE][SIZE];

            br.readLine();
            for(int r = 0; r < SIZE; r++){
                String str = br.readLine();
                for(int c = 0; c < SIZE; c++ ){
                    maze[r][c] = str.charAt(c) - '0';
                    if(maze[r][c] == 2){
                        sr = r; sc = c;
                    } 
                    else if(maze[r][c] == 3){
                        er = r; ec = c;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(bfs(maze) ? 1 : 0).append("\n");
        }
        
        System.out.println(sb);
    }

    private static boolean bfs(int[][] maze){
        Queue<int[]> q = new LinkedList<>();

        maze[sr][sc] = 1;
        q.add(new int[] {sr, sc});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0]; int cc = cur[1];

            if(cr == er && cc == ec) return true;

            for(int i = 0; i < 4; i++){
                int nr = cr + drs[i]; 
                int nc = cc + dcs[i];

                if(inBounds(nr, nc) && maze[nr][nc] != 1){
                    maze[nr][nc] = 1;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        return false;
    }

    private static boolean inBounds(int r, int c){
        return 0 <= r && r < SIZE && 0 <= c && c < SIZE;
    }
}
