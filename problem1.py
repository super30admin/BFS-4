# Space Complexity: O(M * N)
# Time Complexity: O(M * N)
class Solution:

    def countmines(self, board, i, j, dirs):
        re = 0
        m,n = len(board), len(board[0])

        for d in dirs: 
            nr = i + d[0]
            nc = j + d[1]

            if nr >= 0 and nc >=0 and nr < m and nc < n and board[nr][nc] == 'M':
                re+=1
        return re

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m,n = len(board), len(board[0])
        if(board[click[0]][click[1]] == 'M'):
            board[click[0]][click[1]] = 'X'
            return board

        q = collections.deque()

        q.append(click)
        board[click[0]][click[1]] = 'B'
        dirs = [(-1,0), (1,0), (0,1), (0,-1), (-1,1), (1,1), (-1,-1), (1, -1)]

        while q:
            curr = q.popleft()
            cnt = self.countmines(board, curr[0], curr[1], dirs)
            if cnt == 0:
                for d in dirs:
                    nr = curr[0] + d[0]
                    nc = curr[1] + d[1]
                    if nr >= 0 and nc >=0 and nr < m and nc < n and board[nr][nc] == 'E':
                        q.append((nr, nc))
                        board[nr][nc] = 'B'
            else:
                board[curr[0]][curr[1]] = str(cnt)

        return board






