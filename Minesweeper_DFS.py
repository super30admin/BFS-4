#TC=O(NM)
#SC=O(NM)
#DFS
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if(not board):
            return board
        
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        m=len(board)
        n=len(board[0])
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        self.dfs(board,click[0],click[1])
        return board
    
    
    def dfs(self,board,i, j):
            # Base -not winthin boundary or already visited
            if i < 0 or i >= len(board) or j < 0 or j >= len(board[0])  or board[i][j] == 'B':
                return

            # Mark visited
            board[i][j] = 'B'
            mines = self.getMines(board,i, j) #cal mine count
            if(mines==0):
                for dir in self.dirs:
                    r=dir[0]+i
                    c=dir[1]+j
                    self.dfs(board,r,c)
            else:
                board[i][j] = str(mines)
                return

            
           
    
    def getMines(self,board,i,j):
        mines=0
        for dir in self.dirs:
            r=dir[0]+i
            c=dir[1]+j
            if(r>=0 and r<len(board) and c>=0 and c<len(board[0]) and board[r][c]=='M'):
                mines+=1
        return mines
