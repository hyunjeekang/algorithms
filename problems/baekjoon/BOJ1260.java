import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {
	
	// i/o
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// input
	static int N;
	static int M;
	static int V;
	
	// graph
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	// dfs
	public static void dfs(int curVertex) {
		visited[curVertex] = true;
		sb.append(curVertex).append(" ");
		
		for (Integer neighborVertex : graph[curVertex]) {
			if(!visited[neighborVertex]) {
				dfs(neighborVertex);
			}
		}
	}
	
	// bfs
	public static void bfs(int startVertex) {
		
		visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		
		visited[startVertex] = true;
		q.add(startVertex);
		
		int curVertex;
		while(!q.isEmpty()) {
			curVertex = q.poll();
			sb.append(curVertex + " ");
			
			for (Integer neighborVertex : graph[curVertex]) {
				if(!visited[neighborVertex]) {
					visited[neighborVertex] = true;
					q.add(neighborVertex);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// vertex
		graph = new ArrayList[N+1];
		for(int n = 1 ; n < N+1; n++) {
			graph[n] = new ArrayList();
		}
		
		// edge
		int s; int e;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			graph[s].add(e);
			graph[e].add(s);
		}
		
		// sort
		for(int n = 1 ; n < N+1 ; n++) {
			graph[n].sort((o1, o2) -> o1 - o2);
		}
		
		// search
		visited = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		bfs(V);
		
		// output
		System.out.println(sb);
	}
}
