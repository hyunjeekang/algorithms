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

        // System.out.println(up(N));
        System.out.println(Math.min(down(N, 0), Math.min(down(N, 1), down(N, 2))));
    }

    private static int up(int n){
        // n == 1
        for(int i = 0; i < 3; i++){
            dp[1][i] = houses[1][i];
        }

        for(int h = 2; h < n+1; h++){
            dp[h][0] = Math.min(dp[h-1][1], dp[h-1][2]) + houses[h][0];
            dp[h][1] = Math.min(dp[h-1][0], dp[h-1][2]) + houses[h][1];
            dp[h][2] = Math.min(dp[h-1][0], dp[h-1][1]) + houses[h][2];
        }

        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    private static int down(int n, int color){

        // 기저 조건
        if(n == 1){
            return dp[1][color] = houses[1][color];
        }

        // 이미 계산된 경우 
        if(dp[n][color] != 0){
            return dp[n][color];
        }

        // 계산 안 된 경우
        if(color == 0) dp[n][0] = Math.min(down(n-1, 1), down(n-1, 2)) + houses[n][0];
        else if(color == 1) dp[n][1] = Math.min(down(n-1, 0), down(n-1, 2)) + houses[n][1];
        else dp[n][2] = Math.min(down(n-1, 0), down(n-1, 1)) + houses[n][2];
        
        // 계산 완료 값 반환
        return dp[n][color];
    }
    
}
