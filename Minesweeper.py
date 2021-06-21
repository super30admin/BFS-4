class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
                
        if not board:
            return  []
        if board[click[0]][click[1]] == 'M':            
            board[click[0]][click[1]] = "X"
            return board
        q = deque()           
        board[click[0]][click[1]] = "B"
        q.append((click))
        m,n = len(board), len(board[0])
        dirs = [(1,0),(0,1),(-1,0),(0,-1),(-1,-1),(1,-1),(-1,1),(1,1)]
        
        while q:
            i,j = q.popleft()
            mines = self.get_mines(board,i,j,m,n)
            if mines:
                board[i][j] = str(mines)
            else:
                for dir_ in dirs:
                    r = i+ dir_[0] 
                    c = j+ dir_[1]
                    if r >=0 and c >=0 and r<m and c < n and board[r][c] == 'E':
                        board[r][c] = 'B'
                        q.append((r,c))
                
        return board              
                                     
    def get_mines(self,board,i,j,m,n):
        
        dirs = [(1,0),(0,1),(-1,0),(0,-1),(-1,-1),(1,-1),(-1,1),(1,1)]
        mines = 0
        for dir_ in dirs:
            r = i+ dir_[0] 
            c = j+ dir_[1]
            
            if r >=0 and c >=0 and r<m and c < n and board[r][c] == 'M':
                mines +=1
        
        return mines
            
