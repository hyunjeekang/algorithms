import sys
input = sys.stdin.readline
H, M = map(int, input().split())
tot = H * 60 + M - 45
print(tot // 60 % 24, tot % 60)


# if M < 45:
#     M += 15
#     if H == 0:
#         H = 23
#     else:
#         H -= 1
# else:
#     M -= 45

# print(H, M)