import sys
input = sys.stdin.readline
N = int(input().strip())
road = list(map(int, input().split()))
uphill = False
uphills = [0]*N #uphill sizes
idx = 0
for i in range(0, N-1):
    if road[i] < road[i+1]:
        if not uphill:
            uphill = True
        uphills[idx] += road[i+1] - road[i]
    else:
        if uphill:
            uphill = False
            idx += 1
uphills.sort(reverse=True)
print(uphills[0])