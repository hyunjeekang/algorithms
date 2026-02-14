import sys
input = sys.stdin.readline

def combination(start, depth):
    global comb, result, nums
    if depth == 6:
        result.append(comb[:])
        return
    
    for i in range(start, len(nums)):
        comb.append(nums[i])
        combination(i+1, depth+1)
        comb.pop()
    

while True:
    inputs = list(map(int, input().split()))
    k = inputs[0]
    if k == 0: break
    nums = inputs[1:]
    comb = []
    result = []
    combination(0, 0)
    result.sort()
    for row in result:
        print(*row)
    print()
    