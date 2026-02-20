############################## topology sort ##############################
import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[]for _ in range(N+1)]

indegree = [0]*(N+1)
result = [0]*(N+1)

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegree[b] += 1

q = deque([])
for i in range(1, N+1):
    if indegree[i] == 0:
        q.append(i)

while q:
    node = q.popleft()

    for next_node in graph[node]:
        indegree[next_node] -= 1
        result[next_node] = max(result[next_node], result[node] + 1)

        if indegree[next_node] == 0:
            q.append(next_node)

print(*result[1:])

############################## dp ##############################
"""
보통 위상 정렬 문제는 그래프의 순서를 알 수 없음 
다만 14567번에는 **"A < B인 입력만 주어진다"**는 제약 조건 O
: 그래프가 항상 번호가 낮은 곳에서 높은 곳으로만 향한다는 조건
-> 점화식으로 풀이하는 DP로도 풀이 가능
-> 점화식 : 현재 과목의 학기 : 모든 선수과목 중 최대 학기 + 1
-> 모든 선수과목(i보다 작은 번호들)의 DP 값은 이미 계산이 완료된 상태
"""
# import sys
# input = sys.stdin.readline

# N, M = map(int, input().split())
# adj = [[] for _ in range(N+1)]
# result = [1] * (N+1)

# for _ in range(M):
#     a, b = map(int, input().split())
#     adj[b].append(a)

# for i in range(1, N+1):
#     for pre in adj[i]:
#         result[i] = max(result[i], result[pre]+1)

# print(*result[1:])
