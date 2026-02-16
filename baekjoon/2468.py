import sys
from collections import deque
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input().strip())
grid = [list(map(int, input().split()))for _ in range(N)]
max_height = 0 
for row in grid:
    max_height = max(max(row), max_height)

drs = [-1, 1, 0, 0]
dcs = [0, 0, -1, 1]

def safe(r, c, rain):
    if grid[r][c] > rain:
        return True
    return False

def in_bound(r, c):
    return 0 <= r < N and 0 <= c < N

def dfs(r, c, rain):
    global visited
    for i in range(4):
        nr, nc = r + drs[i], c + dcs[i]
        if in_bound(nr, nc) and safe(nr, nc, rain) and not visited[nr][nc]:
            visited[nr][nc] = True
            dfs(nr, nc, rain)

def bfs(r, c, rain):
    global visited
    q = deque([])
    q.append((r, c))

    while q:
        cr, cc = q.popleft()

        for i in range(4):
            nr, nc = cr + drs[i], cc + dcs[i]
            if in_bound(nr, nc) and safe(nr, nc, rain) and not visited[nr][nc]:
                visited[nr][nc] = True
                q.append((nr, nc))
    

max_safe_zone = 0
for rain in range(0, max_height+1): # 비가 안 와서 아무 곳도 잠기지 않을 수 있음
    cur_safe_zone = 0
    visited = [[False]*N for _ in range(N)]
    for r in range(N):
        for c in range(N):
            if safe(r, c, rain) and not visited[r][c]:
                visited[r][c] = True
                cur_safe_zone +=1
                # dfs(r, c, rain)
                bfs(r, c, rain)
    max_safe_zone = max(cur_safe_zone, max_safe_zone)
print(max_safe_zone)