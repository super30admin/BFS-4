# Using BFS
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        self.dirs = [[0, 1], [0, -1], [1, 0], [-1, 0], [1, 1], [-1, -1], [-1, 1], [1, -1]]
        self.m, self.n = len(board), len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        q = deque()
        q.append([click[0], click[1]])
        board[click[0]][click[1]] = 'B'
        while q:
            curr = q.popleft()
            mines = self.getMines(board, curr[0], curr[1])
            if mines != 0:
                board[curr[0]][curr[1]] = str(mines)
            else:
                for direc in self.dirs:
                    r = curr[0] + direc[0]
                    c = curr[1] + direc[1]
                    if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'E':
                        q.append([r, c])
                        board[r][c] = 'B'
        return board
    
    def getMines(self, board, i, j):
        count = 0
        for direc in self.dirs:
            r = direc[0] + i
            c = direc[1] + j
            if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'M':
                count += 1
        return count
        
        
# Time Complexity: O(m * n)
# Space Complexity: O(m * n)


# Using DFS
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        self.dirs = [[0, 1], [0, -1], [1, 0], [-1, 0], [1, 1], [-1, -1], [-1, 1], [1, -1]]
        self.m, self.n = len(board), len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        self.dfs(board, click[0], click[1])
        return board
    
    def dfs(self, board, i, j):
        # if i < 0 or i == self.m or j == self.n or j < 0 or board[i][j] != 'E':
        #     return
        board[i][j] = 'B'
        mines = self.getMines(board, i, j)
        if mines != 0:
            board[i][j] = str(mines)
        else:
            for direc in self.dirs:
                r = direc[0] + i
                c = direc[1] + j
                if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'E':
                    self.dfs(board, r, c)
        
    def getMines(self, board, i, j):
        count = 0
        for direc in self.dirs:
            r = direc[0] + i
            c = direc[1] + j
            if r >= 0 and r < self.m and c >= 0 and c < self.n and board[r][c] == 'M':
                count += 1
        return count

# Time Complexity: O(m * n)
# Space Complexity: O(m * n)
        
        