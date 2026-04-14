import sys
input = sys.stdin.readline
M = int(input())
states = 0

for _ in range(M):
    line = input().split()
    command = line[0]
    
    if command == 'all':
        states = (1 << 21) - 1
    elif command == 'empty':
        states = 0
    else:
        x = int(line[1])
        if command == 'add':
            states |= (1 << x)
        elif command == 'remove':
            states &= ~(1 << x)
        elif command == 'check':
            print(1 if states & (1 << x) else 0)
        elif command == 'toggle':
            states ^= (1 << x)
