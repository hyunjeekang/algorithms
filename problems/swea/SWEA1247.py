import sys
input = sys.stdin.readline

def dist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)

# 회사 -> 고객 방문 -> 집
# 방문 순서 순열 -> 거리 합 계산
# 가장 짧은 거리 리턴

def solve(N, ccord, hcord, cords):
    visited = [False for _ in range(N)]
    perm = []
    min_dist_sum = float("inf") 

    def permutation(depth):
        nonlocal min_dist_sum
        
        if depth == N:
            dist_sum = 0

            # 방문 순서대로 거리 계산
            for i in range(0, N-1):
                dist_sum += dist(perm[i][0], perm[i][1], perm[i+1][0], perm[i+1][1])
            
            # 회사 -> 첫 고객 / 마지막 고객 -> 집 추가
            dist_sum += dist(ccord[0], ccord[1], perm[0][0], perm[0][1])
            dist_sum += dist(hcord[0], hcord[1], perm[-1][0], perm[-1][1])

            min_dist_sum = dist_sum if dist_sum < min_dist_sum else min_dist_sum
        
        else:
            for i in range(N):
                if not visited[i]:
                    visited[i] = True
                    perm.append(cords[i])
                    permutation(depth+1)
                    perm.pop()
                    visited[i] = False

    permutation(0)
    return min_dist_sum

T = int(input())
for t in range(1,T+1):
    N = int(input())
    inputs = list(map(int, input().split()))
    cx, cy, hx, hy = inputs[:4]
    inputs = inputs[4:]
    cords = []
    for i in range(0, len(inputs), 2):
        cords.append((inputs[i], inputs[i+1]))
    result = solve(N, (cx, cy), (hx, hy), cords)
    print(f"#{t} {result}")
    