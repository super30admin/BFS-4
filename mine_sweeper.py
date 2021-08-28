# Time Complexity: O(m*n)
# Space Complexity: O(1)
from typing import List


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        self.m = len(board)
        self.n = len(board[0])

        self.direc = [(0, -1), (0, 1), (1, 0), (-1, 0), (-1, -1), (-1, 1), (1, -1), (1, 1)]

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        if board[click[0]][click[1]] == 'E':
            return self.dfs(click[0], click[1], board)

    def dfs(self, r, c, board):

        curr_mines = self.get_adj_mines(r, c, board)

        if curr_mines:
            board[r][c] = str(curr_mines)
            return board

        board[r][c] = 'B'

        for d in self.direc:
            x = d[0] + r
            y = d[1] + c

            if 0 <= x < self.m and 0 <= y < self.n and board[x][y] == 'E':
                self.dfs(x, y, board)

        return board

    def get_adj_mines(self, r, c, board):

        mines = 0

        for d in self.direc:
            x = d[0] + r
            y = d[1] + c

            if 0 <= x < self.m and 0 <= y < self.n and board[x][y] == 'M':
                mines += 1

        return mines


