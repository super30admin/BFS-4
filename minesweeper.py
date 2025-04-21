"""
BFS Approach practice
TC - O(m * n)
SC - O(m * n)
"""

class Solution:
    def countMines(self, board, r, c, dirs, m, n):
        count = 0
        for _dir in dirs:
            nr = r + _dir[0]
            nc = c + _dir[1]

            if nr >= 0 and nr < m and nc >= 0 and nc < n and board[nr][nc] == 'M':
                count+= 1
        return count

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0: return board

        m = len(board)
        n = len(board[0])

        # u-d-l-r-ul-ur-ll-lr
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1, 1]]

        row = click[0]
        col = click[1]

        if board[row][col] == 'M':
            board[row][col] = 'X'
            return board

        q = deque([])

        q.append([row, col])
        # update curr row, col cell to B
        board[row][col] = 'B'

        while q:
            curr = q.popleft()

            countmines = self.countMines(board, curr[0], curr[1], dirs, m, n)

            if countmines > 0:
                board[curr[0]][curr[1]] = str(countmines)
            else:
                for _dir in dirs:
                    nr = curr[0] + _dir[0]
                    nc = curr[1] + _dir[1]

                    if nr >= 0 and  nr < m and nc >= 0 and nc < n and board[nr][nc] == 'E':
                        board[nr][nc] = 'B'
                        q.append([nr, nc])
        return board