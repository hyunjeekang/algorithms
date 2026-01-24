import sys
input = sys.stdin.readline

N, K = map(int, input().split())
grades = []

target_score = []
for _ in range(N):
    data = list(map(int, input().split()))
    grades.append(data)
    if data[0] == K:
        target_score = data[1:]

rank = 1
for i in range(N):
    if grades[i][0] == K:
        continue
    
    if grades[i][1] > target_score[0]:
        rank += 1
        
    elif grades[i][1] == target_score[0] and grades[i][2] > target_score[1]:
        rank += 1
        
    elif grades[i][1] == target_score[0] and grades[i][2] == target_score[1] and grades[i][3] > target_score[2]:
        rank += 1

print(rank)