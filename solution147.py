#Time Complexity:O(mn)
#Space Complexity:O(mn)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if len(board)==0:
            return board
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]       #declare the 8 directions to be checked for mines
        self.m=len(board)
        self.n=len(board[0])
        if board[click[0]][click[1]]=='M':                                      #if the initial click is a mines replace with X and return board
            board[click[0]][click[1]]='X'
            return board
        d=deque()                                                               #use a deque to store board positions 
        d.append([click[0],click[1]])                                           #start from clicked position and replace it with blank
        board[click[0]][click[1]]='B'
        while d:                                                                #process elements in q one after another.
            curr=d.popleft()                                            
            mines=self.getMines(board,curr[0],curr[1])                          #calculate how many mines are around a given position
            if mines==0:                                                        #if there are zero mines
                for di in self.dirs:                                            #if any of the neighbors are E replace them with B
                    r=curr[0]+di[0]
                    c=curr[1]+di[1]
                    if r>=0 and r<self.m and c>=0 and c<self.n and board[r][c]=='E':
                        d.append([r,c])
                        board[r][c]='B'
            else:
                board[curr[0]][curr[1]]=str(mines)                              #if there are mines in surrounding replace value with number of mines
        return board                                                           
    
    def getMines(self,board:List[List[str]], i:int,j:int):                      #to count number of mines in the surrounding check howmany neighbors have M and return the count
        mines=0
        for di in self.dirs:
            r=i+di[0]
            c=j+di[1]
            if r>=0 and r<self.m and c>=0 and c<self.n and board[r][c]=='M':
                mines+=1
        return mines