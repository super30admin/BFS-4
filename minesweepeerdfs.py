# Time Complexity : O(M*N)
# Space Complexity : O(M*N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach
from collections import deque
class Solution:
    def __init__(self):
        self.dx = [-1,-1,-1,0,1,1,1,0]
        self.dy = [-1,0,1,1,1,0,-1,-1]
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
    
        row = click[0]
        col = click[1]
        
        if board[row][col] == 'M':
            board[row][col] = 'X'
            return board
        else:
            self.dfsVisit(board, row, col)
            
        return board
    
    
    def dfsVisit(self, board, row, col):
        countAdjacentMines = self.countMines(board, row, col)
        if countAdjacentMines == 0:
            board[row][col] = 'B'
            for i in range(8):
                x = row+self.dx[i]
                y = col+self.dy[i]
                if self.isValid(board,x,y) and board[x][y]!='B':
                    self.dfsVisit(board, x, y)
        else:
            board[row][col] = str(countAdjacentMines)
            
            
    def countMines(self, board, i, j):
        count = 0
        for k in range(8):
            x = i+self.dx[k]
            y = j+self.dy[k]
            if self.isValid(board,x,y) and board[x][y] == 'M':
                count +=1
        return count
    
    def isValid(self, board, i, j):
        return i<len(board) and j<len(board[0]) and i>=0 and j>=0
        
        
        
        
        
        