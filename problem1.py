class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if(board[click[0]][click[1]]=='M'):
            board[click[0]][click[1]]='X'
            return board
        m=len(board)
        n= len(board[0])
        q = [click]
        board[click[0]][click[1]]='B'
        di =[[0,-1],[-1,0],[1,0],[0,1],[1,1],[1,-1],[-1,1],[-1,-1]]
        while(len(q)!=0):
            x,y = q.pop(0)
            count = self.count(board,x,y,di,m,n)
            if(count>0):
                board[x][y]=str(count)
            else:
                for i,j in di:
                    nr = x+i
                    nc = y+j
                    if(nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='E'):
                        q.append([nr,nc])
                        board[nr][nc]='B'
        return board
                        
    def count(self,board,x,y,di,m,n):
        count = 0
        for i,j in di:
            nr = x+i
            nc = y+j
            if(nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='M'):
                count+=1
        return count