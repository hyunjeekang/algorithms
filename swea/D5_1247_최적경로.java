import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1247 {

    static class Cord {
        int r, c;
        public Cord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static Cord homeCord, workCord;
    static ArrayList<Cord> customerCords;
    static boolean[] visited;
    static int minDist;

    public static int calcDist(Cord c1, Cord c2) {
        return Math.abs(c1.r - c2.r) + Math.abs(c1.c - c2.c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            
            workCord = new Cord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            homeCord = new Cord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            
            customerCords = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                customerCords.add(new Cord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            minDist = Integer.MAX_VALUE;
            visited = new boolean[N];

            dfs(workCord, 0, 0);

            sb.append('#').append(t).append(' ').append(minDist).append('\n');
        }
        System.out.print(sb);
    }

    public static void dfs(Cord current, int count, int totalDist) {
        if (totalDist >= minDist) return;

        if (count == N) {
            int finalDist = totalDist + calcDist(current, homeCord);
            minDist = Math.min(minDist, finalDist);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Cord next = customerCords.get(i);
                dfs(next, count + 1, totalDist + calcDist(current, next));
                visited[i] = false;
            }
        }
    }
}