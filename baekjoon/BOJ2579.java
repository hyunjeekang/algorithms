import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579{

    static int n;
    static int stair;
    static int result;

    static int[] dp;
    static int[] stairs;

    static BufferedReader br;
    
    public static int solve(){
        if(n == 1) return stairs[1];
        
        dp = new int[n+1];

        dp[1] = stairs[1];
        dp[2] = stairs[1]+stairs[2];

        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[i];
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        stairs = new int[n+1];
        for(int i = 1; i <= n; i++){
            stair = Integer.parseInt(br.readLine());
            stairs[i] = stair;
        }

        result = solve();
        System.out.println(result);
    }
}