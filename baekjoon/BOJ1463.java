import java.io.*;

public class BOJ1463 {
    static int N;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];

        // System.out.println(bottomUp(N));
        System.out.println(topDown(N));

    }

    private static Integer bottomUp(int n) {
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);

        }
        return dp[n];
    }

    private static Integer topDown(int n){
        
        // 기저 조건
        if(n == 1){
            return 0;
        }

        // 배열 확인
        // 계산된 값 있는 경우 
        if(dp[n] != null){
            return dp[n];
        }

        // 계산된 값 없는 경우
        // 1. 1 빼기
        dp[n] = topDown(n - 1) + 1;

        // 2. 2 나누기
        if(n%2 == 0) dp[n] = Math.min(dp[n], topDown(n/2) + 1);

        // 3. 3 나누기
        if(n%3 == 0) dp[n] = Math.min(dp[n], topDown(n/3) + 1);

        return dp[n];
    }
}
