lamps = ['N']+list(input())
len_lamps = len(lamps)
switch = {'Y':'N', 'N':'Y'}
cnt = 0

for i in range(1, len_lamps):
    lamp = lamps[i]
    if lamp == 'Y':
        for j in range(i, len_lamps, i):
            lamps[j] = switch[lamps[j]]
        cnt += 1

if 'Y' in lamps:
    print(-1)
else:
    print(cnt)