import java.io.*;

public class BOJ2748 {
    static long[] memo = new long[100];
    static int n;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        System.out.println(fibonacci(n));
        // System.out.println(fibonacci());
    }

    // 하향식 
    private static long fibonacci(int n){
        // 기저 조건: 가장 작은 문제
        if(n == 1 || n == 2) return 1;
        
        // 이미 계산된 경우 -> 바로 값 리턴 
        if(memo[n] != 0) return memo[n];

        // 계산 안 된 경우 -> 계산 후 배열에 저장
        memo[n] = fibonacci(n-1) + fibonacci(n-2);
        return memo[n];
    }

    // 상향식
    private static long fibonacci() {

        // dp table
        long[] dp = new long[n+1];

        // 기저 조건
        dp[1] = 1;
        if(n >= 2){
            dp[2] = 1;
        }

        // 반복문
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
        
    }

}
