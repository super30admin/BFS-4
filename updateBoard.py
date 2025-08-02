# TimeComplexity:O(m x n)
# SpaceComplexity:O(m x n)
# Approach:
# Standard BFS/DFS problem , start at click location and spread ,,important thing is changing vla after visting it

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #no visted needed can check for b
        dir=[(0,1),(1,0),(-1,0),(0,-1),(1,1),(-1,-1),(1,-1),(-1,1)]
        q=deque([click])
        x,y=click[0],click[1]
        if board[x][y]=="M":
            board[x][y]="X"
            return board

        m,n=len(board),len(board[0])
        # print(m,n,"this is m and n")
        while(len(q)):
           
            x,y=q.popleft()
            mine=False
            mineCount=0
            neighbours=[]
            for i,j in dir:
                # print(type(x),type(n),n)
                if -1<i+x<m and -1<j+y<n :
                    if board[i+x][j+y]=="M": 
                        mine=True
                        mineCount+=1
                    elif board[i+x][j+y]!="B" and not board[i+x][j+y].isnumeric() :
                        neighbours.append([i+x,j+y])
            if mine==False:
                for ni in neighbours:
                    board[ni[0]][ni[1]]="B" # this is very important you willbe adding same nodes
                    q.append(ni)
                board[x][y]="B"
            else:
                board[x][y]=str(mineCount)
        return board
            




        
