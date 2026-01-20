classset = set(range(1, 31))
iptset = set([int(input()) for _ in range(28)])
resultset = sorted(list(classset - iptset))
for num in resultset:
    print(num)