import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623 {

    // i/o
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // input
    static int N;
    static int M;

    // topology sort
    static ArrayList<Integer>[] graph;
    static Queue<Integer> q;
    static boolean canSort = true;
    static int[] inDegree;

    public static void topologySort(){

        q = new LinkedList<Integer>();
        int curNode;

        // indegree : 0 -> add q
        for(int i = 1 ; i < N+1 ; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            curNode = q.poll();
            sb.append(curNode).append("\n");

            for (int nextNode : graph[curNode]) {
                    inDegree[nextNode]--;
                    if(inDegree[nextNode] == 0){
                        q.add(nextNode);
                    }
                }
            }

        // check visited
        for (int i : inDegree) {
            if(i > 0){
                canSort = false;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        
        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // graph , indegree init
        inDegree = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 0 ; i < N+1 ; i++){
            graph[i] = new ArrayList<>();
        }

        int cur = 0; int pre = 0; int num;
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            
            for(int j = 0; j < num ; j++){
                cur = Integer.parseInt(st.nextToken());
                if(pre != 0){
                    graph[pre].add(cur);
                    inDegree[cur]++;
                }
                pre = cur;
            }
            pre = 0;
        }

        // debug graph init
        // for (ArrayList<Integer> row : graph) {
        //     for (Integer ele : row) {
        //         System.out.print(ele + " ");
        //     }System.out.println();
        // }

        // System.out.println();

        // sort
        topologySort();

        // // output
        if(!canSort)System.out.println(0);
        else System.out.println(sb);
    }
}
