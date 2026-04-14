import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
N = int(input())

# int 풀이
def cycle(num, count):
    if count and num == N:
        return count

    new_num = (num%10) * 10 + (num // 10 + num%10)%10
    return cycle(new_num, count+1)

# str 풀이
# N = input().strip()
# def cycle(str_num, cycle_count):
#     if cycle_count and int(str_num) == int(N):
#         return cycle_count
    
#     temp_num = str_num
#     if 10 > int(str_num):
#         temp_num = '0' + temp_num
    
#     digit_sum = sum(map(int, temp_num))
#     next_str = temp_num[-1] + str(digit_sum)[-1]
#     return cycle(str(int(next_str)), cycle_count+1)

print(cycle(N, 0))
