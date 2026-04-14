import sys
from collections import deque

def solve():
    input = sys.stdin.readline
    n, m, k = map(int, input().split())
    
    if n == 1 and m == 1:
        print(1)
        return

    grid = [input().rstrip() for _ in range(n)]
    
    # visited[r][c][b] 벽을 b번 부수고 방문했는지 체크
    visited = [[[False] * (k + 1) for _ in range(m)] for _ in range(n)]
    
    # r, c, broken, dist, is_day
    queue = deque([(0, 0, 0, 1, 1)])
    visited[0][0][0] = True
    
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]
    
    while queue:
        r, c, broken, dist, is_day = queue.popleft()
        
        for i in range(4):
            nr, nc = r + dr[i], c + dc[i]
            
            if 0 <= nr < n and 0 <= nc < m:
                # 목적지
                if nr == n - 1 and nc == m - 1:
                    print(dist + 1)
                    return
                
                # 빈칸
                if grid[nr][nc] == '0':
                    if not visited[nr][nc][broken]:
                        visited[nr][nc][broken] = True
                        queue.append((nr, nc, broken, dist + 1, 1 - is_day))
                
                # 벽
                elif broken < k:
                    if not visited[nr][nc][broken + 1]:
                        if is_day: # 낮 - 부수고 이동
                            visited[nr][nc][broken + 1] = True
                            queue.append((nr, nc, broken + 1, dist + 1, 0))
                        else: # 밤 - 대기
                            queue.append((r, c, broken, dist + 1, 1))
                            
    print(-1)

solve()