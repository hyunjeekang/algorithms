import sys 
input = sys.stdin.readline

number_of_switches = int(input())
switches = [None] + list(map(int, input().split()))
number_of_students = int(input())

for _ in range(number_of_students):
    sex, num = map(int, input().split())

    if sex == 1:
        for i in range(num, number_of_switches + 1, num):
            switches[i] = 1 - switches[i]
        
    else:
        switches[num] = 1 - switches[num]
        
        k = 1
        while (num - k >= 1 and num + k <= number_of_switches and 
               switches[num - k] == switches[num + k]):
            switches[num - k] = 1 - switches[num - k]
            switches[num + k] = 1 - switches[num + k]
            k += 1

ans = switches[1:]
for i in range(0, len(ans), 20):
    print(*ans[i:i+20])