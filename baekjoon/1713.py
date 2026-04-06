import sys
input = sys.stdin.readline

N = int(input())
R = int(input())
recommends = list(map(int, input().split()))

frame = []

for i in range(R):
    student = recommends[i]

    exists = False
    for f in frame:
        if f[0] == student:
            f[1] += 1
            exists = True
            break
    
    if not exists:
        if len(frame) >= N:
            target_idx = 0
            min_val = frame[0][1]

            for j in range(1, len(frame)):
                if frame[j][1] < min_val:
                    min_val = frame[j][1]
                    target_idx = j

            frame.pop(target_idx)
        frame.append([student, 1])

result = sorted([f[0] for f in frame])
print(*result)