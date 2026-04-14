import heapq
import sys
input = sys.stdin.readline

V, E = map(int, input().split())
start = int(input().strip())
graph = [[]for _ in range(V+1)]

for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

def dijkstra():
    hq = []
    heapq.heappush(hq, (0, start))

    dist = [float('inf')]*(V+1)
    dist[start] = 0

    while hq:
        # 큐에서 현재 가장 비용이 적은 노드 팝
        curWeight, curNode = heapq.heappop(hq)

        # 이미 더 짧은 경로로 처리된 적이 있다면 무시
        if dist[curNode] < curWeight:
            continue
        
        # 현재 노드를 거쳐서 가는 새로운 거리 계산
        for nextNode, weight in graph[curNode]:
            newWeight = curWeight + weight

            # 기존에 알고 있던 거리보다 더 짧다면 업데이트
            if newWeight < dist[nextNode]:
                dist[nextNode] = newWeight
                heapq.heappush(hq, (newWeight, nextNode))

    for i in range(1, V+1):
        if dist[i] == float('inf'):
            print('INF')
        else: print(dist[i])

dijkstra()