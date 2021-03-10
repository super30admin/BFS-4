class Solution:
    def getMines(self, board, row, column):
        # Time Complexity: O(mn)
        # Space Complexity: O(mn)
        dirs = [[0,1],[0,-1],[1,0],[-1,0],[1,1],[-1,-1],[1,-1],[-1,1]]
        o=0
        for i in dirs:
            r = row + i[0]
            c = column + i[1]
            if(r>=0 and r<len(board) and c>=0 and c<len(board[0]) and board[r][c]=="M"):
                o+=1
        return o
            
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if(board==None or len(board)==0):
            return board
        
        dirs = [[0,1],[0,-1],[1,0],[-1,0],[1,1],[-1,-1],[1,-1],[-1,1]]
        
        # base case
        
        if(board[click[0]][click[1]]=="M"):
            board[click[0]][click[1]]="X"
            return board
        
        q = deque([])
        board[click[0]][click[1]]='B'
        q.append([click[0],click[1]])
        while(len(q)>0):
            curr = q.popleft()
            mines = self.getMines(board,curr[0],curr[1])
            if(mines==0):
                for dir in dirs:
                    r = curr[0] + dir[0]
                    c = curr[1] + dir[1]
                    if(r>=0 and r<len(board) and c>=0 and c<len(board[0]) and board[r][c]=="E"):
                        q.append([r,c])
                        board[r][c] = "B"
            else:
                board[curr[0]][curr[1]] = str(mines)
        return board
