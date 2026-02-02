tc = int(input())

def solve(n, m, nums):
    max_sum = 0
    min_sum = 10000*m + 1
    for i in range(n-m+1):
        cur_sum = 0
        for j in range(i, i+m):
            cur_sum += nums[j]
        if cur_sum < min_sum: min_sum = cur_sum
        if cur_sum > max_sum: max_sum = cur_sum
    
    return max_sum - min_sum

for t in range(1, tc+1):
    n, m = map(int, input().split())
    nums = list(map(int, input().split()))
    print(f'#{t} {solve(n, m, nums)}')
