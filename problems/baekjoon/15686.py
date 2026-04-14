"""
치킨 거리 -> 집과 가장 가까운 치킨집 사이 거리(맨해튼 거리)
도시의 치킨 거리 : 모든 집의 치킨 거리 합 
0 빈 칸 1 집 2 치킨집
"""

import sys
input = sys.stdin.readline
N, M = map(int, input().split())
city = [list(map(int, input().split()))for _ in range(N)]
store_cords = []
house_cords = []

for r in range(N):
    for c in range(N):
        if city[r][c] == 1:
            house_cords.append((r, c))
        if city[r][c] == 2:
            store_cords.append((r, c))

store_cnt = len(store_cords)

def calc_chicken_dist(r1, r2, c1, c2):
    return abs(r1 - r2) + abs(c1 - c2)

"""
전체 치킨집 -> M개만 남기고 cnt-M개 치킨집 폐업
true/false로 폐업 여부 결정하기
조합 nCm
도시의 치킨 거리 최솟값 출력
"""

def house_chicken_dist(hr, hc):
    min_chicken_dist = float('inf')
    for idx in range(store_cnt):
        if not close[idx]:
            cr, cc = store_cords[idx]
            min_chicken_dist = min(min_chicken_dist, calc_chicken_dist(hr, cr, hc, cc))
    return min_chicken_dist


min_city_chicken_dist = float('inf')
close = [True]*store_cnt
def combination(start, depth):
    global close, min_city_chicken_dist

    if depth == M:  # M개 뽑으면
        # 현재 도시 치킨 거리 합 계산
        cur_city_chicken_dist = 0
        for hr, hc in house_cords:
            cur_city_chicken_dist += house_chicken_dist(hr, hc)
        
        # 최소 도시 치킨 거리 갱신
        min_city_chicken_dist = min(min_city_chicken_dist, cur_city_chicken_dist)
        return

    for idx in range(start, store_cnt):
        close[idx] = False
        combination(idx+1, depth+1)
        close[idx] = True

combination(0, 0)
print(min_city_chicken_dist)