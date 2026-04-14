import sys
input_data = sys.stdin.read().split()
grid = [int(x) for x in input_data[1:]]
grid.sort()
print('\n'.join(map(str, grid)))