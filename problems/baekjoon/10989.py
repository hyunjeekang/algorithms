import sys

count_array = [0] * 10001
n = int(sys.stdin.readline())

for _ in range(n):
    num = int(sys.stdin.readline())
    count_array[num] += 1

for i in range(10001):
    if count_array[i] != 0:
        for _ in range(count_array[i]):
            print(i)