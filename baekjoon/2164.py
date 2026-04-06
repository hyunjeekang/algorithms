from collections import deque

N = int(input())
q = deque(range(1, N + 1))

# 큐에 카드가 한 장 남을 때까지 반복
while len(q) > 1:
    # 1. 가장 위에 있는 카드 버리기
    q.popleft()
    
    # 카드를 버린 후 한 장만 남았다면 바로 종료
    if len(q) == 1:
        break
        
    # 2. 가장 위에 있는 카드 아래로 옮기기
    q.append(q.popleft())

print(q[0])