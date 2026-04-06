import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654 {
    
    // i/o
    static StringTokenizer st;
    static StringBuilder sb;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // input
    static int N;
    static int M;
    static int[] numbers;

    // comb
    static int[] result;
    static boolean[] visited;

    public static void combination(int depth){
        if(depth == M){
            for (Integer num : result) {
                sb.append(num).append(" ");
            }sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                result[depth] = numbers[i];
                combination(depth+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        
        // input N, M
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init/input numbers
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0 ; n < N ; n++){
            numbers[n] = Integer.parseInt(st.nextToken());
        }
        
        // sort
        Arrays.sort(numbers);

        // output
        sb = new StringBuilder();
        result = new int[M];
        visited = new boolean[N];
        combination(0);
        System.out.println(sb);
    }
}
