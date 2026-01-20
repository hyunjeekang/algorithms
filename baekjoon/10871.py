import sys
input = sys.stdin.readline
N, X = map(int, input().split())
A = list(map(int, input().split()))
for n in A:
    if n < X: print(n, end=' ' )