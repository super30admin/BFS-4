#time complexityO(m+n)
#space complexity O(m+n) queue size
class Solution:
    _dir=[[0,1],[1,0],[0,-1],[-1,0],[1,1],[-1,-1],[1,-1],[-1,1]]
    m=0
    n=0
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board==None or board==[]: return 
       
        q=[]
        if board[click[0]][click[1]]=='M':  
            board[click[0]][click[1]]='X' 
            return board
        self.m=len(board)
        self.n=len(board[0])
        q.append(click)
        board[click[0]][click[1]]='B'
        while q!=[]:
            curr=q.pop(0)
            mine=self.getMine(board,curr[0],curr[1])
            print(mine)
            if mine==0:
                for d in self._dir:
                    r=d[0]+curr[0]
                    c=d[1]+curr[1]
                    if r>=0 and r<self.m and c>=0 and c<self.n and board[r][c]=='E':
                        q.append([r,c])
                        board[r][c]='B'
            else:
                board[curr[0]][curr[1]]=str(mine)
        return board
        
        
        
    def getMine(self,board,i,j):
        mine=0
        for d in self._dir:
            r=d[0]+i
            c=d[1]+j
            if r>=0 and r< self.m and c>=0 and c<self.n and board[r][c]=='M':
                mine+=1
        return mine
            
        