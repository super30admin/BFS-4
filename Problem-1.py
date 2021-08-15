"""
Approach: DFS

TC: O(mn) m -> no. of rows in board, n -> num of cols in board
SC: O(mn)

"""
from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        m = len(board)
        n = len(board[0])
        q = deque()
        q.append((click[0],click[1]))
        board[click[0]][click[1]] = 'B'
        dirs = [[0, 1], [0, -1], [-1, 0], [1, 0], [1, 1],[-1, 1],[1, -1],[-1, -1]]
        while q:
            curr = q.popleft()
            moves = self.getMines(board, m,n, curr[0], curr[1])
            
            if moves:
                board[curr[0]][curr[1]] = str(moves)
            else:
                for offset_i, offset_j in dirs:
                    new_i = curr[0] + offset_i
                    new_j = curr[1] + offset_j
                    if (m > new_i >= 0 and n > new_j >= 0 and board[new_i][new_j] == 'E'):
                        board[new_i][new_j] = 'B'
                        q.append((new_i, new_j))
        return board
    
    def getMines(self, board, m,n, r, c):
        dirs = [[0, 1], [0, -1], [-1, 0], [1, 0], [1, 1],[-1, 1],[1, -1],[-1, -1]]
        count = 0
        for offset_r, offset_c in dirs:
            new_r = r + offset_r
            new_c = c + offset_c
            if m > new_r >= 0 and n > new_c >= 0 and board[new_r][new_c] == 'M':
                count += 1
                
        return count