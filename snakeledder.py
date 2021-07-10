from collections import deque
from typing import List


# # Definition for a Node.
# class Node:
#     def __init__(self, val = 0, neighbors = None):
#         self.val = val
#         self.neighbors = neighbors if neighbors is not None else []


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        if board is None or len(board) == 0: return -1

        r = len(board)

        darray = [-1] * (r * r)
        j = 0
        direction = 0
        idx = 0
        row = r - 1

        while row >= 0 and j >= 0:

            if board[row][j] == -1:
                darray[idx] = -1
            else:
                darray[idx] = board[row][j] - 1
            idx += 1

            if direction % 2 == 0:
                j += 1

                if j > len(board) - 1:
                    j = len(board) - 1
                    row -= 1

                    direction += 1
            else:
                j -= 1
                if j < 0:
                    j = 0
                    row -= 1

                    direction += 1
        queue = deque()
        queue.append(0)
        darray[0] = -2
        minmoves = 0
        lastindex = len(board) * len(board[0]) - 1

        while queue.__len__() > 0:
            size = queue.__len__()
            for index in range(size):
                curr = queue.popleft()
                if curr == lastindex:  return minmoves
                for newrole in range(1, 7):
                    child = curr + newrole
                    if child > lastindex: break
                    if darray[child] != -2:
                        if darray[child] != -1:
                            queue.append(darray[child])
                        else:
                            queue.append(child)
                    darray[child] = -2

            minmoves += 1

        return -1
