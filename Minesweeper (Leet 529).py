'''
Using BFS
Time: O(mn)
Space: O(mn)
'''


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]]  == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,1],[-1,-1]]
        q = collections.deque()
        q.append([click[0],click[1]])
        if board[click[0]][click[1]] == 'E':
            board[click[0]][click[1]] = 'B'
        while len(q) != 0:
            curr = q.popleft()
            
            count = 0
            for di in dirs:
                r = curr[0] + di[0]
                c = curr[1] + di[1]
                if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['M']:
                    count += 1
            if count != 0:
                board[curr[0]][curr[1]] = str(count)
            else:
            
           
                for di in dirs:
                    r = curr[0] + di[0]
                    c = curr[1] + di[1]
                    if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['E']:

                        if board[r][c] == 'E':
                            board[r][c] = 'B'
                            q.append([r,c])
                    

        return board
            
                        
            


'''
Using DFS
Time: O(mn)
Space: O(mn)
'''

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]]  == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,1],[-1,-1]]
        if board[click[0]][click[1]] == 'E':
            board[click[0]][click[1]] = 'B'
        self.dfs(board, click[0], click[1], dirs)
        
        return board
    
    
    def dfs(self, board, i, j, dirs):
        # base
        
            # or i can append directly and check out of bounds conditions for i and j in base block (anything should work)
        
        # logic
        
        count = 0
        for di in dirs:
            r = i + di[0]
            c = j + di[1]
            if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['M']:
                count += 1
        if count != 0:
            board[i][j] = str(count)
        else:
            for di in dirs:
                r = i + di[0]
                c = j + di[1]
                if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['E']:

                    if board[r][c] == 'E':
                        board[r][c] = 'B'
                        self.dfs(board, r, c, dirs)



class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]]  == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,1],[-1,-1]]
        # if board[click[0]][click[1]] == 'E':
        #     board[click[0]][click[1]] = 'B'
        self.dfs(board, click[0], click[1], dirs)
        
        return board
    
    
    def dfs(self, board, i, j, dirs):
        # base
        if i < 0 or j < 0 or i >= len(board) or j >= len(board[0]) or board[i][j] != 'E':
            return
        
        board[i][j] = 'B'
        # logic
        
        count = 0
        for di in dirs:
            r = i + di[0]
            c = j + di[1]
            if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['M']:
                count += 1
        if count != 0:
            board[i][j] = str(count)
        else:
            for di in dirs:
                r = i + di[0]
                c = j + di[1]
#                 if r >= 0 and c >= 0 and r < len(board) and c < len(board[0]) and board[r][c] in ['E']:

#                     if board[r][c] == 'E':
#                         board[r][c] = 'B'
                self.dfs(board, r, c, dirs)