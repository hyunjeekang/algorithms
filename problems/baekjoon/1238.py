import sys
import heapq
input = sys.stdin.readline

N, M, X = map(int, input().split())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    s, e, w = map(int, input().split())
    graph[s].append((e, w))

# 각 마을 <-> X마을 최단 거리
NtoX = [[] for _ in range(N+1)]

def dijkstra(start, end):
    hq = []
    dist = [float('inf')]*(N+1)
    heapq.heappush(hq, (0, start))  # weight, next
    dist[start] = 0

    while hq:
        cweight, cur = heapq.heappop(hq)
        
        if dist[cur] < cweight:
            continue

        for next, weight in graph[cur]:
            nweight = dist[cur] + weight    # 다음 노드의 최단거리 후보 : 현재최단거리 + 현재노드 -> 다음노드 비용
            if nweight <  dist[next]:       # 후보 vs 최단거리 비교
                dist[next] = nweight        # 갱신
                heapq.heappush(hq, (weight, next))  # 다음 탐색

    return abs(dist[end])

for i in range(1, N+1):
    NtoX[i] = dijkstra(i, X) + dijkstra(X, i)   # 왕복 최단거리

print(max(NtoX[1:]))