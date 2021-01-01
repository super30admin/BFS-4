#TC=O(NM)
#SC=O(NM)
#BFS
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if(not board):
            return board
        
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        m=len(board)
        n=len(board[0])
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        q=collections.deque()
        q.append(click)
        board[click[0]][click[1]]='B'
        while q:
            curr=q.popleft()
            mines=self.getMines(board,curr[0],curr[1])
            if(mines==0):
                for dir in self.dirs:
                    r=dir[0]+curr[0]
                    c=dir[1]+curr[1]
                    if(r>=0 and r<m and c>=0 and c<n and board[r][c]=='E'):
                        q.append([r,c])
                        board[r][c]='B'
            else:#mines > 0
                board[curr[0]][curr[1]]=str(mines)
        
        return board
    
    def getMines(self,board,i,j):
        mines=0
        for dir in self.dirs:
            r=dir[0]+i
            c=dir[1]+j
            if(r>=0 and r<len(board) and c>=0 and c<len(board[0]) and board[r][c]=='M'):
                mines+=1
        return mines
