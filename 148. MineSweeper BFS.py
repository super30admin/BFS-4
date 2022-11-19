from collections import deque


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

        board[click[0]][click[1]] = "B"

        q = deque()
        q.append([click[0], click[1]])

        while len(q) > 0:
            curr = q.popleft()
            countmines = self.countMines(board, curr[0], curr[1])

            if countmines > 0:
                board[curr[0]][curr[1]] = str(countmines)
            else:
                for d in self.dirs:
                    r = d[0] + curr[0]
                    c = d[1] + curr[1]

                    if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == "E":
                        q.append([r, c])
                        board[r][c] = "B"
        return board

    def countMines(self, board, i, j):
        count = 0

        for d in self.dirs:
            r = d[0] + i
            c = d[1] + j

            if r >= 0 and c >= 0 and r < self.m and c < self.n and board[r][c] == "M":
                count += 1

        return count

    # BFS
# Time Complexity : O(m * n)
# Space Complexity :O(m * n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
