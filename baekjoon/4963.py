import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

drs = [1, 1, 1, -1, -1, -1, 0, 0]
dcs = [-1, 0, 1, -1, 0, 1, -1, 1]

def in_bound(r, c):
    return 0 <= r < h and 0 <= c < w

def dfs(r, c):
    for i in range(8):
        nr, nc = r + drs[i], c + dcs[i]
        if in_bound(nr, nc) and grid[nr][nc] == 1:
            grid[nr][nc] = 0
            dfs(nr, nc)
    return

while True:
    w, h = map(int, input().split())
    if w == 0 and h == 0 : break
    grid = [list(map(int, input().split()))for _ in range(h)]

    count = 0
    for r in range(h):
        for c in range(w):
            if grid[r][c] == 1:
                count += 1
                grid[r][c] = 0
                dfs(r, c)
    
    print(count)