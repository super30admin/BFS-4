#Time complexity: O(mn)
#Space complexity: O(m) or O(n)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        q=deque()
        dirs=[(0,1),(0,-1),(1,0),(-1,0),(1,1),(-1,-1),(1,-1),(-1,1)]
        if board[click[0]][click[1]]=="M":
            board[click[0]][click[1]]="X"
            return board
        q.append((click[0],click[1]))
        board[click[0]][click[1]]="B"
        while q:
            i,j=q.popleft()
            if self.helper(i,j,board,dirs)!=0:
                board[i][j]=str(self.helper(i,j,board,dirs))
            else:
                for d in dirs:
                    nr=i+d[0]
                    nc=j+d[1]
                    if nr>=0 and nr<len(board) and nc>=0 and nc<len(board[0]) and board[nr][nc]=="E":
                        q.append((nr,nc))
                        board[nr][nc]="B"
        return board
        
    def helper(self,i,j,board,dirs):
        count=0
        for d in dirs:
            nr=i+d[0]
            nc=j+d[1]
            if nr>=0 and nr<len(board) and nc>=0 and nc<len(board[0]) and board[nr][nc] == "M":
                count+=1
        return count
                    
                
            
        
            
        