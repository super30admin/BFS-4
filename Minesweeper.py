# Time Complexity - O(N)
# Space Complexity - O(1) recursive stack
class Solution:
    def __init__(self):
        self.dirs = [[0, 1], [1, 0], [1, 1], [-1, -1], [-1, 0], [0, -1], [1, -1], [-1, 1]]

    def updateBoard(self, board, click):
        n = len(board)
        m = len(board[0])

        i = click[0]
        j = click[1]

        # condition 1:
        if board[i][j] == 'M':
            board[i][j] = 'X'
            return board

        # call dfs here
        self.dfs(board, i, j)
        return board
    
    def dfs(self,board,i,j):
        n = len(board)
        m = len(board[0])
        #Base case
        if(i<0 or i>=n or j<0 or j>=m or board[i][j]!='E'):
            return
        
        #Logic
        mines=self.getMines(board,i,j)
        if(mines==0):
            board[i][j]='B'
            for direction in self.dirs:
                r=direction[0]+i
                c=direction[1]+j
                self.dfs(board,r,c)
        else:
            board[i][j]=str(mines)
    
    
    def getMines(self,board,i,j):
        n = len(board)
        m = len(board[0])
        count=0
        for directions in self.dirs:
            r=directions[0]+i
            c=directions[1]+j
            if(r<0 or r>=n or c<0 or c>=m):
                continue
            if(board[r][c]=='M'):
                count+=1
        return count
