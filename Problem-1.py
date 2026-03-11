# Time Complexity : O(M * N)   # Each cell in the board may be visited once
# Space Complexity : O(M * N)  # Recursion stack / visited cells during DFS
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# If a mine is clicked, mark it as 'X'.
# Otherwise count adjacent mines; if >0 place the number.
# If no adjacent mines exist, recursively reveal all neighbors.

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        r, c = click
        
        if board[r][c] == 'M':
            board[r][c] = 'X'
            return board
        
        directions = [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]
        
        def dfs(x,y):
            if board[x][y] != 'E':
                return
            
            mines = 0
            for dx,dy in directions:
                nx,ny = x+dx, y+dy
                if 0<=nx<len(board) and 0<=ny<len(board[0]) and board[nx][ny]=='M':
                    mines += 1
            
            if mines > 0:
                board[x][y] = str(mines)
                return
            
            board[x][y] = 'B'
            
            for dx,dy in directions:
                nx,ny = x+dx, y+dy
                if 0<=nx<len(board) and 0<=ny<len(board[0]):
                    dfs(nx,ny)
        
        dfs(r,c)
        return board