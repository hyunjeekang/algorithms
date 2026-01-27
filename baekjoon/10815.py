import sys
input = sys.stdin.readline
n = int(input())
ns = set(map(int, input().split()))

m = int(input())
ms = list(map(int, input().split()))

for mm in ms:
    print(1, end=' ') if mm in ns else print(0, end=' ')