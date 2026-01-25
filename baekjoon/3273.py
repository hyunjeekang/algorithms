import sys
input = sys.stdin.readline
n = int(input())
nums = list(map(int, input().split()))
x = int(input())
nums.sort()

count = start = 0
end = n-1
checked = None
while start < end:
    s = nums[start]
    e = nums[end]
    cur = s + e

    if cur == x:
        count += 1
        start += 1
        end -= 1
    elif cur < x:
        start += 1
    elif cur > x:
        end -= 1

print(count)