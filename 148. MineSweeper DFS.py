class Solution:
    dirs = []
    m = 0
    n = 0

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        self.m = len(board)
        self.n = len(board[0])
        self.dirs = [[-1, 0], [1, 0], [0, 1], [0, -1], [-1, -1], [-1, 1], [1, -1], [1, 1]]

        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        # board[click[0]][click[1]] = "B"

        self.dfs(board, click[0], click[1])
        return board

    def dfs(self, board, i, j):
        if i < 0 or j < 0 or i == self.m or j == self.n or board[i][j] != "E":
            return
        board[i][j] = "B"
        countmines = self.countMines(board, i, j)
        if countmines > 0:
            board[i][j] = str(countmines)
        else:
            for d in self.dirs:
                r = d[0] + i
                c = d[1] + j
                self.dfs(board, r, c)

    def countMines(self, board, i, j):
        count = 0

        for d in self.dirs:
            r = d[0] + i
            c = d[1] + j

            if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == "M":
                count += 1

        return count

# DFS
# Time Complexity : O(m * n)
# Space Complexity :O(m * n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
