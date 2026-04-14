import java.io.*;

public class BOJ1003 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[] countZero = new int[41];
        int[] countOne = new int[41];

        // 초기값 (N이 0일 때와 1일 때)
        countZero[0] = 1; countOne[0] = 0;
        countZero[1] = 0; countOne[1] = 1;

        // 40까지 계산
        for(int i = 2; i <= 40; i++){
            countZero[i] = countZero[i - 1] + countZero[i - 2];
            countOne[i] = countOne[i - 1] + countOne[i - 2];
        }

        for(int t = 0 ; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            sb.append(countZero[n]).append(" ").append(countOne[n]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}