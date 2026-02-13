import heapq
import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
graph = [[]for _ in range(N+1)]
for _ in range(M):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))
start, dest = map(int, input().split())

def dijkstra():

    hq = []
    dist = [float('inf')]*(N+1)
    heapq.heappush(hq, (start, 0))
    dist[start] = 0

    while hq:
        cn, cw = heapq.heappop(hq)

        if dist[cn] < cw:
            continue

        for nn, w in graph[cn]:
            nw = dist[cn] + w
            if nw < dist[nn]:
                dist[nn] = nw
                heapq.heappush(hq, (nn, w)) 

    print(dist[dest])

dijkstra()