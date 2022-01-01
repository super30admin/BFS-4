#Time O(n*m), space 2(n+m)
class Solution:         
            
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        #Case 1
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        
        q1=deque()
        q2=deque()
        
        
        q1.append(click[0])
        q2.append(click[1])
        board[click[0]][click[1]] = 'B'
        
        dir=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[-1,-1],[1,-1],[-1,1]]
        
        while q1:
            cr = q1.popleft()
            cc=q2.popleft()
        
            mines = self.getmines(cr,cc,board,dir)
        #case 3
            if mines !=0:
                board[cr][cc]= str(mines)
            #case 2   
            else:
                for i in dir:
                    nr=cr+i[0]
                    nc=cc+i[1]
                
                    if (nr>=0 and nc>=0 and nr<len(board) and nc<len(board[0]) and board[nr][nc]=='E'):
                        board[nr][nc]='B'
                        q1.append(nr)
                        q2.append(nc)
                        
                        
        return board
                        
    def getmines(self,cr,cc,board,dir):
        mines=0

        for j in dir:
            nr=cr+j[0]
            nc=cc+j[1]

            if nr>=0 and nr<len(board) and nc>=0 and nc<len(board[0]) and board[nr][nc]=='M':
                mines+=1
        return mines
