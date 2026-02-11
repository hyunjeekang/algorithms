import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
S, E = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

visited = [-1]*(N+1)

def bfs(start, end):
    q = deque([start])
    visited[start] = 0

    while q:
        cur = q.popleft()

        if cur == end:
            return visited[cur]

        for next in [cur-1, cur+1] + graph[cur]:
            if 0 < next <= N and visited[next] == -1 :
                visited[next] = visited[cur] + 1
                q.append(next)

print(bfs(S, E))
