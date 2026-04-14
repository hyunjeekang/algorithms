N = int(input())
M = int(input())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    com1, com2 = map(int, input().split())
    graph[com1].append(com2)
    graph[com2].append(com1)

count = 0
visited = [False]*(N+1)
def dfs(com):
    global count
    visited[com] = True
    for next in graph[com]:
        if not visited[next]:
            count += 1
            dfs(next)

dfs(1)
print(count)
