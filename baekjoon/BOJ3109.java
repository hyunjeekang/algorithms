import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

    final static char BUILDING = 'x', EMPTY = '.';
    final static int[][] PIPES = {
        {-1, 1},
        {0, 1},
        {1, 1}
    };

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, result;
    static char[][] grid;
    static boolean[][] visited;

    public static boolean inBound(int r, int c){
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static boolean dfs(int r, int c){
        int nr, nc;
        visited[r][c] = true;

        // 마지막 열 도착
        if(c == C-1){
            result ++;
            return true;
        }
        
        for(int i = 0 ; i < 3; i++){
            nr = r + PIPES[i][0];
            nc = c + PIPES[i][1];

            if(inBound(nr, nc) && grid[nr][nc] == EMPTY && !visited[nr][nc]){
                if(dfs(nr, nc)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException{
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        visited = new boolean[R][C];

        for(int r = 0 ; r < R; r++){
            grid[r] = br.readLine().toCharArray();
        }

        for(int r = 0; r < R; r++){
            dfs(r,0);
        }

        System.out.println(result);
    }
}
