# // Time Complexity :o(m*n)
# // Space Complexity :O(m*n) 
class Solution:
    def __init__(self):
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        self.dfs(board,click[0],click[1])
        return board
        
    def dfs(self,board,x,y):
        #base
        #logic
        board[x][y]='B'
        cnt=self.countmines(board,x,y)
        if cnt>0:
            board[x][y]=str(cnt)
        else:
            for i in self.dirs:
                r=i[0]+x
                c=i[1]+y
                if r>=0 and c>=0 and r<len(board) and c<len(board[0]) and board[r][c]=='E':
                    board[x][y]='B'
                    self.dfs(board,r,c)
                    

            