import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005 {

    // i/o
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // input
    static int T;
    static int N;
    static int K;
    static int W;
    static int[] buildTime;

    // topology sort
    static Queue<Integer> q;
    static ArrayList<Integer>[] graph;
    static int[] compTime;
    static int[] inDegree;

    public static void topologySort(){

        q = new LinkedList<>();
        compTime = new int[N+1];
        
        for(int i = 1 ; i < N+1; i++){
            if(inDegree[i] == 0){
                compTime[i] = buildTime[i];
                q.add(i);
            }
        }

        int curNode;
        while(!q.isEmpty()){
            curNode = q.poll();
            if(curNode == W){
                sb.append(compTime[curNode]).append("\n");
                break;
            }

            for (int nextNode : graph[curNode]) {

                compTime[nextNode] = Math.max(compTime[curNode] + buildTime[nextNode], compTime[nextNode]);

                inDegree[nextNode]--;
                if(inDegree[nextNode] == 0){
                    q.add(nextNode);
                    }
                }
            }
        }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // buildTime
            buildTime = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i < N+1 ; i++){
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            // graph
            inDegree = new int[N+1];
            graph = new ArrayList[N+1];
            for(int g = 0 ; g < N+1 ; g++){
                graph[g] = new ArrayList<>();
            }

            int X; int Y;
            for(int i = 0 ; i < K; i++){
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                graph[X].add(Y);
                inDegree[Y]++;
            }

            // W
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());

            // output
            topologySort();
        }
        System.out.println(sb);
    }
}
