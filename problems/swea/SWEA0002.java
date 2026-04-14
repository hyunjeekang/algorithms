class SWEA0002{

    public static void main(String[] args) {
        int[] memo = new int[10];
        memo[1] = 2; //파, 노
        memo[2] = 5; //파파, 노파, 노노, 노파, 빨

        for(int i = 3; i < 7; i++){
            memo[i] = memo[i-1]*2 + memo[i-2];
        }

        System.out.println(memo[6]);
    }
    
}
