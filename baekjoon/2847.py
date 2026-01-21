import sys
input = sys.stdin.readline
N = int(input().strip())
points = [int(input().strip()) for _ in range(N)]
subpoints = 0
for i in range(N-1, 0, -1):
    while points[i] <= points[i-1]:
        points[i-1] -= 1
        subpoints += 1
print(subpoints)