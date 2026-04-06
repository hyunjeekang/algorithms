import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class D1_1926 {
    public static void main(String[] args) throws IOException {
        // Scanner보다 빠른 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력받은 문자열을 정수로 변환
        int N = Integer.parseInt(br.readLine());
        
        // 출력을 한 번에 모아서 하기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= N; i++) {
            String numStr = String.valueOf(i);
            int clapCount = 0;
            
            // 3, 6, 9 개수 세기
            for (int j = 0; j < numStr.length(); j++) {
                char c = numStr.charAt(j);
                if (c == '3' || c == '6' || c == '9') {
                    clapCount++;
                }
            }
            
            // 횟수만큼 '-' 추가
            if (clapCount > 0) {
                for (int k = 0; k < clapCount; k++) {
                    sb.append("-");
                }
            } else {
                // 3, 6, 9가 없으면 숫자 그대로 추가
                sb.append(numStr);
            }
            
            // 띄어쓰기 추가
            sb.append(" ");
        }
        
        // 완성된 문자열을 한 번에 출력
        System.out.println(sb.toString().trim());
        
        br.close();
    }
}