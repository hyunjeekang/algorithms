import sys
input = sys.stdin.readline

N = int(input())
grid = [list(map(int, input().split())) for _ in range(N)]

move = [[0, 2], [1, 2], [0, 1, 2]]
drs = [0, 1, 1]
dcs = [1, 0, 1]

def solve():
    if grid[N-1][N-1] == 1:
        print(0)
        return

    stack = [(0, 1, 0)]
    count = 0
    
    while stack:
        r, c, state = stack.pop()
        
        if r == N-1 and c == N-1:
            count += 1
            continue
            
        for dir in move[state]:
            nr, nc = r + drs[dir], c + dcs[dir]
            
            if 0 <= nr < N and 0 <= nc < N and grid[nr][nc] == 0:
                if dir == 2:
                    if grid[r][nc] != 0 or grid[nr][c] != 0:
                        continue
                stack.append((nr, nc, dir))
    
    print(count)

solve()