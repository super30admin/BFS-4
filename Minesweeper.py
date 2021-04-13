# Time Complexity : O(MN)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using BFS Approach

from collections import deque


class Solution:
    def updateBoard(self, board: List[List[str]],
                    click: List[int]) -> List[List[str]]:
        if not board:
            return None
        i, j = click[0], click[1]
        if board[i][j] == "M":
            board[i][j] = "X"
            return board
        queue = deque([])
        neighbors = [(0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (1, -1),
                     (-1, 1), (-1, -1)]

        queue.append((i, j))
        board[i][j] = 'B'
        while queue:
            x, y = queue.popleft()
            mines = self.getMines(board, x, y)
            if mines == 0:
                for neighbor in neighbors:
                    row = x + neighbor[0]
                    col = y + neighbor[1]
                    if row >= 0 and row < len(
                            board) and col >= 0 and col < len(
                                board[0]) and board[row][col] == "E":
                        queue.append((row, col))
                        board[row][col] = "B"
            else:
                board[x][y] = str(mines)
        return board

    def getMines(self, board, i, j):
        res = 0
        neighbors = [(0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (1, -1),
                     (-1, 1), (-1, -1)]

        for neighbor in neighbors:
            row = i + neighbor[0]
            col = j + neighbor[1]
            if row >= 0 and row < len(board) and col >= 0 and col < len(
                    board[0]) and board[row][col] == "M":
                res += 1
        return res
