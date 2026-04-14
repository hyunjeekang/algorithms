import java.io.*;
import java.util.*;

public class BOJ16953 {

    static long A, B;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        int ans = recur(B);
        
        System.out.println(ans);
    }

    private static int recur(long n){
        if (n == A) return 1;
        
        if (n < A) return -1;

        if (n % 10 == 1) {
            int res = recur(n / 10);
            return (res == -1) ? -1 : res + 1;
            
        } else if (n % 2 == 0) {
            int res = recur(n / 2);
            return (res == -1) ? -1 : res + 1;
            
        } else {
            return -1;
        }
    }
}