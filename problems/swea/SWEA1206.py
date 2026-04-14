def solve():
    line = input().split()
    n = int(line[0])
    buildings = list(map(int, input().split()))

    ans = 0
    for i in range(2, n - 2):
        max_neighbor = max(buildings[i-2], buildings[i-1], buildings[i+1], buildings[i+2])
        
        if buildings[i] > max_neighbor:
            ans += buildings[i] - max_neighbor

    return ans

for i in range(1, 11):
    result = solve()
    if result is not None:
        print(f"#{i} {result}")