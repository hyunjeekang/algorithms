import sys
from collections import deque
input = sys.stdin.readline
n = int(input())

q = deque([])
print_arr = []

for _ in range(n):
    command = input().strip()
    
    ipt = command.split()
    cmd = ipt[0]

    if cmd == 'push':
        q.append(ipt[-1])

    elif cmd == 'pop':
        if not q:
            print_arr.append(-1)
            continue
        print_arr.append(q.popleft())
    
    elif cmd == 'size':
        print_arr.append(len(q))

    elif cmd == 'empty':
        print_arr.append(1) if not q else print_arr.append(0)

    elif cmd == 'front':
        if not q:
            print_arr.append(-1)
            continue    
        print_arr.append(q[0])

    elif cmd == 'back':
        if not q: 
            print_arr.append(-1)
            continue
        print_arr.append(q[-1])
    
print('\n'.join(map(str,print_arr)))
