import java.io.*;
import java.util.*;

public class SWEA3124 {

    static int T, V, E;
    
    //kruskal -> 간선 중심, 전체에서 작은 간선부터 확인
    //edge
    static class Edge implements Comparable<Edge>{
    	int from, to, weight;
    	public Edge(int from, int to, int weight) {
    		this.from = from;
    		this.to = to;
    		this.weight = weight;
    	}
    	
    	@Override
    	public int compareTo(Edge o) {
    	    return Integer.compare(this.weight, o.weight);
    	}
    }
    
    static class kruskalMST {
    	private int[] parent, rank;
    	
    	public kruskalMST(int N) {
    		parent = new int[N+1];
    		rank = new int[N+1];
    		
    		for(int i = 1 ; i < N+1; i++) {
    			parent[i] = i;
    		}
    	}
    	
    	public int find(int x) {
    		if(parent[x] == x) return x;
    		return (parent[x] = find(parent[x]));
    	}
    	
    	public boolean union(int x, int y) {
    		int rootX = find(x);
    		int rootY = find(y);
    		
    		if(rootX == rootY) return false;
    		else {
    			if(rank[rootX] > rank[rootY]) {
    				parent[rootY] = rootX;
    			}else if(rank[rootX] < rank[rootY]){
    				parent[rootX] = rootY;
    			}else {
    				parent[rootY] = rootX;
    				rank[rootX]++;
    			}
    			return true;
    		}
    	}
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int A, B, C;
        List<Edge> edges;
        T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t < T+1; t++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            edges = new ArrayList<>();

            for(int e = 0 ; e < E; e++){
                st = new StringTokenizer(br.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                edges.add(new Edge(A, B, C));
            }
            Collections.sort(edges);
            
            kruskalMST mst = new kruskalMST(V);
            
            int edgeCnt = 0;
            long mstWeight = 0;
            
            for (Edge edge : edges) {
            	if(mst.find(edge.from) != mst.find(edge.to)) {
            		mst.union(edge.from, edge.to);
            		edgeCnt ++;
            		mstWeight += edge.weight;
            	}
            	if(edgeCnt == V-1) break;
			}
            
            sb.append('#').append(t).append(' ').append(mstWeight).append('\n');
        }
        System.out.println(sb);
    }
}
