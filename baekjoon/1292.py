sequence = []
length = i = 0
while length < 1000:
    for _ in range(i): sequence.append(i)
    length += i
    i += 1

A, B = map(int, input().split())
print(sum(sequence[A-1:B]))