class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board

        self.m = len(board)
        self.n = len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        self.dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [1, -1], [-1, 1], [1, 1], [-1, -1]]

        queue = []
        queue.append([click[0], click[1]])
        board[click[0]][click[1]] = 'B'

        while queue:
            curr = queue.pop(0)
            mines = self.getSurrMines(board, curr[0], curr[1])
            if mines != 0:
                board[curr[0]][curr[1]] = str(mines)
            else:
                for dirr in self.dirs:
                    nr = dirr[0] + curr[0]
                    nc = dirr[1] + curr[1]
                    if nr >= 0 and nc >= 0 and nr < self.m and nc < self.n and board[nr][nc] == 'E':
                        queue.append([nr, nc])
                        board[nr][nc] = 'B'
        return board

    def getSurrMines(self, board, i, j):
        mines = 0
        for dirr in self.dirs:
            nr = dirr[0] + i
            nc = dirr[1] + j
            if nr >= 0 and nc >= 0 and nr < self.m and nc < self.n and board[nr][nc] == 'M':
                mines += 1
        return mines