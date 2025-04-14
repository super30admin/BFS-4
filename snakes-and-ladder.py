# Time O(m*n)
# Space O(m*n)
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board[0])
        board_arr = []
        flag = False
        for i in range(n-1, -1, -1):
            if not flag:
                board_arr.extend(board[i])
            else: board_arr.extend(board[i][::-1])
            flag = not flag
        #print(board_arr)
        dq = deque()
        dq.append(1)
        visited = {1}
        minDice = 0
        n_sq = n * n
        while len(dq) > 0:
            length = len(dq)
            #print(dq)
            for i in range(length):
                curr = dq.popleft()
                if curr == n_sq:
                    return minDice
                for dice in range(curr + 1, min(curr+7, n_sq+1)):
                    if dice not in visited:
                        if board_arr[dice - 1] == -1:
                            #visited.add(dice)
                            dq.append(dice)
                        #elif board_arr[dice - 1] not in visited:
                        else:
                            #visited.add(board_arr[dice - 1])
                            dq.append(board_arr[dice - 1])
                        visited.add(dice)
            minDice += 1
            
        return -1
