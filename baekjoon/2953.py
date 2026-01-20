grid = [list(map(int, input().split()))for _ in range(5)]
winner_point = winner_idx = 0
for idx, row in enumerate(grid):
    temp_sum = sum(row)
    if temp_sum > winner_point:
        winner_point = temp_sum
        winner_idx = idx+1
print(winner_idx, winner_point)

# scores = [sum(map(int, input().split())) for _ in range(5)]
# print(scores.index(max(scores)) + 1, max(scores))