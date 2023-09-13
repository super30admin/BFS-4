
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        #Method 1 - BFS
        # Time Complexity: O(m*n) 
        # Space Complexity: O(m*n)

        
        # m = len(board)
        # n = len(board[0])

        # if board[click[0]][click[1]] == 'M': #if click itself is M mark X and return
        #     board[click[0]][click[1]] = 'X'
        #     return board
        
        # dirs=[(1,0), (0,1), (-1,0), (0,-1), (-1,-1), (-1,1), (1,-1), (1,1)]

        # def countMines(board,r, c, m , n, dirs): #count mines for current cell
        #     count = 0
        #     for d in dirs:
        #         nr = r + d[0]
        #         nc = c + d[1]
        #         if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='M': #explore all neighbours and if any of them is M, increase count
        #             count+=1
        #     return count

        # queue = deque()
        # queue.append((click[0], click[1])) #add click to Q and mark it B
        # board[click[0]][click[1]] = 'B'

        # while queue:
        #     curr_r, curr_c = queue.popleft() 
        #     count = countMines(board, curr_r, curr_c, m , n, dirs) #get count of mines for current cell
        #     if count==0: #if count is 0, then process the neighbours
        #         for d in dirs:
        #             nr = curr_r + d[0]
        #             nc = curr_c + d[1]
        #             if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='E': #only process nieghbours if they are empty cells that aren't processed already, if it's a mine, don't.
        #                 board[nr][nc] = 'B' #mark it B -> visted already
        #                 queue.append((nr,nc)) #add to q.
        #     else: #if not 0, then udpate curr cell to count
        #         board[curr_r][curr_c] = str(count)
        
        # return board

        #Method 2 - DFS
        # Time Complexity: O(m*n) 
        # Space Complexity: O(m*n)
        m = len(board)
        n = len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        dirs=[(1,0), (0,1), (-1,0), (0,-1), (-1,-1), (-1,1), (1,-1), (1,1)]

        def countMines(board,r, c, m , n, dirs):
            count = 0
            for d in dirs:
                nr = r + d[0]
                nc = c + d[1]
                if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='M':
                    count+=1
            return count

        def dfs(board,r, c, m , n, dirs):
            #base
            if r<0 or c<0 or r>m or c>n or board[r][c]!='E': #out of bounds check and if current cell isn't an E -> already processed or is a mine don't process it
                return 
            #logic
            board[r][c]='B' #mark curr as B -> visted
            count=countMines(board, r, c, m , n, dirs) #get mine count
            if count==0: #if 0, then process the neighbours
                for d in dirs: 
                    nr = r + d[0]
                    nc = c + d[1]
                    if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='E': ##only process nieghbours if they are empty cells that aren't processed already, if it's a mine, don't.
                        dfs(board,nr, nc, m , n, dirs)
            else: #if not 0, then udpate curr cell to count
                board[r][c] = str(count)
        
        dfs(board,click[0], click[1], m , n, dirs)
        return board