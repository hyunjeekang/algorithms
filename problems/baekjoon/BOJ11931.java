import java.io.*;
import java.util.*;

public class BOJ11931 {
	
	static int N;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>(N);
		
		for(int n = 0 ; n < N; n++) {
			list.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(list, Collections.reverseOrder());
		
		for(int n = 0 ; n < N; n++) {
			sb.append(list.get(n)).append("\n");
		}
		System.out.println(sb);
	}
}