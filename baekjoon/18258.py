import sys
input = sys.stdin.readline
N = int(input())

# queue 직접 구현
MAX_LENGTH = 2000000

class Queue:
    def __init__(self):
        self.q = [None]*MAX_LENGTH
        self.start_idx = 0
        self.end_idx = -1
    
    def size(self):
        print(self.end_idx - self.start_idx + 1)
        return
    
    def push(self, num):
        self.end_idx += 1
        self.q[self.end_idx] = num
        return 
    
    def pop(self):
        if self.empty():
            print(-1)
            return
        print(self.q[self.start_idx])
        self.start_idx+=1
        return
        
    def empty(self):
        if self.start_idx > self.end_idx:
            return 1
        return 0
    
    def front(self):
        if self.empty():
            print(-1)
            return
        print(self.q[self.start_idx])

    def back(self):
        if self.empty():
            print(-1)
            return
        print(self.q[self.end_idx])
    
queue = Queue()
for _ in range(N):
    command_list = input().split()
    command = command_list[0]
    if command == 'push':
        queue.push(int(command_list[-1]))
    elif command == 'pop':
        queue.pop()
    elif command == 'size':
        queue.size()
    elif command == 'empty':
        print(queue.empty())
    elif command == 'front':
        queue.front()
    elif command == 'back':
        queue.back()

# deque 사용
# from collections import deque

# queue = deque([])
# for _ in range(N):
#     command_list = input().split()
#     command = command_list[0]
#     if command == 'push':
#         queue.append(int(command_list[-1]))
#     elif command == 'pop':
#         print(queue.popleft()) if queue else print(-1)
#     elif command == 'size':
#         print(len(queue))
#     elif command == 'empty':
#         print(int(not queue))
#     elif command == 'front':
#         print(queue[0]) if queue else print(-1)
#     elif command == 'back':
#         print(queue[-1]) if queue else print(-1)