# Time Complexity : O(m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#

# BFS.
from collections import deque


class Solution:
    def updateBoard(self, board: list[list[str]], click: list[int]) -> list[list[str]]:
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        m = len(board)
        n = len(board[-1])
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, 1], [-1, -1], [1, -1], [1, 1]]
        queue = deque()
        queue.append(click)
        while queue:
            count = 0
            pop = queue.pop()
            for x in dirs:
                i = x[0] + pop[0]
                j = x[1] + pop[1]
                if 0 <= i < m and 0 <= j < n and board[i][j] == 'M':
                    count += 1
            if count > 0:
                board[pop[0]][pop[1]] = str(count)
            else:
                board[click[0]][click[1]] = 'B'
                for x in dirs:
                    i = x[0] + pop[0]
                    j = x[1] + pop[1]
                    if 0 <= i < m and 0 <= j < n and board[i][j] == 'E':
                        board[i][j] = 'B'
                        queue.append([i, j])
        return board


print(Solution().updateBoard(
    [["E", "E", "E", "E", "E"], ["E", "E", "M", "E", "E"], ["E", "E", "E", "E", "E"], ["E", "E", "E", "E", "E"]],
    [3, 0]))