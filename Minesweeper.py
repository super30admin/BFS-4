from collections import deque

class Solution:
    #Solution 1
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #Approach: BFS
        #Time Complexity: O(m * n)
        #Space Complexity: O(m * n)
        #where, the board is of m*n size
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        m = len(board)
        n = len(board[0])
        
        dirArr = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
        
        de = deque()
        de.append(click)
        board[click[0]][click[1]] = 'B'
        
        while de:
            popped = de.popleft()
            mines = 0
            neighbors = []
            for dir in dirArr:
                r = popped[0] + dir[0]
                c = popped[1] + dir[1]
                if r >= 0 and r < m and c >= 0 and c < n and board[r][c] == 'M':
                    mines += 1
                if r >= 0 and r < m and c >= 0 and c < n and board[r][c] == 'E':
                    neighbors.append([r, c])
                    
            if mines != 0:
                board[popped[0]][popped[1]] = str(mines)
            
            else:
                for neighbor in neighbors:
                    de.append(neighbor)
                    board[neighbor[0]][neighbor[1]] = 'B'
                    
        return board
    
    #Solution 2
    """"
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #Approach: DFS
        #Time Complexity: O(m * n)
        #Space Complexity: O(m * n)
        #where, the board is of m*n size
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.m = len(board)
        self.n = len(board[0])
        
        self.dirArr = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
        
        self.dfs(board, click[0], click[1])
        return board
    
    def dfs(self, board, i, j):
        #base
        if i < 0 or i == self.m or j < 0 or j == self.n or board[i][j] != 'E':
            return
        
        #logic
        board[i][j] = 'B'
        mines = 0
        neighbors = []
        for dir in self.dirArr:
            r = i + dir[0]
            c = j + dir[1]
            if r >= 0  and r < self.m and c >= 0 and c < self.n and board[r][c] == 'M':
                mines += 1
            neighbors.append([r, c])
                
        if mines != 0:
            board[i][j] = str(mines)
            
        else:
            for neighbor in neighbors:
                self.dfs(board, neighbor[0], neighbor[1])
    """