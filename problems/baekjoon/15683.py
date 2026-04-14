import sys
input = sys.stdin.readline

N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]

# 우, 하, 좌, 상
drs = [0, 1, 0, -1]
dcs = [1, 0, -1, 0]

cctv = []
dir_list = [] # 각 CCTV가 선택한 회전 방향 (0: 0도, 1: 90도, 2: 180도, 3: 270도)
cctv_dir = [[],[0],[0, 2],[0, 1],[0, 1, 2],[0, 1, 2, 3]] # CCTV 타입별 기본 감시 방향
cctv_cnt = 0
min_blind_cnt = float('inf')
cctvs = {1, 2, 3, 4, 5}

# 1. CCTV 위치 수집
for r in range(N):
    for c in range(M):
        if grid[r][c] in cctvs:
            cctv.append((r, c))
            cctv_cnt += 1

def in_bound(cord):
    r, c = cord
    return 0 <= r < N and 0 <= c < M

def search():
    # 매 시뮬레이션마다 감시 영역을 기록할 새로운 배열 생성
    searched = [[False]*M for _ in range (N)]
    
    for t in range(cctv_cnt):
        orr, orc = cctv[t] # CCTV 원본 위치 고정

        # 선택된 회전 방향이 반영된 감시 방향 리스트 가져오기
        directions = get_dirlist(grid[orr][orc], dir_list[t])

        for d in directions:
            dr, dc = drs[d], dcs[d]
            cr, cc = orr, orc # 탐색 시작할 때마다 본체 위치에서 출발
            
            while True:
                nr, nc = cr + dr, cc + dc

                if in_bound((nr, nc)):
                    if grid[nr][nc] == 6: # 벽을 만나면 탐색 중단
                        break
                    if grid[nr][nc] == 0: # 빈칸일 경우 감시 표시
                        searched[nr][nc] = True
                    # 다음 칸으로 전진 (다른 CCTV가 있어도 통과 가능)
                    cr, cc = nr, nc
                else: # 맵 밖으로 나가면 중단
                    break

    return searched

def get_blind_cnt(searched):
    cnt = 0
    for r in range(N):
        for c in range(M):
            if grid[r][c] == 0 and not searched[r][c]:
                cnt += 1
    return cnt

def get_dirlist(cctv_type, index):
    # 원본 cctv_dir 리스트 보존을 위해 복사
    res = cctv_dir[cctv_type][:]
    for i in range(len(res)):
        res[i] = (res[i] + index) % 4 
    return res

def backtrack(depth):
    global min_blind_cnt
    # 기저 조건: 모든 CCTV의 방향이 결정되었을 때
    if depth == cctv_cnt:
        cur_blind_cnt = get_blind_cnt(search())
        min_blind_cnt = min(min_blind_cnt, cur_blind_cnt)
        return
    
    # 각 CCTV별로 4가지 회전 방향 시도
    for i in range(0, 4):
        dir_list.append(i)
        backtrack(depth+1)
        dir_list.pop() # 원복

if cctv_cnt == 0:
    # CCTV가 없는 경우 초기 빈칸 개수 계산
    zone = 0
    for row in grid:
        zone += row.count(0)
    print(zone)
else:
    backtrack(0)
    print(min_blind_cnt)