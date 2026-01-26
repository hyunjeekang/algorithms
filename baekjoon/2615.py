import sys
grid = [list(map(int, sys.stdin.readline().split())) for _ in range(19)]

dr = [0, 1, 1, -1]
dc = [1, 0, 1, 1]

for r in range(19):
    for c in range(19):
        if grid[r][c] != 0:
            color = grid[r][c]

            for i in range(4):
                pr, pc = r - dr[i], c - dc[i]
                if 0 <= pr < 19 and 0 <= pc < 19 and grid[pr][pc] == color:
                    continue

                count = 1
                for k in range(1, 5):
                    nr, nc = r + dr[i] * k, c + dc[i] * k 
                    if 0 <= nr < 19 and 0 <= nc < 19 and grid[nr][nc] == color:
                        count += 1
                    else:
                        break
                
                if count == 5:
                    nr, nc = r + dr[i] * 5, c + dc[i] * 5
                    
                    if not (0 <= nr < 19 and 0 <= nc < 19 and grid[nr][nc] == color):
                        print(color)
                        print(r + 1, c + 1)
                        sys.exit()
print(0)