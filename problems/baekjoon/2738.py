N, M = map(int, input().split())
grid1 = [list(map(int, input().split())) for _ in range(N)]
grid2 = [list(map(int, input().split())) for _ in range(N)]
# for r in range(N):
#     for c in range(M):
#         grid1[r][c] += grid2[r][c]
# for row in grid1: print(*row)
result = [[c+d for c, d in zip(a, b)] for a, b in zip(grid1, grid2)]
for row in result: print(*row)