import sys
input = sys.stdin.readline
grid = list(map(int,input().split()))
ascending = [n for n in range(1, 9)]
descending = [n for n in range(8,0,-1)]

if grid == ascending:
    print('ascending')
elif grid == descending:
    print('descending')
else:
    print('mixed')