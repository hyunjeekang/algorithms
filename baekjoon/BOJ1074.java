import java.io.*;
import java.util.*;

public class BOJ1074 {

    static int N, R, C, result;
    static int[][] map;
    static int num = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        recur((int)Math.pow(2,N), 0, 0, 0);
        System.out.println(result);
    }   

    private static void recur(int cur, int num, int r, int c){

        if(r == R && c == C){
            result = num;
            return;
        }

        int half = cur / 2;
        int area = half * half;

        if (R < r + half && C < c + half) {
            recur(half, num, r, c);
        }
        else if (R < r + half && C >= c + half) {
            recur(half, num + area, r, c + half);
        }
        else if (R >= r + half && C < c + half) {
            recur(half, num + (area * 2), r + half, c);
        }
        else {
            recur(half, num + (area * 3), r + half, c + half);
        }
    }
}
