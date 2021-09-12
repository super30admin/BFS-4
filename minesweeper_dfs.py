
#Time complexity : O(MN)
#Space complexity : O(MN) 
class Solution:
    def __init__(self,dirs=None):
        self.dirs = [(0,-1),(0,1),(-1,0),(1,0),(-1,-1),(1,-1),(-1,1),(1,1)]
        self.m = None
        self.n = None
    
    def count_mines(self, board,point):
        
        cp = 0
        for item in self.dirs:
            r = point[0]+item[0]
            c = point[1]+item[1]
                    
            if (r>=0 and r < self.m) and (c>=0 and c < self.n) and board[r][c]=='M':
                cp+=1
        
       
        return cp
            
        
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
            
        self.m = len(board)
        self.n = len(board[0])
        
        def dfs(popped):
        
            if (popped[0]<0 or popped[0]==self.m) or (popped[1]<0 or popped[1]==self.n) or board[popped[0]][popped[1]]!='E':
                return
            
            
            board[popped[0]][popped[1]] = 'B'
            mines = self.count_mines(board,popped)
            if mines>0:
                board[popped[0]][popped[1]] = str(mines)
            else:
                for direct in self.dirs:
                    r = popped[0]+direct[0]
                    c = popped[1]+direct[1]
                    dfs((r,c))
        

        dfs(click) 
        return board
                    
        