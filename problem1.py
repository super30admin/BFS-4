# problem 1 
# https://leetcode.com/problems/minesweeper/

class Solution:
    def updateBoard(self, board: list[list[str]], click: list[int]) -> list[list[str]]:
        dirs = [(-1,0), (-1,-1), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)]
        m, n = len(board), len(board[0])

        def countMines(i, j):
            count = 0 
            for r,c in dirs:
                if 0 <= i+r < m and 0<= j+c < n and board[i+r][j+c] == 'M':
                    count+=1
            return count

        def dfs(i, j):
            if i < 0 or j < 0 or i >= m or j >= n or board[i][j] != 'E':
                return
            count = countMines(i, j)
            if count == 0:
                board[i][j] = 'B'
                for r, c in dirs:
                    dfs(i + r, j + c)
            else:
                board[i][j] = str(count)

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
        else:
            dfs(click[0], click[1])

        return board
