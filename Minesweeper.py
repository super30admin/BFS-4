'''
TC: O(m*n) - for rows and column
SC: O(m*n) - for maintaining the queue
'''
from collections import deque
from typing import List

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #BFS
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        ROWS, COLS = len(board), len(board[0])
        q = deque()
        dirs = [(-1,0),(1,1),(0,-1),(-1,-1),(1,0),(-1,1),(0,1),(1,-1)]

        def check_surroundings(r,c):
            count = 0
            res = []
            for dir_ in dirs:
                nr, nc = r+dir_[0], c+dir_[1]
                if nr>=0 and nc>=0 and nr<ROWS and nc<COLS:
                    if board[nr][nc] == 'M':
                        count += 1
                    if board[nr][nc] == 'E':
                        res.append((nr,nc))
            return count, res
        
        q.append((click[0],click[1]))
        board[click[0]][click[1]] = 'B'
        while q:
            r,c = q.popleft()
            count, res = check_surroundings(r,c)
            if count == 0:
                for nodex, nodey in res:
                    board[nodex][nodey] = 'B'
                    q.append((nodex,nodey))
            else:
                board[r][c] = str(count)
        return board
s = Solution()
print(s.updateBoard([["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], [1,1]))
print(s.updateBoard([["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], [1,2]))
print(s.updateBoard([["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], [3,0]))