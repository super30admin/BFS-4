class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board:
            return None
        m = len(board)
        n = len(board[0])
        qu = deque()
        def countMines(i,j):
            count = 0
            for a in [0,1],[1,0],[0,-1],[-1,0],[1,1],[-1,1],[1,-1],[-1,-1]:
                r = i + a[0]
                c = j + a[1]
                if(0<=r<m and 0<=c<n and board[r][c]=="M"):
                    count+=1
            return count
        
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        qu.append([click[0],click[1]])
        board[click[0]][click[1]] = "B"
        
        while(qu):
            curr = qu.popleft()
            count = countMines(curr[0],curr[1])
            if count == 0:
                for a in [0,1],[1,0],[0,-1],[-1,0],[1,1],[-1,1],[1,-1],[-1,-1]:
                    r = curr[0] + a[0]
                    c = curr[1] + a[1]
                    if (0 <=r< m and 0<=c<n and board[r][c] == "E"):
                        qu.append([r,c])
                        board[r][c] = "B"
            else:
                board[curr[0]][curr[1]] = str(count)
        return board
                
              
        
        
            