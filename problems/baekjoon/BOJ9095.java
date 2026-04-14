import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9095 {

    static int t;
    static int n;
    static int result;
    static int[] dp;


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void setDP(){
        dp = new int[11];
        dp[1] = 1; dp[2] = 2; dp[3] = 4; 
        
        for(int i = 4 ; i <= 10; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
    }

    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());
        
        setDP();
        
        for(int tc = 0; tc < t ; tc++){
            n = Integer.parseInt(br.readLine());
            result = dp[n];
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

}
