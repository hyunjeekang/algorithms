tc = int(input())
def solve(n, nums):
    min, max = 1000001, 0
    for i in range(n):
        cur = nums[i]
        if cur < min: 
            min = cur
        elif cur > max:
            max = cur
    return max - min

for t in range(1, tc+1):
    n = int(input())
    nums = list(map(int, input().split()))
    print(f'#{t} {solve(n, nums)}')