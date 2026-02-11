from collections import deque

N, M = map(int, input().split())
S, E = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

visited = [-1]*(N+1)
def bfs(start, end):
    q = deque([(start, 0)])

    while q:
        cur, cur_time = q.popleft()

        for next in [cur-1, cur+1] + graph[cur]:
            if 1 <= next <= N and visited[next] == -1 :
                visited[next] = cur_time + 1
                q.append((next, cur_time+1))

    return visited[end]
    
print(bfs(S, E))
