import java.io.*;
import java.util.*;

public class SWEA1251 {

    public static class Edge implements Comparable<Edge> {
        int from, to;
        long weight; // long

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static class KruskalMST {
        private int[] parent;
        private int[] rank;
        private List<Edge> edges;

        public KruskalMST(int N) {
            parent = new int[N];
            rank = new int[N];
            edges = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return (parent[x] = find(parent[x]));
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
                else if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }

        // 총합 반환
        public long getMstSum(int N) {
            Collections.sort(edges);
            long total = 0;
            int count = 0;
            for (Edge edge : edges) {
                if (find(edge.from) != find(edge.to)) {
                    union(edge.from, edge.to);
                    total += edge.weight;
                    if (++count == N - 1) break;
                }
            }
            return total;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            long[] x = new long[N];
            long[] y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) x[i] = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) y[i] = Long.parseLong(st.nextToken());
            
            double E = Double.parseDouble(br.readLine().trim());

            KruskalMST mst = new KruskalMST(N);

            // 모든 간선
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    long l2 = dx * dx + dy * dy;
                    mst.addEdge(new Edge(i, j, l2));
                }
            }

            // 결과 계산 & 반올림
            long total = mst.getMstSum(N);
            double result = total * E;
            System.out.println("#" + t + " " + Math.round(result));
        }
    }
}