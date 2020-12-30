"""
Time Complexity : O(mn)
Space Complexity : O(mn)
Did this code successfully run on Leetcode : I don't have leetcode Premium. I saw the code online and just
wrote down my algo
Any problem you faced while coding this : no

Here, we can do this problem by both bfs as well as DFS(commented). We just need to look at the rules provided in the question
and follow them along.
"""
from collections import deque


class Solution:

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board:
            return []
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        q = deque()
        self.m = len(board)
        self.n = len(board[0])
        self.dirs = ((-1, 0), (1, 0), (0, -1), (0, 1),
                     (-1, -1), (-1, 1), (1, -1), (1, 1))
        q = collections.deque([(click[0], click[1])])
        board[click[0]][click[1]] = 'B'
        while q:
            r, c = q.popleft()
            mines = self.findMines(board, r, c)
            if mines > 0:
                board[r][c] = str(mines)
                continue
            for d in self.dirs:
                i = d[0]+r
                j = d[1]+c
                if i >= 0 and i < self.m and j >= 0 and j < self.n and board[i][j] == 'E':
                    q.append((i, j))
                    board[i][j] = 'B'
        return board

    def findMines(self, board, r, c):
        count = 0
        for d in self.dirs:
            i = d[0]+r
            j = d[1]+c
            if i >= 0 and i < self.m and j >= 0 and j < self.n and board[i][j] == 'M':
                count += 1
        return count
#     def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
#         if not board:
#             return []
#         if board[click[0]][click[1]]=='M':
#             board[click[0]][click[1]]='X'
#             return board
#         self.m=len(board)
#         self.n=len(board[0])
#         self.dirs=((-1,0),(1,0),(0,-1),(0,1),(-1,-1),(-1,1),(1,-1),(1,1))
#         self.dfs(board,click[0],click[1])
#         return board


#     def dfs(self,board,i,j):
#         if i<0 or i>=self.m or j<0 or j>=self.n or board[i][j]=='B':
#             return
#         mines=self.findMines(board,i,j)
#         if mines>0:
#             board[i][j]=str(mines)
#             return
#         board[i][j]='B'
#         for d in self.dirs:
#             r=d[0]+i
#             c=d[1]+j
#             self.dfs(board,r,c)
