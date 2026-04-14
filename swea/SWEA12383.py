tc = int(input())

def solve(length, heights):
    max_gap = 0
    for i in range(length):
        height = heights[i]
        if height:
            gap = 0
            for j in range(i+1, length):
                target = heights[j]
                if height > target:
                    gap += 1
            max_gap = max_gap if max_gap >= gap else gap

    return max_gap

for i in range(1, tc+1):
    n = int(input())
    heights = list(map(int, input().split()))
    print(f"#{i} {solve(n, heights)}")

                