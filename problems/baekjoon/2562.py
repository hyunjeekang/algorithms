nums = [[int(input().strip()),i+1]for i in range(9)]
nums.sort()
print(nums[-1][0])
print(nums[-1][1])
