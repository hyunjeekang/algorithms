a, b, c = [int(input().strip()) for _ in range(3)]
tar = str(a*b*c)
for i in range(10):print(tar.count(str(i)))