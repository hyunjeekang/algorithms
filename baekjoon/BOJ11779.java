import java.io.*;
import java.util.*;

public class BOJ11779 {
    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solve(n, graph, start, end);
    }

    public static void solve(int n, ArrayList<Node>[] graph, int start, int end) {
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curV = current.vertex;
            int curW = current.weight;

            // 이미 처리된 경로가 더 짧다면 스킵
            if (dist[curV] < curW) continue;

            for (Node next : graph[curV]) {
                // 누적 거리 비교
                if (dist[next.vertex] > dist[curV] + next.weight) {
                    dist[next.vertex] = dist[curV] + next.weight;
                    prev[next.vertex] = curV; // 역추적 저장
                    
                    // 해당 정점까지의 전체 누적 거리 넣기
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");

        // 역추적 경로 생성
        Stack<Integer> path = new Stack<>();
        int curr = end;
        while (curr != 0) {
            path.push(curr);
            curr = prev[curr];
            if (curr == start) {
                path.push(start);
                break;
            }
        }
        
        // 시작 노드부터 끝 노드까지의 경로 복구
        List<Integer> resultPath = new ArrayList<>();
        int target = end;
        while(target != 0) {
            resultPath.add(target);
            target = prev[target];
        }

        sb.append(resultPath.size()).append("\n");
        for (int i = resultPath.size() - 1; i >= 0; i--) {
            sb.append(resultPath.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}