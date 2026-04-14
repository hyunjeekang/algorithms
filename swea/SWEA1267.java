import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1267 {
	
	//static
	static final int TC = 10;
	
	// i/o
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// input
	static int V;	// vertex
	static int E;	// edge
	
	// topology sort
	static ArrayList<Integer>[] graph;
	static Queue<Integer> q;
	static int[] inDegree;
	
	public static void topologySort() {
		
		q = new LinkedList<Integer>();
		int curNode;
		
		// inDegree == 0 -> add
		for(int v = 1; v < V+1; v++) {
			if(inDegree[v] == 0) {
				q.add(v);
			}
		}
		
		while(!q.isEmpty()) {
			curNode = q.poll();
			sb.append(" ").append(curNode);
			
			for (Integer neighborNode : graph[curNode]) {
				inDegree[neighborNode]--;
				if(inDegree[neighborNode] == 0) {
					q.add(neighborNode);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		for(int t = 1; t <= TC; t++) {
			
			// output setting
			sb = new StringBuilder();
			sb.append("#").append(t);
			
			// input - V, E
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// graph init
			graph = new ArrayList[V+1];
			for(int v = 1 ; v < V+1; v++) {
				graph[v] = new ArrayList<>();
			}
			
			// indegree
			inDegree = new int[V+1];
			
			// input - graph
			int v1; int v2;
			st = new StringTokenizer(br.readLine());
			
			for(int e = 0 ; e < E; e++) {
			
				v1 = Integer.parseInt(st.nextToken());
				v2 = Integer.parseInt(st.nextToken());
				
				graph[v1].add(v2);
				inDegree[v2]++;
			}
			
			// topology sort
			topologySort();
			
			// output
			System.out.println(sb);
		}
	}
}
