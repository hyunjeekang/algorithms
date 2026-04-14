import java.io.*;
import java.util.*;

public class D6_1263 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result, T, N, MAX;
        int[][] adj;

        T = Integer.parseInt(br.readLine());
        MAX = 100000000;

        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            adj = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adj[i][j] = Integer.parseInt(st.nextToken()) == 1 ? 1 : MAX;
                    if (i == j)
                        adj[i][j] = 0;
                }
            }

            result = solve(N, adj);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int N, int[][] adj) {
        int minDist = Integer.MAX_VALUE;

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adj[i][j] = Math.min(adj[i][j], (adj[i][k] + adj[k][j]));
                }
            }
        }

        for (int p = 1; p <= N; p++) {
            int curDist = Arrays.stream(adj[p]).sum();
            minDist = minDist > curDist ? curDist : minDist;
        }

        return minDist;
    }
}
