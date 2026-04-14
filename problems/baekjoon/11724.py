import sys
sys.setrecursionlimit(10**6)
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[]for _ in range(N+1)]

for _ in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

visited = [False]*(N+1)

def dfs(node):
    for next in graph[node]:
        if not visited[next]:
            visited[next] = True
            dfs(next)
    return

# def bfs(node):
#     q = deque([])
#     q.append(node)

#     while q:
#         curnode = q.pop()

#         for next in graph[curnode]:
#             if not visited[next]:
#                 visited[next] = True
#                 q.append(next)

count = 0
for node in range(1, N+1):
    if not visited[node]:
        count += 1
        visited[node] = True
        dfs(node)
        # bfs(node)

print(count)