import sys
input = sys.stdin.readline

drs = [1, -1, 0, 0]
dcs = [0, 0, -1, 1]

R, C = map(int, input().split())
grid = [list(input().strip()) for _ in range(R)]
alphabets = [False]*26
alphabets[ord(grid[0][0]) - ord('A')] = True
max_count = 0

def in_bound(r, c):
    return 0 <= r < R and 0 <= c < C

def dfs(r, c, count):
    global max_count
    
    if count > max_count: 
        max_count = count

    for i in range(4):
        nr, nc = r + drs[i], c + dcs[i]
        
        if in_bound(nr, nc):
            idx = ord(grid[nr][nc]) - ord('A')
            
            if not alphabets[idx]:
                alphabets[idx] = True
                dfs(nr, nc, count+1)
                alphabets[idx] = False

dfs(0,0,1)
print(max_count)