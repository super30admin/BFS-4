#Time Complexity: O(m*n)
#Space Complexity: O(m*n)

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m=len(board)
        n=len(board[0])
        que=collections.deque()
        directions=[[0,1],[1,0],[0,-1],[-1,0],[-1,1],[1,-1],[-1,-1],[1,1]]
        output=[[0]*n for _ in range(m)]
        for i in range(0,m):
            for j in range(0,n):
                output[i][j]=board[i][j]
        if board[click[0]][click[1]]=="M":
            output[click[0]][click[1]]="X"
            return output
        else:
            que.append((click[0],click[1]))
            output[click[0]][click[1]]="B"
        
        while(que):
            i,j=que.popleft()
            mines=self.calculateNeighbours(i,j,directions,board)
            if mines==0:
                for d in directions:
                    ni=i+d[0]
                    nj=j+d[1]
                    if ni>=0 and ni<len(board) and nj>=0 and nj<len(board[0]): 
                        if output[ni][nj]!="B":
                            que.append((ni,nj))
                            output[ni][nj]="B"
            else:
                output[i][j]=str(mines)
        return output

    def calculateNeighbours(self, r, c, directions, board):
        result=0
        for d in directions:
            i=r+d[0]
            j=c+d[1]
            if i>=0 and i<len(board) and j>=0 and j<len(board[0]):
                if board[i][j]=="M":
                    result+=1
        return result

#--------------------------------------------------------------------
#TC: O(m*n)
#SC : O(m*n)
class Solution:
    global output
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m=len(board)
        n=len(board[0])
        que=collections.deque()
        directions=[[0,1],[1,0],[0,-1],[-1,0],[-1,1],[1,-1],[-1,-1],[1,1]]
        self.output=[[0]*n for _ in range(m)]
        for i in range(0,m):
            for j in range(0,n):
                self.output[i][j]=board[i][j]
        if board[click[0]][click[1]]=="M":
            self.output[click[0]][click[1]]="X"
            return self.output
        else:
            que.append((click[0],click[1]))
            #self.output[click[0]][click[1]]="B"
        self.dfs(click[0],click[1],directions,board)
        return self.output

    def dfs(self,i,j,directions, board):
        if i<0 or i >=len(board) or j<0 or j>=len(board[0]) or self.output[i][j]=="B":
            return 
        self.output[i][j]="B"
        mines=self.calculateNeighbours(i,j,directions,board)
        if mines==0:
            for d in directions:
                ni=i+d[0]
                nj=j+d[1]
                self.dfs(ni,nj,directions, board)
        else:
            self.output[i][j]=str(mines)
        
    

    def calculateNeighbours(self, r, c, directions, board):
        result=0
        for d in directions:
            i=r+d[0]
            j=c+d[1]
            if i>=0 and i<len(board) and j>=0 and j<len(board[0]):
                if board[i][j]=="M":
                    result+=1
        return result
