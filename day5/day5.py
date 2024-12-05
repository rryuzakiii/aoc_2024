from itertools import combinations
data = [_.strip() for _ in open("input.txt", "r").readlines()]
rules, updates = [[int(k) for k in _.split("|")] for _ in data[0:data.index('')]], [[int(k) for k in _.split(",")] for _ in data[data.index('') + 1:]]
s = 0
for update in updates:
    if all(list(_) in rules for _ in combinations(update, 2)):
        s += update[len(update) // 2]
print(s)