import sys

input = sys.stdin.readline
n = int(input())
budgets = list(map(int, input().split()))
total_budget = int(input())

max_limit = max(budgets)
checked_b, checked_f = 0, max(budgets)

def search(limit):
    global checked_b, checked_f

    if checked_f <= checked_b:
        return checked_f

    cur_total_budget = 0
    
    for budget in budgets:
        if budget < limit:
            cur_total_budget += budget
        else:
            cur_total_budget += limit

    if total_budget >= cur_total_budget:
        checked_b = limit + 1
        next_limit = (checked_b + checked_f) // 2
        return search(next_limit)

    else:
        checked_f = limit - 1
        next_limit = (checked_b + checked_f) // 2
        return search(next_limit)

    
if total_budget >= sum(budgets):
    print(max_limit)

else:
    print(search(total_budget//n))
