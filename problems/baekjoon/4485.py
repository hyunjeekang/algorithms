import sys
import heapq
input = sys.stdin.readline

drs = [-1, 1, 0, 0]
dcs = [0, 0, -1, 1]

def dijkstra():
    hq = []
    dist = [[float('inf')]*N for _ in range(N)]
    dist[0][0] = grid[0][0]
    heapq.heappush(hq, (0, 0, 0))   # rp, r, c

    while hq:
        crp, cr, cc = heapq.heappop(hq)

        if dist[cr][cc] < crp:
            continue

        for i in range(4):
            nr, nc = cr + drs[i], cc + dcs[i]
            if nr < 0 or nc < 0 or nr >= N or nc >= N:
                continue
            
            nrp = dist[cr][cc] + grid[nr][nc]
            if nrp < dist[nr][nc]:
                dist[nr][nc] = nrp
                heapq.heappush(hq, (grid[nr][nc], nr, nc))

    return dist[N-1][N-1]

p = 1
while True:
    try:
        line = input().split()
        N = int(line[0])
        if N == 0: break
        grid = [list(map(int, input().split())) for _ in range(N)]
        result = dijkstra()
        
        print(f'Problem {p}: {result}')
        p += 1
        
    except EOFError:
        break