package jadegaja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236 {
	
	static final int[] drs = {-1, 0, 0, 1};
	static final int[] dcs = {0, -1, 1, 0};
	
	static BufferedReader br;
	static StringTokenizer st;
	
	// 입력
	static int N;
	static int data;
	static int[][] grid;
	
	// 아기 상어 위치, 크기, 먹은 물고기 수
	static int bsr; 
	static int bsc;
	static int babyShark = 2;
	static int eatenFish = 0;
	
	// 엄마 상어 도움 없이 물고기 잡아먹을 수 있는 시간
	static int totalTime = 0;
	
	// point
	public static class Point implements Comparable<Point>{
		int r; int c; int time;
		
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
		@Override
		public int compareTo(Point o) {
		    // 1. 거리가 같다면
		    if (this.time == o.time) {
		        // 2. 행(r, 위아래) 위치가 같다면
		        if (this.r == o.r) {
		            // 3. 열(c, 좌우) 위치가 더 작은 것(왼쪽)이 우선!
		            return Integer.compare(this.c, o.c); 
		        }
		        // 행이 다르면 행이 더 작은 것(위쪽)이 우선!
		        return Integer.compare(this.r, o.r); 
		    }
		    // 거리가 다르면 거리가 더 짧은 것이 우선!
		    return Integer.compare(this.time, o.time); 
		}
	}
	
	// 공간 범위 내 검증
	public static boolean inBound(int r, int c) {
		return ((0 <= r)&&(r < N)) && ((0 <= c) && (c < N));
	}
	
	// bfs
	public static boolean bfs(int sr, int sc) {
		// visited, pq
		boolean[][] visited = new boolean[N][N]; 
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		
		// 시작점 초기화
		pq.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		int cr; int cc; int curTime;
		int nr; int nc;
		
		while(! pq.isEmpty()) {
			
			Point curPoint = pq.poll();
			cr = curPoint.r; cc = curPoint.c; curTime = curPoint.time;
			
			// 먹을 수 있는 물고기 발견
			if(grid[cr][cc] > 0 && babyShark > grid[cr][cc]) {
				grid[cr][cc] = 0;
				totalTime += curTime;
				eatenFish ++;
				bsr = cr; bsc = cc;
				
				if(eatenFish == babyShark) {
					babyShark ++;
					eatenFish = 0;
				}
				
				return true;
			}
			
			for(int i = 0 ; i < 4; i++) {
				nr = cr + drs[i]; nc = cc + dcs[i];
				
				// 갈 수 있는 칸 : 범위 내 && 아기상어 크기보다 같거나 작은 물고기가 있는(또는 물고기가 없는) 칸
				if(inBound(nr, nc) && (babyShark >= grid[nr][nc]) && (!visited[nr][nc])) {
					visited[nr][nc] = true;
					pq.add(new Point(nr, nc, curTime+1));
				}
			}		
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		grid = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				data = Integer.parseInt(st.nextToken());
				if(data == 9) {
					bsr = r; bsc = c; data = 0;
				}
				grid[r][c] = data;
			}
		}
		
		boolean found = true; 
		while(found) {
			found = bfs(bsr, bsc);
		}
		
		System.out.println(totalTime);
	}
}
