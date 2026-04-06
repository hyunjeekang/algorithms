import java.util.*;
import java.io.*;

public class D4_7465_창용마을무리의개수 {

    public static class DisjointSet{

        private int[] parents;
        private int[] ranks;
        private int count;

        public DisjointSet(int n){
            parents = new int[n+1];
            ranks = new int[n+1];
            count = n;

            for(int i = 1 ; i < n+1 ; i++){
                parents[i] = i;
            }
        }

        public int find(int a){
            if(a == parents[a]) return a;
            return (parents[a] = find(parents[a]));
        }

        public void union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA != rootB){
                if(ranks[rootA] > ranks[rootB]){
                    parents[rootB] = rootA;
                }else if(ranks[rootA] < ranks[rootB]){
                    parents[rootA] = rootB;
                }else{
                    parents[rootB] = rootA;
                    ranks[rootA]++;
                }
                count--;
            }
        }

        public int getCount(){
            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        int N, M, a, b;
        for(int t = 1; t < T+1; t++){

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            DisjointSet djs = new DisjointSet(N);
            
            for(int m = 0 ; m < M; m++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                djs.union(a, b);
            }
            result.append('#').append(t).append(' ').append(djs.getCount()).append('\n');
        }
        System.out.print(result);
    }
}
