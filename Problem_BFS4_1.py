from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        EMPTY="E"
        MINE="M"
        BLANK="B"
        BOOM="X"
        n=len(board)
        if n==0: return board
        m=len(board[0])
        DIRECTIONS=[[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,1],[-1,-1]]
        queue=deque()
        queue.append(click)
        while queue:
            row,col= queue.popleft()
            
            if board[row][col]==MINE:
                board[row][col]=BOOM
                return board
            elif board[row][col]==EMPTY:
                mines=[]
                count=0
                for d in DIRECTIONS:
                    r=row+d[0]
                    c=col+d[1]
                    
                    if r<0 or c<0 or r>=n or c>=m or (board[r][c]!=EMPTY and board[r][c]!=MINE):
                        continue
                    elif board[r][c]==MINE:
                        count+=1
                    mines.append([r,c])
                if count==0:
                    board[row][col]=BLANK
                    queue.extend(mines)
                else:
                    board[row][col]=str(count)
                    
        return board

%TC: O(n)
%SC: O(n)