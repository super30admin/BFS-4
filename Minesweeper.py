class Solution:

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board[0]) == 0: return None

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'B'
            return board

        self.dirs = [[1, 0], [0, 1], [-1, 0], [0, -1], [1, 1], [-1, -1], [1, -1], [-1, 1]]
        self.m = len(board)
        self.n = len(board[0])

        queue = deque()
        queue.append([click[0], click[1]])

        board[click[0]][click[1]] = 'B'

        while queue.__len__() > 0:
            size = queue.__len__()

            for id in range(size):
                samplenode = queue.popleft()

                r = samplenode[0]
                c = samplenode[1]
                mines = self.getmines(r, c, board)

                if mines == 0:
                    for dir in self.dirs:
                        row = r + dir[0]
                        col = c + dir[1]
                        if (row < self.m and col < self.n and row >= 0 and col >= 0 and board[row][col] == 'E'):
                            queue.append([row, col])
                            board[row][col] = 'X'
                else:

                    board[r][c] = str(mines)

        return board

    def getmines(self, row, column, board):

        count = 0

        for d in self.dirs:
            r = row + d[0]
            c = column + d[1]
            if r < self.m and c < self.n and r >= 0 and c >= 0 and board[r][c] == 'M':
                count += 1

        return count
