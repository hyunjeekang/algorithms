from collections import deque

n = int(input().strip())
start, target = map(int, input().split())
m = int(input().strip())
graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

# ----------- dfs ---------------

dist_list = [-1] * (n + 1)
def dfs(cur, d):
    dist_list[cur] = d
    
    for next_node in graph[cur]:
        if dist_list[next_node] == -1:
            dfs(next_node, d + 1)

dfs(start, 0)
print(dist_list[target])

# ----------- bfs ---------------

# visited = [False]*(n+1)
# def bfs(cur, target):
#     dist = 0
#     visited[cur] = True
#     q = deque([(cur, target, dist)])

#     while q:
#         cur, target, dist = q.popleft()
#         if cur == target:
#             return dist
        
#         for next in graph[cur]:
#             if not visited[next]:
#                 visited[next] = True
#                 q.append((next, target, dist+1))

#     if not visited[target]:
#         return -1

# print(bfs(start, target))
