GRID_SIZE = 10
CONFETTI_COUNT = 5

grid = [list(map(int, input().split())) for _ in range(GRID_SIZE)]
confetti = [0] + [CONFETTI_COUNT for _ in range(CONFETTI_COUNT)]     # [0, 5, 5, 5, 5, 5]
min_attached_confetti = float('inf')

def check_confetti_size(r, c, size):
    if r + size > GRID_SIZE or c + size > GRID_SIZE:
        return False
    for dr in range(size):
        for dc in range(size):
            if grid[r + dr][c + dc] != 1:
                return False
    return True

def draw_confetti(r, c, size, color):
    for dr in range(size):
        for dc in range(size):
            grid[r + dr][c + dc] = color

def backtrack(attached_confetti):
    global min_attached_confetti
    
    if attached_confetti >= min_attached_confetti:  # 이미 찾은 최소 색종이보다 더 쓰면 뒤로
        return

    sr, sc = -1, -1
    for r in range(GRID_SIZE):
        for c in range(GRID_SIZE):
            if grid[r][c] == 1:
                sr, sc = r, c
                break
        if sr != -1 : break
    
    if sr == -1 :     # 붙일 영역 없다면 최소 색종이 개수 비교 -> 갱신
        min_attached_confetti = attached_confetti if attached_confetti < min_attached_confetti else min_attached_confetti
        return
    
    for size in range(5, 0, -1):
        if confetti[size] != 0 and check_confetti_size(sr, sc, size):
            confetti[size] -= 1   # 색종이 소진
            draw_confetti(sr, sc, size, 0)

            backtrack(attached_confetti+1)
            
            draw_confetti(sr, sc, size, 1)  # 원복
            confetti[size] += 1   # 색종이 복구

backtrack(0)
print(min_attached_confetti if min_attached_confetti != float('inf') else -1)
