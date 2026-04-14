import java.util.*;
import java.io.*;

public class BOJ2448 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        if (input == null) return;
        int n = Integer.parseInt(input);

        map = new char[n][2 * n - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], ' ');
        }

        drawStars(0, n - 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void drawStars(int r, int c, int n) {
        if (n == 3) {
            map[r][c] = '*';
            map[r + 1][c - 1] = map[r + 1][c + 1] = '*';
            map[r + 2][c - 2] = map[r + 2][c - 1] = map[r + 2][c] = map[r + 2][c + 1] = map[r + 2][c + 2] = '*';
            return;
        }

        int m = n / 2;
        drawStars(r, c, m);             // 위
        drawStars(r + m, c - m, m);     // 좌하
        drawStars(r + m, c + m, m);     // 우하
    }
}
