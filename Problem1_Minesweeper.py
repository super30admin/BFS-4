# Time Complexity: O(mn), where - rows, n - cols
# Space Complexity: O(mn)

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board or len(board) == 0:
            return board

        # Rule 1 - If a mine is revealed, the game is over
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        self.rows = len(board)
        self.cols = len(board[0])
        self.dirs = [[0, 1], [1, 0], [0, -1], [-1, 0],
                     [1, 1], [-1, -1], [-1, 1], [1, -1]]

        q = deque()
        q.append([click[0], click[1]])
        board[click[0]][click[1]] = 'B'

        while q:
            curr = q.popleft()
            mines = self.get_mines(board, curr[0], curr[1])

            # Rule 2 - If no adjacent mines is revealed
            if mines == 0:
                for d in self.dirs:
                    r = curr[0] + d[0]
                    c = curr[1] + d[1]
                    if 0 <= r < self.rows and 0 <= c < self.cols and board[r][c] == 'E':
                        q.append([r, c])
                        board[r][c] = 'B'
            # Rule 3 - If at least one adjacent mine is revealed
            else:
                board[curr[0]][curr[1]] = str(mines)

        return board

    def get_mines(self, board, row, col) -> int:
        mines = 0
        for d in self.dirs:
            r = row + d[0]
            c = col + d[1]
            if 0 <= r < self.rows and 0 <= c < self.cols and board[r][c] == 'M':
                mines += 1
        return mines
