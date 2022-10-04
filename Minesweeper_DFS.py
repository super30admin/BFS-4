# DFS Approach
# Time complexity : O(n*m)
# Space complexity : O(n*m)
# Leetcode : Solved and submitted

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        # check for null case
        if not board:
            return None
        
        # making the board as global
        self.board = board
        
        # find the rows and cols of the board
        rows = len(self.board)
        cols = len(self.board[0])
        
        # direction array to check all 8 sides of a cell
        self.dirs = [[0,1],[1,0],[-1,0],[0,-1],[-1,-1],[-1,1],[1,-1],[1,1]]
        
        # if the starting point is a Mine, then update the cell as 'X' and return the board
        if self.board[click[0]][click[1]] == 'M':
            self.board[click[0]][click[1]] = 'X'
            return self.board
         
        # call the dfs function with the board and starting index
        self.dfs(self.board, click[0], click[1], rows, cols)
        
        # return the board after modification
        return self.board
    
    def dfs(self, board, i, j, rows, cols):
        # base
        # if we go out of the boundary of matrix or encounter any character other than E, return to the last called function
        if i < 0 or i == rows or j < 0 or j == cols or self.board[i][j] != 'E':
            return
        
        # logic
        # mark the cell as visited
        self.board[i][j] = 'B'
        
        # call the count function to see if we have a fine in any of the neighbors
        count = self.countMines(self.board, i, j, rows, cols)
        
        # if no mine, then we check recursively on each neigbors and update them
        if count == 0:
            for di in self.dirs:
                nr = i + di[0]
                nc = j + di[1]
                
                # recursively call the dfs on the new cell
                self.dfs(self.board, nr, nc, rows, cols)
        else:
            # if we have a mine, then simply update the count of the cell with the count
            board[i][j] = str(count)
    
    
    # count the number of mine for each cell
    def countMines(self, board, i, j, rows, cols):
        count = 0
        
        # compare all the 8 sides with the value M, if found then update the count
        for di in self.dirs:
            nr = i + di[0]
            nc = j + di[1]
            
            if nr >= 0 and nr < rows and nc >= 0 and nc < cols and board[nr][nc] == 'M':
                count += 1
        return count
