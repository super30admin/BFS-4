# Approach - Problem is connected components type as by clicking one spot other neighboring ones open up recursively
# Use BFS or DFS
# Time - O( 8 * MN) ~= O(M * N) since every cell recursively checks 8 cells
# Space - O(M * N)


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        # base case
        if not board or len(board) == 0:
            return board
        
        self.m = len(board)
        self.n = len(board[0])
        
        # 8 directions to explore ( up, down, left, right + 4 diags)
        self.dirs = [(0,1), (0,-1), (1,0), (-1,0), (1,1), (1,-1), (-1, 1), (-1,-1)]
        
        # if at first click itself we touch a Mine, update to X and return the board
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        queue = collections.deque()
        queue.append((click[0], click[1])) # add to queue first click positions and then mark visited ie 'B
        board[click[0]][click[1]] = 'B'
        
        while queue:
            
            current_row, current_col = queue.popleft()
            
            mines = self.getMines(board, current_row, current_col) # how many mines surrounding this position we popped
            
            if mines == 0:
                # process neighbors if already empty 'E' and mark visited and add to queue
                for dir in self.dirs:
                    r = current_row + dir[0]
                    c = current_col + dir[1]
                    
                    if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'E':
                        queue.append((r, c))
                        board[r][c] = 'B'
                        
                
            else:
                # update position with mines count
                board[current_row][current_col] = str(mines)
                
        return board
                
    def getMines(self, board, i, j):
        # count of mines surrounding a position
        
        mines = 0
        
        for dir in self.dirs:
            r = i + dir[0]
            c = j + dir[1]
            
            if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'M':
                mines += 1
                
        return mines
        