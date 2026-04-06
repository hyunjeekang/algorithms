import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10026 {
	
	final static int[] drs = {0, 0, -1, 1};
	final static int[] dcs = {-1, 1, 0, 0};
	
	static int N;
	static int count;
	static char[][] grid;
	static boolean[][] visited;
	
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}	
	
	public static boolean inBound(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < N);
	}
	
	public static void bfs(int sr, int sc) {
		char color = grid[sr][sc];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(sr, sc));
		
		while(!queue.isEmpty()){	// while queue -> while(!queue.isEmpty())
			
			Node curNode = queue.poll();  		// pop -> poll
			int cr = curNode.r; int cc = curNode.c;
			
			for(int i = 0 ; i < 4; i++) {
				int nr = cr + drs[i]; int nc = cc + dcs[i];
				if(inBound(nr, nc) && !visited[nr][nc] && grid[nr][nc] == color) {
					visited[nr][nc] = true;
					queue.add(new Node(nr, nc));
				}
			}
		}
	}
	
	public static int getCount() {
		int count = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0 ; c < N; c++) {
				if(!visited[r][c]){	
					// "RGB" 문자열 안에서 grid[r][c] 글자를 찾았을 때 그 결과가 -1이 아니라면(존재한다면) String.indexOf()
					count ++;
					visited[r][c] = true;
					bfs(r, c);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		
		// 입출력 클래스
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine()); // <- throws IOException
		StringBuilder sb = new StringBuilder();
		
		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		grid = new char[N][N];	// 빈 문자열 초기화
		visited = new boolean[N][N];	// false 초기화
		
		for(int r = 0 ; r < N ; r++) {
			String inputLine = br.readLine();	// "RRRBB"처럼 한 줄만 읽어옴 .readline()
			for(int c = 0 ; c < N ; c++) {
				grid[r][c] = inputLine.charAt(c);	// c번째 문자를 배열에 삽입 .charAt(c)
			}
		}
		
		// 적록색약 아닌 사람
		count = getCount();
		sb.append(count).append(" ");
		
		
		// 적록색약인 사람
		visited = new boolean[N][N];	// visited 초기화
		
		for(int r = 0; r < N ; r++) {
			for(int c = 0 ; c < N ; c++) {
				if(grid[r][c] == 'R') {
					grid[r][c] = 'G';
				}
			}
		}
		
		count = getCount();
		sb.append(count);
		
		System.out.println(sb);
	}

}
