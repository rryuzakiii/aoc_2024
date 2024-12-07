with open('input.txt', 'r') as file:
    map_data = file.read().strip().splitlines()
row = column = None
for r in range(len(map_data)):
    for c in range(len(map_data[r])):
        if map_data[r][c] == '^':
            row, column= r, c
            break
    if row is not None:
        break
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
current_direction = 0
r, c = row, column 
Visited = set()
Visited.add((r, c))
while True:
    dr, dc = directions[current_direction]
    rr, cc = r + dr, c + dc
    if not (0 <= rr < len(map_data) and 0 <= cc < len(map_data[0])):
        break
    if map_data[rr][cc] == '#':
        current_direction = (current_direction + 1) % 4
    else:
        r, c = rr, cc
        Visited.add((r, c))

print( len(Visited))
