import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ5430 {

    // static
    static final String ERROR = "error";

    // i/o
    static StringBuilder sb;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // input
    static int T;
    static String p;
    static int N;

    // deque
    static Deque<Integer> dq;

    public static boolean calculate(){
        boolean reverse = false;
        char command;

        for(int i = 0; i < p.length(); i++){

            command = p.charAt(i);
            if(command == 'R'){
                reverse = !reverse;
            }
            else if(command == 'D'){
                // empty -> error
                if(dq.isEmpty()) return false;
                
                // update reverse
                if(reverse) dq.pollLast();
                else dq.pollFirst();
            }
        }

        // output
        sb.append("[");
        while (!dq.isEmpty()) {
            sb.append(reverse ? dq.pollLast() : dq.pollFirst());
            if (!dq.isEmpty()) sb.append(",");
        }
        sb.append("]");

        return true;
    }

    public static void main(String[] args) throws IOException{
        // tc
        T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T; t++){
            
            // input p , N
            p = br.readLine();
            N = Integer.parseInt(br.readLine());

            // deque
            dq = new ArrayDeque<>();

            // input data
            String line = br.readLine();
            line = line.substring(1, line.length()-1); // delete '[', ']'
            st = new StringTokenizer(line, ",");    // delimiter : ','
            while(st.hasMoreTokens()){
                dq.add(Integer.parseInt(st.nextToken()));
            }

            // calculate
            sb = new StringBuilder();
            boolean result = calculate();

            // output
            if(!result) System.out.println(ERROR);
            else System.out.println(sb);
        }
    }
}
