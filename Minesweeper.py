"""
Time Complexity : O(m x n) where m and n are the dimensions of the board
Space Complexity : O(m x n) where m and n are the dimensions of the board
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        
        self.m = len(board)
        self.n = len(board[0])
        
        # If we revealed a mine i.e "M" we change it to 'X' and return the board as 
        # our game is over
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [1, -1], [-1, 1], [1, 1], [-1, -1]]
        
        q = deque([click])
        # We first initialize the click position to 'B' and mark it as blank and
        # perform BFS
        board[click[0]][click[1]] = 'B'
        while q:
            curr = q.popleft()
            # We get the mines that are marked near the current position we are 
            # visiting and then we check if there are no mines then we simply keep on
            # on visiting otherwise we mark the current pos as the no of mines
            mines = self.getMines(board, curr[0], curr[1])
            if mines != 0:
                board[curr[0]][curr[1]] = str(mines)
            else:
                for d in self.dirs:
                    row = curr[0] + d[0]
                    col = curr[1] + d[1]
                    if row >= 0 and row < self.m and col >= 0 and col < self.n and board[row][col] == 'E':
                        q.append([row, col])
                        board[row][col] = 'B'
        return board
    
    def getMines(self, board, i, j):
        total = 0
        for d in self.dirs:
            nr = i + d[0]
            nc = j + d[1]
            if nr >= 0 and nr < self.m and nc >= 0 and nc < self.n and board[nr][nc] == 'M':
                total += 1
        return total
        