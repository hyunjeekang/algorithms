import sys
# import copy
input = sys.stdin.readline

GRID_SIZE = 9
grid = [list(map(int, input().split()))for _ in range(GRID_SIZE)]

# 0 cords
zero_cords = []
for r in range(GRID_SIZE):
    for c in range(GRID_SIZE):
        if grid[r][c] == 0:
            zero_cords.append((r,c))

zero_cnt = len(zero_cords)

# try possible nums @ 0

# check hor / ver / 3*3
    # 0~2 / 3~5 / 6~8
    # 3*int(a/3) -> 0, 3, 6

def get_possible_nums(r, c):
    numbers = [False]*10

    # check hor / ver nums
    for i in range(GRID_SIZE):
        numbers[grid[r][i]] = True
        numbers[grid[i][c]] = True

    # check 3*3 square nums
    rr = 3*int(r/3)
    cc = 3*int(c/3)
    for i in range(3):
        for j in range(3):
            num = grid[rr+i][cc+j]
            numbers[num] = True
    
    possible_nums = []
    for i in range(1, 10):
        if numbers[i]:
            continue
        possible_nums.append(i)

    return possible_nums

def dfs(idx):
    global sudoku, grid, result
    
    # if sudoku: return

    if idx == zero_cnt:
        # sudoku = True
        # result = copy.deepcopy(grid)
        # return
        for row in grid:
            print(*row)
        sys.exit(0)

    r, c = zero_cords[idx]
    if grid[r][c] != 0: return
    
    nums = get_possible_nums(r, c)
    
    for num in nums:
        grid[r][c] = num
        dfs(idx+1)
        grid[r][c] = 0


# sudoku = False
# result = []
dfs(0)
# for row in result:
#     print(*row)