import sys
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N)]
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
    
exist = False
visited = [False]*N
def recur(node, sequence):
    global visited, exist
    
    if exist:
        return

    if sequence == 5:
        exist = True
        return
    
    if len(graph[node]) == 0:
        return
    
    for next in graph[node]:
        if not visited[next]:
            visited[next] = True
            recur(next, sequence+1)
            visited[next] = False

for i in range(N):
    visited[i] = True
    recur(i, 1)
    visited[i] = False
print(1 if exist else 0)