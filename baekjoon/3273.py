import sys
input = sys.stdin.readline
n = int(input())
nums = list(map(int, input().split()))
x = int(input())

nums.sort()

count = 0
checked = None
bpt = n-1
for fpt in range(n):
    if fpt >= bpt:
        break
    while fpt < bpt and nums[fpt] + nums[bpt] > x:
        bpt -= 1
    if fpt < bpt and nums[fpt] + nums[bpt] == x:
        count += 1
        bpt -= 1

print(count)