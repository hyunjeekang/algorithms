import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))
add, sub, mul, div = map(int, input().split())

max_res = -float('inf')
min_res = float('inf')

def dfs(idx, cur_res, add, sub, mul, div):
    global max_res, min_res
    
    if idx == N:
        max_res = max(max_res, cur_res)
        min_res = min(min_res, cur_res)
        return

    if add > 0:
        dfs(idx + 1, cur_res + nums[idx], add - 1, sub, mul, div)
    
    if sub > 0:
        dfs(idx + 1, cur_res - nums[idx], add, sub - 1, mul, div)
        
    if mul > 0:
        dfs(idx + 1, cur_res * nums[idx], add, sub, mul - 1, div)
        
    if div > 0:
        dfs(idx + 1, int(cur_res / nums[idx]), add, sub, mul, div - 1)

dfs(1, nums[0], add, sub, mul, div)

print(max_res)
print(min_res)