import sys
input = sys.stdin.readline
N = int(input())
inning_results = [list(map(int, input().split()))for _ in range(N)]
score_table = [0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4]

def game_simulation(hitters):
    score = 0
    next_hitter = 0
    for inning in range(N):
        next_hitter, inning_score = inning_simulation(next_hitter, hitters, inning)
        score += inning_score
    return score

def inning_simulation(start_hitter, hitters, inning):
    out_count = 0
    score  = 0
    hitter_idx = start_hitter
    # b1 = b2 = b3 = 0
    base = 0

    while out_count < 3:
        cur_hitter = hitters[hitter_idx]
        hit = inning_results[inning][cur_hitter]

        if hit == 0:
            out_count += 1
        else:
            # 1. 현재 베이스에 타자(1)를 추가하고 루타수만큼 밀기
            new_base = (base << 1 | 1) << (hit - 1)
            
            # 2. 3루를 벗어난 주자들(3비트 이후)을 점수에 더함
            score += score_table[new_base >> 3]
            
            # 3. 베이스에 1~3루 주자만 남김 (3비트만 남기기)
            base = new_base & 7 # 7 == 111
        
        # match hit:
        #     case 0 : out_count += 1
        #     case 1 : 
        #         score += b3
        #         b1, b2, b3 = 1, b1, b2
        #     case 2:
        #         score += (b2 + b3)
        #         b1, b2, b3 = 0, 1, b1
        #     case 3:
        #         score += (b1 + b2 + b3)
        #         b1, b2, b3 = 0, 0, 1
        #     case 4:
        #         score += (b1 + b2 + b3 + 1)
        #         b1, b2, b3 = 0, 0, 0

        hitter_idx = (hitter_idx + 1) % 9
    return hitter_idx, score

perm = []
max_score = 0
selected = [False]*9
def permutation(depth):
    global perm, selected, max_score
    
    if depth == 8:
        hitters = perm[:3] + [0] + perm[3:]
        score = game_simulation(hitters)
        max_score = max(score, max_score)
        return
    
    for i in range(1, 9):
        if not selected[i]:
            selected[i] = True
            perm.append(i)
            permutation(depth+1)
            perm.pop()
            selected[i] = False

permutation(0)
print(max_score)