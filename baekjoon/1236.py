import sys
input = sys.stdin.readline
N, M = map(int, input().split())
grid = [list(input().strip()) for _ in range(N)]
empty_row = empty_col = addedguard = 0

for r in range(N):
    if 'X' not in grid[r]:
        empty_row += 1

for c in range(M):
    isempty = True
    for r in range(N):
        if grid[r][c] == 'X':
            isempty = False
            break
    if isempty: empty_col += 1

print(max(empty_col, empty_row))