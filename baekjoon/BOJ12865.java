import java.io.*;
import java.util.*;

public class BOJ12855 {
    
    static int N, K, W, V;
    static int[][] stuffs;
    static Integer[][] memo;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stuffs = new int[N+1][2];
        memo = new Integer[N+1][K+1];

        for(int n = 1; n < N+1; n++){
            st = new StringTokenizer(br.readLine());
            stuffs[n][0] = Integer.parseInt(st.nextToken());    // W
            stuffs[n][1] = Integer.parseInt(st.nextToken());    // V
        }

        System.out.println(dp(N, K));
    }

    private static int dp(int i, int k){
        // 기저
        if(i == 0 || k == 0) return 0;

        // 메모된 경우 
        if(memo[i][k] != null) return memo[i][k];

        // 메모 안 된 경우
        // 물건 넣을 수 있는 경우 
        if(stuffs[i][0] <= k){
            memo[i][k] = Math.max(dp(i-1, k), dp(i-1, k-stuffs[i][0]) + stuffs[i][1]);
        }
        else memo[i][k] = dp(i-1, k);

        return memo[i][k];
    }
    
}
