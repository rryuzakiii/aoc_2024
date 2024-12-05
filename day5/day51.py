
from itertools import combinations
data = [_.strip() for _ in open("input.txt", "r").readlines()]
rules, updates = [[int(k) for k in _.split("|")] for _ in data[0:data.index('')]], [[int(k) for k in _.split(",")] for _ in data[data.index('') + 1:]]
correct = []
for update in updates:
    if all(list(_) in rules for _ in combinations(update, 2)):
        correct.append(update)
wrong = [_ for _ in updates if _ not in correct]
s2 = 0
for update in wrong:
    while not all(list(_) in rules for _ in combinations(update, 2)):
        for i in range(len(update) - 1):
            if [update[i], update[i + 1]] not in rules:
                update[i], update[i + 1] = update[i + 1], update[i]
    s2 += update[len(update) // 2]
print(s2)