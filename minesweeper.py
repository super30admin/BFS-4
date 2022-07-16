#Time Complexity: O(mn)
#Space Complexity: O(mn)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        self.dirns = [[0,1],[0,-1],[-1,0],[1,0],[-1,1],[-1,-1],[1,-1],[1,1]]
        m = len(board)
        n = len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        self.dfs(board,click[0],click[1],m,n)
        return board
    
    def dfs(self,board,i,j,m,n):
        if i<0 or i>m or j<0 or j>n or board[i][j] != 'E':
            return
        
        board[i][j] = 'B'
        mines = self.countMines(board,i,j,m,n)
        if mines>0:
            board[i][j] = str(mines)
        else:
            for dirn in self.dirns:
                nr = i + dirn[0]
                nc = j + dirn[1]
                if nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc] == 'E':
                    self.dfs(board,nr,nc,m,n)
            
        
        
        
        
    def countMines(self,board,i,j,m,n):
        count = 0
        for dirn in self.dirns:
            nr = i + dirn[0]
            nc = j + dirn[1]
            if nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc] == 'M':
                count += 1
        return count
                
                