MAX_LENGTH = 10001

class Stack:
    def __init__(self):
        self.idx = -1
        self.stack = [None]*MAX_LENGTH

    def push(self, data):
        self.idx += 1
        self.stack[self.idx] = data

    def empty(self):
        return 1 if self.idx == -1 else 0

    def pop(self):
        if self.empty():
            return -1
        popped = self.stack[self.idx]
        self.idx -= 1
        return popped

    def size(self):
        return self.idx + 1

    def top(self):
        if self.empty():
            return -1
        return self.stack[self.idx]
    
n = int(input())
inputs = [input().strip() for _ in range(n)]
stack = Stack()
printarr = []
for ipt in inputs:
    command = ipt.split()
    cmd = command[0]

    if cmd == 'push':
        data = int(command[1])
        stack.push(data)

    elif cmd == 'pop':
        printarr.append(stack.pop())

    elif cmd == 'size':
        printarr.append(stack.size())

    elif cmd == 'empty':
        printarr.append(stack.empty())

    elif cmd == 'top':
        printarr.append(stack.top())

print('\n'.join(map(str,printarr)))