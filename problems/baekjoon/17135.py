N, M, D = map(int, input().split())
enemies = []

for r in range(N):
    row_data = list(map(int, input().split()))
    for c in range(M):
        if row_data[c] == 1:
            enemies.append((r, c))

def get_distance(r1, c1, r2, c2):
    return abs(r1 - r2) + abs(c1 - c2)

def castle_defence(archers, enemies):
    
    cur_enemies = enemies[:]
    total_removed = 0
    
    while cur_enemies:
            targets = set()
            for ar, ac in archers:
                best_enemy = None
                min_dist = D + 1
                
                for er, ec in cur_enemies:
                    dist = get_distance(ar, ac, er, ec)
                    
                    if dist <= D:
                        if dist < min_dist:
                            min_dist = dist
                            best_enemy = (er, ec)
                        elif dist == min_dist:
                            if ec < best_enemy[1]:
                                best_enemy = (er, ec)
                
                if best_enemy:
                    targets.add(best_enemy)
            
            for target in targets:
                cur_enemies.remove(target)
                total_removed += 1
            
            next_enemies = []
            for er, ec in cur_enemies:
                if er + 1 < N:
                    next_enemies.append((er + 1, ec))
            cur_enemies = next_enemies
        
    return total_removed

max_removed = 0
archers = []
def main(start, count):
    global max_removed
    if count == 3:
        cur_removed = castle_defence(archers, enemies)
        max_removed = cur_removed if cur_removed > max_removed else max_removed
        return
    for c in range(start, M):
        archers.append((N, c))
        main(start+1, count+1)
        archers.pop()

main(0, 0)
print(max_removed)