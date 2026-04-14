import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1767 {
	
	// static final
	static final int[] drs = {-1, 1, 0, 0};
	static final int[] dcs = {0, 0, -1, 1};
	
	// i/o
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// input
	static int T ;
	static int N;
	static int[][] map;
	
	// dfs
	static ArrayList<Core> cores;
	static int maxCores;
	static int minWireLen;
	
	// core
	public static class SWEA_1767{
		int r; int c;
		public Core(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	/*
	 * 부분집합 + 4방향 탐색
	 * 현재 코어 연결 안 하기 + 연결하기 : 4방향 전선 깔아보기  
	 * 갈 수 있는 모든 연결 해보기 -> 가장 많은 코어 연결하기 -> 가장 많은 코어에서 가장 짧은 전선 길이 합 구하기
	 */
	
	// 프로세서 범위 내인지 확인 
	public static boolean inBound(int r, int c) {
		return (0 <= r) && (r < N) && (0 <= c) && (c < N);
	}
	
	// wire 놓을 수 있는지 확인
	public static boolean canFill(int r, int c) {
		return map[r][c] == 0;
	}
	
	// 전선 깔기(val:2) / 복구(val:0)
	public static void fill(int r, int c, int dir, int val) {
		int nr = r + drs[dir];
		int nc = c + dcs[dir];
		
		while(inBound(nr, nc)) {
			map[nr][nc] = val;			
			nr += drs[dir];
			nc += dcs[dir];
		}
	}
	
	public static void dfs(int index, int curCores, int curWireLen) {
		
		// 가지 - 남은 코어 다 연결해도 maxCores보다 작다면
		int remainCores = cores.size() - index;
		if(curCores + remainCores < maxCores) {
			return;
		}
		
		// 기저 - 끝까지 다 봤을 때 
		if(index == cores.size()) {
			if(curCores > maxCores) {
				maxCores = curCores;
				minWireLen = curWireLen;
			}else if(curCores == maxCores) {
				minWireLen = Math.min(minWireLen, curWireLen);
			}
			return;
		}
		
		// 현재 코어 연결하기 : 4방향으로 전선 깔아보기 
		for(int dir = 0; dir < 4; dir++) {
			
			Core curCore = cores.get(index);
			
			int cr = curCore.r; int cc = curCore.c;
			int nr = cr + drs[dir]; int nc = cc + dcs[dir];
			int tempWireLen = 0;
			
			while(inBound(nr, nc)) {
				if(!canFill(nr, nc)) {
					tempWireLen = -1;
					break;
				}
				nr += drs[dir];
				nc += dcs[dir];
				tempWireLen++;
			}
			
			// 끝까지 연결됐는지 확인
			if(tempWireLen != -1) {
				fill(cr, cc, dir, 2);	// 전선 배치
				dfs(index+1, curCores+1, curWireLen+tempWireLen);	// 다음 코어
				fill(cr, cc, dir, 0);	// 전선 복구 
			}
		}
		
		// 현재 코어 연결 안 하기 
		dfs(index+1, curCores, curWireLen); 
	}

	public static void main(String[] args) throws IOException {
		
		// test cases
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			// output
			sb.append("#").append(tc).append(" ");
			
			// input - N
			N = Integer.parseInt(br.readLine());
			
			// map, cores
			map = new int[N][N];
			cores = new ArrayList<Core>();
			
			// input  - map, cores
			int data = 0;
			for(int r = 0 ; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				
				for(int c = 0 ; c < N ; c++) {
					data = Integer.parseInt(st.nextToken());
					
					if(data == 1 && !(r == 0 || r == N-1 || c == 0 || c == N-1)) {
						cores.add(new Core(r, c));
					}
					map[r][c] = data;
				}
			}
			
			// dfs
			maxCores = 0;
			minWireLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			// output
			sb.append(minWireLen).append("\n");
		}
		// print
		System.out.println(sb);
	}
}
