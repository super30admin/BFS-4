class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        dirs = [[1,0],[0,1],[1,1],[-1,0],[0,-1],[1,-1],[-1,1],[-1,-1]]
        a,b = click
        queue = deque([])
        if board[a][b] == "M":
            board[a][b] = "X"
            return board
        else:
            queue.append(click)
            
        while queue:
            curr = queue.popleft()
            i,j = curr
            if board[i][j] == "E":
                numberOfMines = self.findNumberOfMines(board,  curr, dirs)
                if numberOfMines=="0":
                    board[i][j] = "B"
                    for a,b in dirs:
                        a+=i
                        b+=j
                        if 0<=a<len(board) and 0<=b<len(board[0]) and board[a][b] == "E":
                            queue.append([a,b])
                else:
                    board[i][j] = numberOfMines
        return board
                
    def findNumberOfMines(self, board, curr, dirs):
        i, j = curr
        count = 0
        for a,b in dirs:
            a+=i
            b+=j
            if 0<=a<len(board) and 0<=b<len(board[0]) and board[a][b] == "M":
                count+=1
        
        return str(count)
    
            
        
            
                
                
            
        
