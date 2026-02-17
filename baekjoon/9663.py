import sys
input = sys.stdin.readline
N = int(input().strip())

row = [False] * N   
cross1 = [False] * (2 * N)  # 우상향 대각 r+c
cross2 = [False] * (2 * N)  # 우하향 대각 r-c+N

count = 0
def recur(r):
    global count

    if r == N:
        count += 1
        return
    
    for c in range(N):
        if not row[c] and not cross1[r+c] and not cross2[r-c+N]:
            row[c] = cross1[r+c] = cross2[r-c+N] = True
            recur(r+1)
            row[c] = cross1[r+c] = cross2[r-c+N] = False

recur(0)
print(count)