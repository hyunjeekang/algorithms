import java.io.*;
import java.util.*;

public class BOJ22971 {

    final static int MODULO = 998244353;
    static int N;
    static int[] A, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        A = new int[N];
        m = new int[N];
        Arrays.fill(m, -1);

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(dp(i)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static int dp(int n) {
        if (m[n] != -1)
            return m[n];

        m[n] = 1;

        for (int i = 0; i < n; i++) {
            if (A[i] < A[n]) {
                m[n] = (m[n] + dp(i)) % MODULO;
            }
        }

        return m[n];
    }
}