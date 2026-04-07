import java.io.*;
import java.util.*;

public class BOJ1149 {

    static int N;
    static int[][] houses;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        houses = new int[N+1][3]; //[index][rgb]
        dp = new int[N+1][3];

        for(int n = 1 ; n < N+1; n++){
            st = new StringTokenizer(br.readLine());
            houses[n][0] = Integer.parseInt(st.nextToken());
            houses[n][1] = Integer.parseInt(st.nextToken());
            houses[n][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(up(N));
    }

    private static int up(int N){
        // n == 1
        for(int i = 0; i < 3; i++){
            dp[1][i] = houses[1][i];
        }

        for(int h = 2; h < N+1; h++){
            dp[h][0] = Math.min(dp[h-1][1], dp[h-1][2]) + houses[h][0];
            dp[h][1] = Math.min(dp[h-1][0], dp[h-1][2]) + houses[h][1];
            dp[h][2] = Math.min(dp[h-1][0], dp[h-1][1]) + houses[h][2];
        }

        return Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
    }
    
}
