# from collections import deque

N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]

drs = [0, 0, -1, 1]
dcs = [-1, 1, 0, 0]
max_safe_zone = 0
default_walls = 0
for row in grid:
    default_walls += row.count(1)

# def bfs(r, c, checked_virus):
#     q = deque([])
#     q.append((r, c))
#     count = 1

#     while q:
#         cr, cc = q.popleft()
#         for i in range(4):
#             nr, nc = cr + drs[i], cc + dcs[i]
#             if 0 <= nr < N and 0 <= nc < M:
#                 if grid[nr][nc] == 0 and not checked_virus[nr][nc]:
#                     checked_virus[nr][nc] = True
#                     q.append((nr, nc))
#                     count += 1
#     return count

def dfs(r, c, checked_virus):
    for i in range(4):
        nr, nc = r + drs[i], c + dcs[i]

        if 0 <= nr < N and 0 <= nc < M:
            if grid[nr][nc] == 0 and not checked_virus[nr][nc]:
                checked_virus[nr][nc] = True
                dfs(nr, nc, checked_virus)

def backtrack(r, c, walls):
    global max_safe_zone

    if walls == 3:
        checked_virus = [[False]*M for _ in range(N)]

        for row in range(N):
            for col in range(M):
                if grid[row][col] == 2 and not checked_virus[row][col]:
                    checked_virus[row][col] = True
                    # bfs(row, col, checked_virus)
                    dfs(row, col, checked_virus)

        cur_safe_zone = 0
        for r in range(N):
            for c in range(M):
                if grid[r][c] == 0 and not checked_virus[r][c]:
                    cur_safe_zone += 1
        
        max_safe_zone = max(max_safe_zone, cur_safe_zone)
        return

    # 현재 행(r)의 나머지 열(c ~ M-1) 탐색
    for cc in range(c, M):
        if grid[r][cc] == 0:
            grid[r][cc] = 1
            backtrack(r, cc + 1, walls + 1)
            grid[r][cc] = 0

    # 그 다음 행(r+1)부터 마지막 행까지는 열을 0부터 탐색
    for cr in range(r + 1, N):
        for cc in range(M):
            if grid[cr][cc] == 0:
                grid[cr][cc] = 1
                backtrack(cr, cc + 1, walls + 1)
                grid[cr][cc] = 0

    # # 2차원 탐색을 1차원 인덱스로 변환 -> 중복 없는 조합 생성
    # for i in range(index, N * M):
    #     cr = i // M
    #     cc = i % M
    #     if grid[cr][cc] == 0:
    #         grid[cr][cc] = 1
    #         backtrack(i + 1, walls + 1)
    #         grid[cr][cc] = 0
    # backtrack(0,0)

backtrack(0, 0, 0)
print(max_safe_zone)