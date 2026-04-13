import java.util.*;
import java.io.*;

public class D3_2814_최장경로 {
    
    static int maxResult;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] graph = new ArrayList[N+1];
            for(int i = 1; i <= N; i++){
                graph[i] = new ArrayList<Integer>();
            }

            for(int m = 0 ; m < M; m++){
                st = new StringTokenizer(br.readLine());
                int f = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph[f].add(t);
                graph[t].add(f);
            }

            maxResult = 0;
            boolean[] visited = new boolean[N+1];

            // 모든 노드에서 출발해
            for(int i = 1; i <= N; i++){
                visited[i] = true;
                dfs(graph, visited, i, 1);
                visited[i] = false;
            }
            
            sb.append("#").append(tc).append(" ").append(maxResult).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int curr, int len){
        maxResult = Math.max(maxResult, len);

        for (int next : graph[curr]) {
            if(!visited[next]){
                visited[next] = true;
                dfs(graph, visited, next, len + 1);
                visited[next] = false; // 갈 곳 없으면 돌아가 
            }
        }
    }
}