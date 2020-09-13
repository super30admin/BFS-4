from queue import Queue
class Solution:
    n=0
    dirs=[(-1,0),(0,1),(0,-1),(1,0),(-1,1),(1,-1),(-1,-1),(1,1)]
    m=0
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if click==None or len(click)==0 or len(board)==0 or board==None:
            return []
        
        self.m=len(board)
        self.n=len(board[0])
        if board[click[0]][click[1]]=="M":
            board[click[0]][click[1]]='X'
            return board
        q=Queue()
        q.put(click)
        board[click[0]][click[1]]='B'
        while(not(q.empty())):
            
            curr=q.get()
            #don't just add the child, but check if mine is present in children
            if self.checkMine(board,curr[0],curr[1])==0:
                for dir1 in self.dirs:
                    nr=dir1[0]+curr[0]
                    nc=curr[1]+dir1[1]
                    #ye idhar process karna hain to bounds check..positive bound check  
                    if nr>=0 and nc>=0 and nr<self.m and nc<self.n and board[nr][nc]=='E':
                        q.put([nr,nc])
                        board[nr][nc]='B'
            else:
                board[curr[0]][curr[1]]=str(self.checkMine(board,curr[0],curr[1]))
        return board
                     
    def checkMine(self,board,i,j):
        count=0
        for dir1 in self.dirs:
            r=dir1[0]+i
            c=dir1[1]+j
            if r>=0 and c>=0 and r<self.m and c<self.n and board[r][c]=='M':
                count+=1
        
        return count    
        
#time is O(n2)
#Space is O(n2)
