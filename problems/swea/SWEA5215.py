def solve(N, L, ingredients):
    max_burger_score = 0

    comb = []
    def combination(start, depth, r):
        nonlocal max_burger_score

        if depth == r:
            cur_burger_calorie = sum(ing[1] for ing in comb)
            if cur_burger_calorie <= L:
                cur_burger_score = sum(ing[0] for ing in comb)
                max_burger_score = cur_burger_score if cur_burger_score > max_burger_score else max_burger_score
            return
        
        for i in range(start, N):
            comb.append(ingredients[i])
            combination(i+1, depth+1, r)
            comb.pop()
        
    for r in range(1, N+1):
        combination(0, 0, r)

    return max_burger_score

T = int(input())
for t in range(1, T+1):
    N, L = map(int, input().split())    # 재료 수, 제한 칼로리
    ingredients = []
    for _ in range(N):
        T, K = map(int, input().split())
        ingredients.append((T, K)) # 점수, 칼로리
    result = solve(N, L, ingredients)
    print(f"#{t} {result}")