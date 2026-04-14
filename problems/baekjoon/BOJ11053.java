import java.io.*;
import java.util.*;

public class BOJ11053 {

    static int N, lts;
    static int[] A, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        m = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        lts = 1;
        for(int i = 0 ; i < N; i++){
            lts = Math.max(lts, dp(i));
        }

        System.out.println(lts);
    }

    private static int dp(int n) {

        if (m[n] != 0) return m[n];

        m[n] = 1;

        for(int i = 0 ; i < n; i++){
            if(A[i] < A[n]) m[n] = Math.max(m[n], dp(i) + 1);
        }

        return m[n];
    }
}
