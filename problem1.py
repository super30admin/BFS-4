#Time Complexity :O(m*n)
#Space Complexity :O(m*n)
#Did this code successfully run on Leetcode :Yes
#Any problem you faced while coding this :No

from ast import List


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        def dfs(board, i, j):
            if i < 0 or i >= len(board) or j < 0 or j >= len(board[0]) or board[i][j] != 'E':
                return
            mine_count = 0
            for x in range(-1, 2):
                for y in range(-1, 2):
                    if x == 0 and y == 0:
                        continue
                    ni, nj = i + x, j + y
                    if 0 <= ni < len(board) and 0 <= nj < len(board[0]) and board[ni][nj] == 'M':
                        mine_count += 1
            if mine_count > 0:
                board[i][j] = str(mine_count)
            else:
                board[i][j] = 'B'
                for x in range(-1, 2):
                    for y in range(-1, 2):
                        dfs(board, i + x, j + y)

        i, j = click
        if board[i][j] == 'M':
            board[i][j] = 'X'
        else:
            dfs(board, i, j)
        return board
        
        