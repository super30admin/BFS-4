#Time complexity: O(mn)
#Space Complexity:O(mn)

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        self.dirs=[[-1,-1],[-1,0],[-1,1],[0,-1],[0,0],[0,1],[1,-1],[1,0],[1,1]]
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
        else:
            self.dfs(board,click[0],click[1])
        return board
    
    
    def dfs(self,board,i,j):
        mines=self.numMines(board,i,j)
        
        if mines==0:
            board[i][j]='B'
            for dir in self.dirs:
                r=dir[0]+i
                c=dir[1]+j
                if 0<=r<len(board) and 0<=c<len(board[0]) and board[r][c]=='E':
                    self.dfs(board,r,c)
        else:
            board[i][j]=str(mines)
        
    def numMines(self,board,i,j):
        cnt=0
        for dir in self.dirs:
            r=dir[0]+i
            c=dir[1]+j
            if 0<=r<len(board) and 0<=c<len(board[0]) and board[r][c]=='M':
                cnt+=1
        return cnt
