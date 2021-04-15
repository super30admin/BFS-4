#Time Complexity :o(m*n)
#Space Complexity :o(m*n)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    m=None
    n=None
    dirs=None
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if(board==None or len(board)==0):
            return []
        self.m=len(board)
        self.n=len(board[0])
        
        #check if the click itself is a Mine
        if(board[click[0]][click[1]]=='M'):
            board[click[0]][click[1]]='X'
            return board
        
        #mark click as Blank and add in queue
        board[click[0]][click[1]]='B'
        queue=collections.deque()
        queue.append([click[0],click[1]])
        
        self.dirs=[[1,0],[0,1],[-1,0],[0,-1],[1,1],[-1,-1],[1,-1],[-1,1]]        
        while queue:
            curr=queue.popleft()
            r=curr[0]
            c=curr[1]
            mines=self.getMines(r,c,board)
            if(mines==0):
                #process the babies
                for dir in self.dirs:
                    row=r+dir[0]
                    col=c+dir[1]
                    if(row<self.m and col<self.n and row>=0 and col>=0 and board[row][col]=='E'):
                        queue.append([row,col])
                        board[row][col]='B'
            #Update the mines count at a given cell
            else:
                board[r][c]=str(mines)
        return board
    
    #get mines count
    def getMines(self,i,j,board):
        count=0
        for dir in self.dirs:
            r=i+dir[0]
            c=j+dir[1]
            if(r<self.m and c<self.n and r>=0 and c>=0 and board[r][c]=='M'):
                count+=1
        return count