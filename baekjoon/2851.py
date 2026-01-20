import sys
input = sys.stdin.readline
mushrooms = [int(input().strip())for _ in range(10)]
point = 0
for m in mushrooms:
    prepoint = point
    point += m

    if point >= 100:
        gapov = point - 100
        gapud = 100 - prepoint

        if gapov <= gapud:
            print(point)
        else:
            print(prepoint)
        break

else:
    print(point)