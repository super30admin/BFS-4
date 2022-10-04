# BFS Approach
# Time complexity : O(8*n*m)
# Space complexity : O(n*m)
# Leetcode : Solved and submitted

from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
       # check for null case
        if not board:
            return None
        
        # find the rows and cols of the board
        rows = len(board)
        cols = len(board[0])
        
        # direction array to check for all 8 sides of a cell
        self.dirs = [[0,1],[1,0],[-1,0],[0,-1],[-1,-1],[-1,1],[1,-1],[1,1]]
        
        # if the click index itself it a mine, then we change the char to X and return the board
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        # add the first position of the click on matrix to the queue
        q = deque([(click[0],click[1])])
        # mark the index as visited by changing it to B - Blank
        board[click[0]][click[1]] = 'B'
        
        # traverse until the queue is empty
        while q:
            # pop the first set of indexes to check
            curr_x, curr_y = q.popleft()
            # get the count of mines next to the current index
            count = self.countMines(board, curr_x, curr_y, rows, cols)
            # if the count is 0, which means that we can look for more cells from here
            if count == 0:
              # iterate over all the 8 sides and whichever ones have E - empty, add them to the queue if they are inside the matrix
                for di in self.dirs:
                    nr = curr_x + di[0]
                    nc = curr_y + di[1]
                
                    if nr >= 0 and nr < rows and nc >= 0 and nc < cols and board[nr][nc] == 'E':
                        # mark the index as visited
                        board[nr][nc] = 'B'
                        q.append((nr,nc))
            else:
              # if we have a mone nearby then we update the count of the cell and do not add the neighbors to the queue
                board[curr_x][curr_y] = str(count)
        
        # return the modified board as the result
        return board
    
    def countMines(self, board, i, j, rows, cols):
        count = 0
        # check for all the cells next to the current cell
        for di in self.dirs:
            nr = i + di[0]
            nc = j + di[1]
            
            # if the cell is within boundary and the cell is a Mine 'M', then increment the count
            if nr >= 0 and nr < rows and nc >= 0 and nc < cols and board[nr][nc] == 'M':
                count += 1
         # return the final count
        return count
