import java.io.*;
import java.util.*;

public class BOJ11725 {

    static boolean[] visited;
    static int[] parent;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        visited = new boolean[N + 1];
        parent = new int[N + 1];

        parent[1] = -1; // root node

        // input
        for (int i = 2; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);
        for (int i = 2; i < N + 1; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int i) {

        for (int nb : graph[i]) {
            if (!visited[nb]) {
                visited[nb] = true;
                parent[nb] = i;
                dfs(nb);
            }
        }
    }
}
