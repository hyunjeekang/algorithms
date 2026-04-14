import sys
input = sys.stdin.readline
N, M = map(int, input().split())
grid = [i for i in range(1, N+1)]
for _ in range(M):
    i, j = map(int, input().split())
    grid[i-1:j] = grid[i-1:j][::-1]
print(*grid)