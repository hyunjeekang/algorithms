class HW_0001_아파트{
    public static void main(String[] args) {
        int[] memo = new int[10];
        memo[1] = 2;
        memo[2] = 3;

        for(int i = 3; i < 10; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }

        System.out.println(memo[8]);
    }
}