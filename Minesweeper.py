# time complexity is o(mn), where m,n is number of rows and columns in the board respectively
# space complexiyt is o(mn), where m,n is number of rows and columns in the board respectively
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #bfs
        if(len(board) == 0):
            return None
        m = len(board)
        n = len(board[0])
        self.dirs = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]
        if(board[click[0]][click[1]] == 'M'):
            board[click[0]][click[1]] = 'X'
            return board
        from collections import deque
        q = deque()
        q.append(click)
        board[click[0]][click[1]] = 'B'
        while(len(q) > 0):
            curr = q.popleft()
            mines = self.countMines(board, curr, m, n)
            if(mines > 0):
                board[curr[0]][curr[1]] = str(mines)
            else:
                for d in self.dirs:
                    nr = curr[0] + d[0]
                    nc = curr[1] + d[1]
                    if(nr >= 0 and nc >= 0 and nr < m and nc < n and board[nr][nc] == 'E'):
                        q.append([nr, nc])
                        board[nr][nc] = 'B'
                        
        return board
    
    def countMines(self, board, curr, m, n):
        count = 0
        for d in self.dirs:
            nr = curr[0] + d[0]
            nc = curr[1] + d[1]
            if(nr >= 0 and nc >= 0 and nr < m and nc < n and board[nr][nc] == 'M'):
                count += 1
        return count
    
    
    
        
        
        
        #dfs
#         if(len(board) == 0):
#             return None
#         m = len(board)
#         n = len(board[0])
#         self.dirs = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]
#         if(board[click[0]][click[1]] == 'M'):
#             board[click[0]][click[1]] = 'X'
#             return board
#         self.dfs(board, click, m, n)
#         return board
        
#     def dfs(self, board, curr, m, n):
#         #base
#         if(curr[0] < 0 or curr[1] < 0 or curr[0] >= m or curr[1] >= n or board[curr[0]][curr[1]] != 'E'):
#             return
#         #logic
#         board[curr[0]][curr[1]] = 'B'
#         mines = self.countMines(board, curr, m, n)
#         if(mines > 0):
#             board[curr[0]][curr[1]] = str(mines)
#         else:
#             for d in self.dirs:
#                 nr = curr[0] + d[0]
#                 nc = curr[1] + d[1]
#                 self.dfs(board, [nr, nc], m, n)
                
    
#     def countMines(self, board, curr, m, n):
#         count = 0
#         for d in self.dirs:
#             nr = curr[0] + d[0]
#             nc = curr[1] + d[1]
#             if(nr >= 0 and nc >= 0 and nr < m and nc < n and board[nr][nc] == 'M'):
#                 count += 1
#         return count
    
        
        
        
        