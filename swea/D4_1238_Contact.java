import java.util.*;
import java.io.*;

public class D4_1238_Contact {
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc = 1; tc <= 10; tc++){
            
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            boolean[][] adj = new boolean[101][101];
            int[] visited = new int[101];
            Arrays.fill(visited, -1);

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n/2; i++){
               int f = Integer.parseInt(st.nextToken());
               int t = Integer.parseInt(st.nextToken());
               adj[f][t] = true;
            }

            int result = bfs(adj, visited, s);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(boolean[][] adj, int[] visited, int s){

        Queue<Integer> q = new LinkedList<>();
        visited[s] = 0;
        q.add(s);

        while(!q.isEmpty()){

            int c = q.poll();
            
            for(int i = 1; i < 101; i++){
                if(adj[c][i] && visited[i] == -1){
                    visited[i] = visited[c]+1;
                    q.add(i);
                }
            }
        }

        int maxTime = -1, maxNum = -1;
        for(int i = 1 ; i < 101; i++){
            if(visited[i] > maxTime){
                maxTime = visited[i];
                maxNum = i;
            }else if(visited[i] == maxTime){
                if(i > maxNum) maxNum = i;
            }
        }

        return maxNum;
    }
}
