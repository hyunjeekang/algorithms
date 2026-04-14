package jadegaja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {
    
    static int M; 
    static int N;
    static int[][] maze;
    static int[][] dist;
    
    static int[] drs = {0, 0, -1, 1};
    static int[] dcs = {-1, 1, 0, 0};
    
    static PriorityQueue<Node> pq;
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static class Node implements Comparable<Node>{
        int r; int c; int dist;
        
        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        
        @Override
        /**
         * this.dist, other.thist 비교
         * 거리 짧은 노드가 큐의 맨 앞으로 가게(우선순위 높게) 설정
         * Integer.compare(A, B)는 A가 B보다 작으면 음수를 반환
         * 우선순위 큐는 이 결과가 음수일수록 우선순위가 높다(먼저 꺼낸다)고 판단
         * */
        public int compareTo(Node other) {
        	return Integer.compare(this.dist, other.dist);
        }
    }
    
    public static boolean inBound(int r, int c) {
    	return ((0 <= r) && (r < N)) && ((0 <= c) && (c < M));
    }
    
    public static int dijkstra() {
        dist = new int[N][M];
        dist[0][0] = 0;

        for(int i = 0; i < N ; i++) {
        	Arrays.fill(dist[i], Integer.MAX_VALUE);	// 한 줄씩 초기화
        }
    	
    	pq = new PriorityQueue<>();
    	pq.add(new Node(0, 0, 0));
    	
    	int cr = 0; int cc = 0;
    	int nr = 0; int nc = 0;
    	int curDist = 0; int newDist = 0;
    	Node curNode;
    	
    	while(!pq.isEmpty()) {
    		curNode = pq.poll();
    		cr = curNode.r; cc = curNode.c; curDist = curNode.dist;
    		
    		if((cr == N-1) && (cc == M-1)) {
    			return curDist;
    		}
    		
    		if(curDist > dist[cr][cc]) continue;
    		
    		for(int i = 0 ; i < 4 ; i++) {
    			nr = cr + drs[i];
    			nc = cc + dcs[i];
    			
    			if(inBound(nr, nc)) {
    				newDist = curDist + maze[nr][nc];
    				
    				if(newDist < dist[nr][nc]) {
    					dist[nr][nc] = newDist;
    					pq.add(new Node(nr, nc, newDist));
    				}
    			}
    		}
    	}
    	return -1;
    }
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); 
        N = Integer.parseInt(st.nextToken());
        
        maze = new int[N][M];
        
        String line;
        for(int r = 0 ; r < N; r++) {
        	line = br.readLine();
        	for(int c = 0 ; c < M ; c++) {
        		maze[r][c] = line.charAt(c) - '0';
        	}
        }
        
        int result = dijkstra();
        System.out.println(result);
    }

}