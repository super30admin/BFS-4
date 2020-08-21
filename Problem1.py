"""
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
"""

from collections import deque
class Solution:
    def count_mines(self, board, r,c): #function to find the number of mines in the vicinity of the cell
        ct = 0
        
        for d in self.dirs:
            row = r + d[0]
            col = c + d[1] 
                    
            if row >= 0 and row < len(board) and col >= 0 and col < len(board[0]) and board[row][col] == "M":
                ct += 1
                
        return ct
            
            
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        self.dirs = [[1,0], [0,1], [-1,0], [0,-1], [1,1], [-1,1], [1,-1], [-1,-1]]
        
        
        if board[click[0]][click[1]] == "M": #if the clicked cell is a mine, set to X and end game
            board[click[0]][click[1]] = "X"
            return board
        q = deque() # for BFS
        
        q.append(click) #add starting point to queue
        
        board[click[0]][click[1]] = "B" #and mark B as in blank, this will also act as visited
        
        while q:
    
            cur = q.popleft()
            
            r = cur[0]
            c = cur[1]
            
            count = self.count_mines(board, r,c) #count the number of mines in the vicinity of the current cell
            
            if count == 0: #if no mines
                for d in self.dirs:
                    row = r + d[0]
                    col = c + d[1] 
                    
                    if row >= 0 and row < len(board) and col >= 0 and col < len(board[0]) and board[row][col] == "E":# add the neighbors marked "E" to the queue
                        board[row][col] = "B" #and mark them "B"
                        q.append([row, col])
            else:
                board[r][c] = str(count) #else put the count of mines
                
        return board
    
    