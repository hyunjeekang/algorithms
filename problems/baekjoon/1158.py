N, K = map(int, input().split())

people = [i for i in range(1, N + 1)]
result = []

idx = 0

for _ in range(N):
    idx = (idx + K - 1) % len(people)
    
    # 해당 인덱스의 사람을 꺼내서 결과에 추가
    result.append(people.pop(idx))

print(f"<{', '.join(map(str, result))}>")

# from collections import deque

# N, K = map(int, input().split())

# # 1번부터 N번까지 큐에 넣기
# q = deque(range(1, N + 1))
# result = []

# while q:
#     # K-1번만큼 앞에서 빼서 뒤로 다시 넣기 (회전)
#     for _ in range(K - 1):
#         q.append(q.popleft())
    
#     # K번째 사람을 완전히 제거하여 결과 리스트에 담기
#     result.append(q.popleft())

# print("<" + ", ".join(map(str, result)) + ">")