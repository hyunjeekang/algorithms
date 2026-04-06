import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static Stack<Character> operatorStack;
	
	public static void main(String[] args) throws IOException{
		
		
		char cur;
		int result = 0, temp = 1;
		
		operatorStack = new Stack<Character>();
		
		String oneLine = br.readLine();
		for(int i = 0 ; i < oneLine.length(); i++) {
			cur = oneLine.charAt(i);
			if(cur == '(') {
				operatorStack.add(cur);
				temp *= 2;
			}else if(cur == '[') {
				operatorStack.add(cur);
				temp *= 3;
			}else if(cur == ')') {
				if(operatorStack.isEmpty() || operatorStack.peek() != '(') {
					result = 0;
					break;
				}if(oneLine.charAt(i-1) == '(') {
					result += temp;
				}
				operatorStack.pop();
				temp /= 2;
			}else if(cur == ']') {
				if(operatorStack.isEmpty() || operatorStack.peek() != '[') {
					result = 0;
					break;
				}if(oneLine.charAt(i-1) == '[') {
					result += temp;
				}
				operatorStack.pop();
				temp /= 3;
			}
		}
		if(!operatorStack.isEmpty()) result = 0;
		System.out.println(result);
	}
}
