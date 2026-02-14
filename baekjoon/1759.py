import sys
input = sys.stdin.readline
L, C = map(int, input().split())
alphabets = list(input().split())
alphabets.sort()

result = []
code = []
def make_code(idx):
    global code, result
    if idx == C:
        if len(code) == L:
            vows = cons = 0
            for c in code:
                if c in "aeiou":
                    vows += 1
                else: cons += 1
            
            if vows >= 1 and cons >= 2:
                code.sort()
                result.append(''.join(code))
        return

    code.append(alphabets[idx])
    make_code(idx+1)
    code.pop()
    
    make_code(idx+1)


make_code(0)
result.sort()
for r in result:
    print(r)