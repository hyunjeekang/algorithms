N = int(input())
grid = [list(map(int, input().split())) for _ in range(N)]

team = [False]*(N)
min_diff =float('inf')

def calculate_diff():
    team_a = team_b = 0
    for i in range(N):
        for j in range(N):
            if team[i] and team[j]:
                team_a += grid[i][j]
            elif not team[i] and not team[j]:
                team_b += grid[i][j]
    return abs(team_a-team_b)

def combination(start, count):
    global min_diff

    if count == N//2:
        min_diff = min(min_diff, calculate_diff())
        return
    
    for i in range(start, N):
        team[i] = True
        combination(i+1, count+1)
        team[i] = False

combination(1, 0)
print(min_diff)