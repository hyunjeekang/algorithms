tc=  int(input())

def solve(n, nums):
    max_sum = 0
    for i in range(n-1):
        cur_sum = nums[i] + nums[i+1]
        if cur_sum > max_sum : max_sum = cur_sum

    return max_sum

for t in range(1, tc+1):
    n = int(input())
    nums = list(map(int, input().split()))
    print(f'#{t} {solve(n, nums)}')