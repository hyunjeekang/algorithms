import java.io.*;
import java.util.*;

public class BOJ11404 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        long MAX_VALUE = 10000000001L;

        long[][] adj = new long[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(adj[i], MAX_VALUE);
            adj[i][i] = 0;
        }

        int from, to, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            adj[from][to] = Math.min(adj[from][to], cost);
        }

        pathFind(N, adj);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                long data = adj[i][j] == MAX_VALUE ? 0 : adj[i][j];
                sb.append(data).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void pathFind(int N, long[][] adj) {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adj[i][j] = Math.min(adj[i][j], (adj[i][k] + adj[k][j]));
                }
            }
        }
    }
}
