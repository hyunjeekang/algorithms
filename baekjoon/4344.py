import sys
input = sys.stdin.readline
C = int(input().strip())
for _ in range(C):
    arr = list(map(int, input().split()))
    n = arr[0]
    avg = cnt = 0
    for i in range(1, n+1):avg += arr[i]
    avg = float(avg)/n
    for i in range(1, n+1):
        if float(arr[i]) > avg:
            cnt += 1
    result = cnt/float(n)*100
    print(f"{result:.3f}%")
