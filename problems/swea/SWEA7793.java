import java.io.*;
import java.util.*;

public class SWEA7793 {

	/**
	 * 
	 * 현재 수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’
	 * ‘.’는 평범한 지역으로, 수연이가 이동할 수도 있으며 “악마의 손아귀” 스킬이 확장될 수도 있다.
	 * 
	 * "악마의 손아귀" 스킬은 매 초마다 상하좌우 인접해있는 영역들을 부식시키며 확장
	 * 수연이는 돌이 있는 위치로는 이동할 수 없다. 또, “악마의 손아귀”는 돌이 있는 곳에도 확장되지 않는다.
	 * 현재 지도가 주어졌을 때, 수연이는 여신님께 최소 시간으로 이동
	 */

	final static int[] drs = {-1, 1, 0, 0}, dcs = {0, 0, -1, 1};
	static int T, N, M;
	static char[][] grid;
	static int[][] devilTime;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++){
			sb.append('#').append(t).append(' ');

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			grid = new char[N][M];
			devilTime = new int[N][M];

			// grid 입력
			for(int r = 0; r < N; r++){
				grid[r] = br.readLine().toCharArray();
			}

			// inf로 초기화
			int sr = 0, sc = 0;
			for(int r = 0; r < N; r++){
				for(int c = 0; c < M; c++){
					devilTime[r][c] = Integer.MAX_VALUE;
					if(grid[r][c] == 'S'){
						sr = r; sc = c;
					}
				}
			}
			devilBfs();
			int result = bfs(sr, sc);
			
			if(result >= 0 ) sb.append(result);
			else sb.append("GAME OVER");
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void devilBfs(){

		Queue<int[]> q = new LinkedList<>();
		// devilTime[r][c] = 0;
		// q.offer(new int[] {r, c, 0});

		for(int r = 0; r < N; r++){
			for(int c = 0; c < M; c++){
				if(grid[r][c] == '*'){
					devilTime[r][c] = 0;
					q.offer(new int[] {r, c, 0});
				}
			}
		}

		int nr, nc;
		while(!q.isEmpty()){
			int[] polled = q.poll();
			for(int i = 0; i < 4; i++){
				nr = polled[0] + drs[i];
				nc = polled[1] + dcs[i];
				if(0 <= nr && nr < N && 0 <= nc && nc < M){
					if(grid[nr][nc] == '.' || grid[nr][nc] == 'S'){
						if(devilTime[nr][nc] == Integer.MAX_VALUE){
							devilTime[nr][nc] = polled[2]+1;
							q.offer(new int[] {nr, nc, polled[2]+1});
						}

					}
				}
			}
		}
	}

	public static int bfs(int r, int c){
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		visited[r][c] = true;
		q.offer(new int[] {r, c, 0});

		int nr, nc;
		while(!q.isEmpty()){
			int[] polled = q.poll();
			if(grid[polled[0]][polled[1]] == 'D') return polled[2];

			for(int i = 0; i < 4; i++){
				nr = polled[0] + drs[i];
				nc = polled[1] + dcs[i];
				if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]){
					if(grid[nr][nc] == '.' || grid[nr][nc] == 'D'){
						if(polled[2] + 1 < devilTime[nr][nc]){
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc, polled[2]+1});
					}
					}
				}
			}
		}
		return -1;
	}
}