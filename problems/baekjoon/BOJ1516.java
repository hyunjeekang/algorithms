import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {

    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] time;
    static int[] buildTime;
    static int N;


    // i/o
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void solve(){
        Queue<Integer> q = new LinkedList();
        buildTime = new int[N+1];

        for(int i = 1 ; i < N+1; i++){
            if(indegree[i] == 0){
                q.add(i);
                buildTime[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int curNode = q.poll();

            for(int nextNode : graph[curNode]){
                buildTime[nextNode] = Math.max(buildTime[nextNode], buildTime[curNode] + time[nextNode]);

                indegree[nextNode]--;
                if(indegree[nextNode] == 0){
                    q.add(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        // input
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        indegree = new int[N+1];
        time = new int[N+1];
        
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 인접리스트
        for(int i = 1 ; i < N+1 ; i++){
            
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken()); // time

            while(st.hasMoreTokens()){
                int data = Integer.parseInt(st.nextToken());
                if(data == -1) break;
                graph[data].add(i);
                indegree[i]++;  // indegree ++
            }
        }

        solve();

        for(int i = 1 ; i < N+1 ; i++){
            sb.append(buildTime[i]).append("\n");
        }

        System.out.println(sb);

    }
    
}
