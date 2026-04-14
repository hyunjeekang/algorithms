import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ7662 {

	static int T, k, value;
	static String command;
	static TreeMap<Integer, Integer> treeMap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine().strip());

		for (int t = 0; t < T; t++) {
			k = Integer.parseInt(br.readLine().strip());

			treeMap = new TreeMap<>();

			for (int kk = 0; kk < k; kk++) {
				st = new StringTokenizer(br.readLine());
				command = st.nextToken();
				value = Integer.parseInt(st.nextToken());

				if (command.equals("I")) {
					treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);

				} else { // command == "D"
                    if(treeMap.isEmpty()) continue;

                    int key = (value == 1) ? treeMap.lastKey() : treeMap.firstKey();

                    int count = treeMap.get(key);
                    if(count == 1){
                        treeMap.remove(key);
                    }else{
                        treeMap.put(key, count-1);
                    }
				}
			}

			if (treeMap.isEmpty())
				sb.append("EMPTY\n");
			else
				sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
		}

		System.out.println(sb);

	}

}