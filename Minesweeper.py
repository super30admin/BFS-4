# Time Complexity : O(mn), where m is the number of rows and n is the number of columns in the board
# Space Complexity : O(mn)
from typing import List


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        row, col = click
        if board[row][col] == 'M':
            board[row][col] = 'X'
            return board
        
        m, n = len(board), len(board[0])
        visited = set()
        
        def dfs(row, col):
            if (row, col) in visited:
                return
            visited.add((row, col))
            mines = 0
            for i, j in ((-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)):
                new_row, new_col = row+i, col+j
                if 0 <= new_row < m and 0 <= new_col < n and board[new_row][new_col] == 'M':
                    mines += 1
            if mines > 0:
                board[row][col] = str(mines)
            else:
                board[row][col] = 'B'
                for i, j in ((-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)):
                    new_row, new_col = row+i, col+j
                    if 0 <= new_row < m and 0 <= new_col < n and board[new_row][new_col] == 'E':
                        dfs(new_row, new_col)
        
        dfs(row, col)
        return board