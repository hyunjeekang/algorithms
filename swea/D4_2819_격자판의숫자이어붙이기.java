package jadegaja;

import java.io.*;
import java.util.*;

public class SWEA2819 {
	
	final static int[] drs = {0, 0, -1, 1};
	final static int[] dcs = {1, -1, 0, 0};
	
	static int T, nr, nc;
	static String[][] grid;
	static String number;
	static StringBuilder curs;
	static Set<String> set;
	
	public static boolean inBounds(int r, int c) {
		return 0 <= r && r < 4 && 0 <= c && c < 4;
	}
	
	public static void dfs(int r, int c, int depth, String str) {
		
		if(depth == 6) {
			set.add(str);
			return;
		}
		
		for(int i = 0 ; i < 4; i++) {
			nr = r + drs[i];
			nc = c + dcs[i];
			
			if(inBounds(nr, nc)) {
				dfs(nr, nc, depth+1, str + grid[nr][nc]);
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			
			// 입력
			grid = new String[4][4];
			for(int r = 0 ; r < 4; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < 4; c++) {
					grid[r][c] = st.nextToken();
				}
			}
			
			// dfs
			set = new HashSet<String>();
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 4; c++) {
					number = null;
					dfs(r, c, 0, grid[r][c]);
				}
			}
			
			sb.append('#').append(t).append(' ').append(set.size()).append('\n');
		}
		System.out.println(sb);
	}
}
