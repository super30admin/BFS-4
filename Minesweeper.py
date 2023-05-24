class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        # Time O(mn)
        # Space O(1)
        n = len(board)
        m = len(board[0])
        r = click[0]
        c = click[1]
        if board[r][c] == 'M':
            board[r][c] = 'X'
            return board

        dirs = [[-1, -1], [0, -1], [1, -1], [-1, 0], [1, 0], [-1, 1], [0, 1], [1, 1]]
        dq = deque()
        dq.append((r, c))
        board[r][c] = 'B'
        while dq:
            r, c = dq.popleft()
            count = 0
            for dirr in dirs:
                nr = r + dirr[0]
                nc = c + dirr[1]
                if nr >= 0 and nr < n and nc >= 0 and nc < m and board[nr][nc] == 'M':
                    count += 1
            if count > 0:
                board[r][c] = str(count)
            else:
                board[r][c] = 'B'
                for dirr in dirs:
                    nr = r + dirr[0]
                    nc = c + dirr[1]
                    if nr >= 0 and nr < n and nc >= 0 and nc < m and board[nr][nc] == 'E':
                        dq.append((nr, nc))
                        board[nr][nc] = 'B'

        return board