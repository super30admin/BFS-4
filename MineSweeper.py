from typing import List,collections
# class Solution:
#     def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
#         dir = [[0,1],[0,-1],[-1,0],[1,0],[1,1],[-1,-1],[-1,1],[1,-1]]
#         m , n = len(board), len(board[0])
#         if board[click[0]][click[1]] == 'M':
#             board[click[0]][click[1]] = 'X'
#             return board

#         q = collections.deque()
#         q.append((click[0],click[1]))
#         board[click[0]][click[1]] = 'B'
#         while q:
#             r,c = q.popleft()
#             count = sum(1 for dr,dc in dir if 0<=dr+r<m and 0<=c+dc<n and board[dr+r][c+dc]== 'M')
#             if count == 0:
#                 for nr,nc in dir:
#                     nr,nc = r+nr,c+nc
#                     if 0<=nr<m and 0<=nc<n and board[nr][nc] == 'E':
#                         q.append((nr,nc))
#                         board[nr][nc] = 'B'
#             else:
#                 board[r][c] = str(count)
#         return board


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m,n= len(board), len(board[0])
        dir = [[0,1],[0,-1],[-1,0],[1,0],[1,1],[-1,-1],[-1,1],[1,-1]]
        def countmines(r,c):
            return sum(1 for dr,dc in dir if 0<=r+dr<m and 0<=c+dc<n and board[r+dr][c+dc] == 'M')
        def dfs(i,j):
            # base
            if i < 0 or j < 0 or i >= m or j >= n or board[i][j] != 'E':
                return
            # logic
            count = countmines(i,j)
            if  count == 0:
                board[i][j] = 'B'
                for nr,nc in dir:
                    if 0<=i+nr<m and 0<=j+nc<n and board[i+nr][j+nc] == 'E':
                        dfs(i+nr,j+nc)
            else:
                board[i][j] = str(count) 
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
        else:
            dfs(click[0],click[1])
        return board
        
        