import sys
input = sys.stdin.readline
n = int(input())
nset = set(map(int, input().split()))
m = int(input())
mlist = list(map(int, input().split()))

for mm in mlist:
    print(1) if mm in nset else print(0)