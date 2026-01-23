import sys
from itertools import combinations

input = sys.stdin.readline

N, M = map(int, input().split())
result = 0

# numbers = list(map(int, input().split()))

# Calculate sums of all combinations and store them in a list (n**3)
# sum_of_nC3 = list(map(sum,combinations(numbers, 3)))
# result, min_diff = 0, M
# for cur_sum in sum_of_nC3:
#     if cur_sum > M:
#         continue
#     cur_diff = M - cur_sum
#     if cur_diff == 0:
#         result = cur_sum
#         break
#     if min_diff > cur_diff:
#         result = cur_sum
#         min_diff = cur_diff

# Generate combinations and compare immediately to save memory (n**3)
# for combo in combinations(numbers, 3):
#     current_sum = sum(combo)
    
#     if result < current_sum <= M:
#         result = current_sum
        
#     if result == M:
#         break

# Fix one number and use two pointers (n**2)
numbers = sorted(list(map(int, input().split())))
for i in range(N-2):
    left = i + 1
    right = N - 1

    while left < right:
        current_sum = numbers[i] + numbers[left] + numbers[right]
        
        if current_sum <= M:
            result = max(result, current_sum)
            left += 1
        else:
            right -= 1

    if result == M:break

print(result)
