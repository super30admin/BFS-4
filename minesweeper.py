class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        if(board[click[0]][click[1]]) == "M":
            board[click[0]][click[1]] = "X"
            return board
        dirs = {(0, 1), (1, 0), (0, -1), (-1, 0),
                (1, 1), (-1, -1), (-1, 1), (1, -1)}
        m = len(board)
        n = len(board[0])
        q = deque([])
        q.append((click[0], click[1]))
        board[click[0]][click[1]] = "B"

        def getMines(board, row, col):
            mines = 0
            for dir in dirs:
                nr = row+dir[0]
                nc = col+dir[1]
                if(nr >= 0 and nr < m and nc >= 0 and nc < n and board[nr][nc] == "M"):

                    mines += 1
            return mines
        while q:
            curr = q.popleft()
            mines = getMines(board, curr[0], curr[1])
            if mines == 0:
                for dir in dirs:
                    r = dir[0]+curr[0]
                    c = dir[1]+curr[1]
                    if(r >= 0 and r < m and c >= 0 and c < n and board[r][c] == "E"):
                        q.append((r, c))
                        board[r][c] = "B"
            else:
                board[curr[0]][curr[1]] = str(mines)
        return board
