def solve():
    n = int(input())
    buildings = list(map(int, input().split()))
    check = [1, -1, 2, -2]
    ans = 0
    for j in range(2, n-2):
        cur_building = buildings[j]
        sub = 255
        view = True
        for k in range(4):
            check_idx = j + check[k]
            if 0 <= check_idx < n:
                check_building = buildings[check_idx]
                if view:
                    if cur_building > check_building:
                        sub = min(sub, cur_building - check_building)
                    else:
                        sub = 0
                        view = False
        ans += sub

    return ans

for i in range(1, 11):
    result = solve()
    print(f"#{i} {result}")
