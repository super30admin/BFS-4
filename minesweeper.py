# Time O(m*n)
# Space O(m*n)
from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]] == 'M': 
            board[click[0]][click[1]] = 'X'
            return board
        m = len(board)
        n = len(board[0])
        dq = deque()
        visited = set((click[0], click[1]))
        dq.append((click[0], click[1]))
        
        directions = [(-1,0), (1,0), (0,1), (0,-1), (-1,-1), (-1,1), (1,-1), (1,1)]
        while len(dq) > 0:
            length = len(dq)
            for i in range(length):
                r, c = dq.popleft()
                count = 0
                for k, l in directions:
                    nr, nc = r+k, c+l
                    if -1 < nr < m and -1 < nc < n and (nr, nc) not in visited:
                        if board[nr][nc] == 'M':
                            count += 1
                if count > 0:
                    board[r][c] = str(count)
                else:
                    for k, l in directions:
                        nr, nc = r+k, c+l
                        if -1 < nr < m and -1 < nc < n and (nr, nc) not in visited:
                            dq.append((nr, nc))
                            visited.add((nr, nc))
                    board[r][c] = 'B'
        return board
        