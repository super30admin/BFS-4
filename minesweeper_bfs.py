
#Time complexity : O(MN)
#Space complexity : O(MN) 
from collections import deque
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
                    
            if (r>=0 and r < self.m) and (c>=0 and c < self.n) and board[r][c]=='M': #count only mines
                cp+=1
        
       
        return cp
            
        
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
            
        self.m = len(board)
        self.n = len(board[0])
        queue = deque()
        board[click[0]][click[1]] = 'B'
        queue.append((click[0],click[1]))
        
        while(queue):
            popped = queue.popleft()
            mines = self.count_mines(board,popped)
            if mines>0:
                board[popped[0]][popped[1]] = str(mines)
            else:
                for direct in self.dirs:
                    r = popped[0]+direct[0]
                    c = popped[1]+direct[1]
                    
                    
                    if (r>=0 and r < self.m) and (c>=0 and c < self.n) and board[r][c]=='E': #only empty cells to be added
                        board[r][c]='B'
                        queue.append((r,c))
            
        return board
                    
        