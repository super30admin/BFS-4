"""
Approach: BFS

Apporach is simple. We will traverse through every cell on the board.

We will use queue for this. We will first add the click cells in the queue and then traverse its adjacent cells to search for mines.

If there are no mines then we will update the cell from E to B and add the cells into the queue for later traversal. 
If mines are found then we will update it to no of adjacent mines found around the cell.  

TC: O(m x n)
SC: O(m x n)

m = no of rows
n = no of columns 
"""

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        
        self.dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[-1,-1],[1,-1],[-1,1]]
        self.m = len(board)
        self.n = len(board[0])
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'  
            return board
        
        q = []
        q.append(click)
        
        board[click[0]][click[1]] = 'B'
        
        while q:
            curr = q.pop(0)
            mines = self.getMines(board,curr[0],curr[1])
            if mines == 0:
                for d in self.dirs:
                    r = curr[0] + d[0]
                    c = curr[1] + d[1]
                    
                    if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == 'E':
                        q.append([r,c])
                        board[r][c] = 'B'
            else:
                board[curr[0]][curr[1]] = str(mines)
                
        return board
    
    def getMines(self,board,i,j):
        mines = 0
        
        for d in self.dirs:
            r = i + d[0]
            c = j + d[1]
            
            if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == 'M':
                mines += 1
                
                
        return mines
    
"""
Approach: DFS


TC: O(m x n)
SC: O(m x n)

m = no of rows
n = no of columns
"""

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        
        self.dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[-1,-1],[1,-1],[-1,1]]
        self.m = len(board)
        self.n = len(board[0])
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.dfs(board,click)
        return board
    
    def dfs(self, board, click):
        if click[0] < 0 or click[0] >= self.m or click[1] < 0 or click[1] >= self.n or board[click[0]][click[1]] != 'E':
            return
        
        mines = self.getMines(board,click[0],click[1])

        board[click[0]][click[1]] = 'B'
        if mines == 0:
            for d in self.dirs:
                r = click[0] + d[0]
                c = click[1] + d[1]
                self.dfs(board,[r,c])
        else:
            board[click[0]][click[1]] = str(mines)
                

    def getMines(self,board,i,j):
        mines = 0
        
        for d in self.dirs:
            r = i + d[0]
            c = j + d[1]
            
            if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == 'M':
                mines += 1
                
        return mines