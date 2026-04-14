import sys

while True:
    line = sys.stdin.readline()
    if not line:
        break
    upper = lower = digit = blank = 0

    for char in line:
        if char.isupper():
            upper += 1
        elif char.islower():
            lower += 1
        elif char.isdigit():
            digit += 1
        elif char == ' ':
            blank += 1
    
    print(lower, upper, digit, blank)