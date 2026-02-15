import sys
input = sys.stdin.readline

targets = []
n = int(input().strip())
for _ in range(n):targets.append(int(input().strip()))
maxnum = max(targets)

operator = []
stack = []
curnum = 1
possible = True

for target in targets:
    while curnum <= target:
        stack.append(curnum)
        operator.append('+')
        curnum += 1
    
    if stack[-1] == target:
        stack.pop()
        operator.append('-')
    else:
        print("NO")
        possible=False
        break

if possible: print('\n'.join(operator)) 