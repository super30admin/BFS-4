# Time Complexity : O(M*N) where M,N are dimension of the board
# Space Complexity : O(M*N) where M,N are dimension of the board
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        
        ROWS, COLS = len(board), len(board[0])
        if ROWS == 0:
            return board
        q = deque()
        dirs = [[0,1], [1,0], [0,-1], [-1,0], [1,1], [-1,1], [1,-1], [-1,-1]]
        board[click[0]][click[1]] = "B"
        q.appendleft(click)
        mines = 0
        while len(q) > 0:
            r, c = q.pop()
            mines = self.countMinesAround(r,c, board, ROWS, COLS)
            if mines > 0:
                board[r][c] = str(mines)
            else:
                for d in dirs:
                    nr = r + d[0]
                    nc = c + d[1]
                    if nc >= 0 and nr >= 0 and nr < ROWS and nc < COLS and board[nr][nc] == "E":
                        board[nr][nc] = 'B'
                        q.appendleft([nr,nc])
                        
        return board
                        
        
    def countMinesAround(self, r,c, board, ROWS, COLS):
        dirs = [[0,1], [1,0], [0,-1], [-1,0], [1,1], [-1,1], [1,-1], [-1,-1]]
        count = 0

        for d in dirs:
            nr = r + d[0]
            nc = c + d[1]
            if nc >= 0 and nr >= 0 and nr < ROWS and nc < COLS and board[nr][nc] == "M":
                count += 1

        return count