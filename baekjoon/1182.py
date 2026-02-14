import sys
input = sys.stdin.readline
N, S = map(int, input().split())
sequence = list(map(int, input().split()))

cnt = 0
def recur(idx, sum):
    global cnt
    if idx == N:
        if sum == S:
            cnt += 1
        return
    
    recur(idx+1, sum + sequence[idx])
    recur(idx+1, sum)

recur(0, 0)
if S == 0: cnt -= 1
print(cnt)