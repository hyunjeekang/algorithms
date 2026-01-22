import sys
input = sys.stdin.readline
A, B = map(list, input().split())
grid = [['.']*len(A) for _ in range(len(B))]

#check common alphabet
idx_a = idx_b = 0
has_common = False
for i, a in enumerate(A):
    for j, b in enumerate(B):
        if a == b:
            idx_a, idx_b = i, j
            has_common = True
            break
    if has_common : break

grid[idx_b] = A
for k, b in enumerate(B):
    grid[k][idx_a] = b

for row in grid:
    print(''.join(map(str, row)))