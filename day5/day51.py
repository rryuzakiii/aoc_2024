from collections import defaultdict, deque

def parse(input):
    rules, updates = input.split("\n\n")
    rule_list = [tuple(map(int, line.split('|'))) for line in rules.splitlines()]
    update_list = [list(map(int, line.split(','))) for line in updates.splitlines()]
    return rule_list, update_list

def build_rules(rule_list):
    precedence = defaultdict(list)
    for before, after in rule_list:
        precedence[after].append(before)
    return precedence

def is_valid_update(update, pre):
    index_map = {page: idx for idx, page in enumerate(update)}
    for after, befores in pre.items():
        if after in index_map:
            after_idx = index_map[after]
            for before in befores:
                if before in index_map and index_map[before] > after_idx:
                    return False
    return True

def topological_sort(update, pre):
    graph = defaultdict(set)
    in_degree = defaultdict(int)
    for page in update:
        graph[page] = set()
        in_degree[page] = 0
    for after, befores in pre.items():
        if after in update:
            for before in befores:
                if before in update:
                    graph[before].add(after)
                    in_degree[after] += 1
    queue = deque([node for node in update if in_degree[node] == 0])
    sorted_list = []
    while queue:
        node = queue.popleft()
        sorted_list.append(node)
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    return sorted_list

def find(update):
    n = len(update)
    return update[n // 2]

def solve(file_path):
    with open(file_path, 'r') as file:
        input = file.read()
    rule_list, update_list = parse(input)
    pre = build_rules(rule_list)
    total_middle_sum = 0

    for update in update_list:
        if not is_valid_update(update, pre):
            corrected_update = topological_sort(update, pre)
            total_middle_sum += find(corrected_update)
    
    return total_middle_sum

file_path = "input.txt"  
result = solve(file_path)
print(result)
