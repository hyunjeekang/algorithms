import java.io.*;
import java.util.*;

public class BOJ15663 {

    static int N;
    static int M;
    static int[] nums;
    static int[] comb;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        comb = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int depth) {

        if (depth == M) {

            for (int num : comb) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int lastUsed = -1;

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            if (lastUsed == nums[i])
                continue;

            visited[i] = true;
            comb[depth] = nums[i];
            lastUsed = nums[i];

            dfs(depth + 1);
            visited[i] = false;
        }

    }

}
