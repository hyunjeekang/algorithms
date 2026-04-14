import sys
input = sys.stdin.readline

# 이중 연결 리스트
class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None

def solve():
    # 맨 앞 노드 생성 (prev=None)
    head = Node("head")
    cursor = head  

    # 초기문자열 노드 생성 및 연결
        # 생성
    init_str = input().strip()
    for char in init_str:
        new_node = Node(char)
        
        # 연결 (수행되기 전에 커서는 문장의 맨 뒤에 위치)
        new_node.prev = cursor
        cursor.next = new_node
        
        cursor = new_node

    m = int(input())
    
    for _ in range(m):
        cmd = input().split()
        
        # 현재 커서 노드가 None(Head)가 아니면 왼쪽으로 이동
        if cmd[0] == 'L':
            if cursor.prev is not None:
                cursor = cursor.prev
        
        # 오른쪽 노드가 있다면 (끝이 아니면) 오른쪽으로 이동
        elif cmd[0] == 'D':
            if cursor.next is not None:
                cursor = cursor.next
        
        # 현재 커서 왼쪽 노드 삭제(head 제외)
        elif cmd[0] == 'B':
            if cursor.prev is not None:
                del_node = cursor
                prev_node = del_node.prev
                next_node = del_node.next
                
                # 연결
                prev_node.next = next_node
                if next_node:
                    next_node.prev = prev_node
                
                cursor = prev_node
        
        # 문자 삽입
        elif cmd[0] == 'P':
            char = cmd[1]
            new_node = Node(char)
            
            next_node = cursor.next
            
            cursor.next = new_node
            new_node.prev = cursor
            
            if next_node:
                new_node.next = next_node
                next_node.prev = new_node
                
            cursor = new_node

    curr = head.next
    while curr:
        print(curr.data, end='')
        curr = curr.next

solve()