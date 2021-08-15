# Time Complexity : O(N^2)
# Space Complexity : O(N^2)
# Did this code successfully run on Leetcode : YES
# Any problem you faced while coding this : NO

class Solution:
    def getMine(self, board, row, col, rows, cols):
        mines = 0
        for i, j in self.directions:
            x, y = row + i, col + j
            if x >= 0 and x < rows and y >= 0 and y < cols and board[x][y] == "M":
                mines += 1
        return mines

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board:
            return board

        self.directions = [[0, 1], [1, 0], [-1, 0], [0, -1], [1, 1], [-1, -1], [1, -1], [-1, 1]]
        rows, cols = len(board), len(board[0])

        # if first click is on a mine itself
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        q = deque([click])
        board[click[0]][click[1]] = "B"

        while q:
            r, c = q.popleft()
            mineCount = self.getMine(board, r, c, rows, cols)  # get number of mines around the current coordinate
            if mineCount == 0:  # if 0 mines are in the neighborhood of the current coordinate
                for i, j in self.directions:
                    x, y = r + i, c + j
                    if x >= 0 and x < rows and y >= 0 and y < cols and board[x][y] == "E":  # if neighbor is empty
                        q.append([x, y])
                        board[x][y] = "B"
            else:  # if there are more than 0 mines are in the neighborhood of the current coordinate
                board[r][c] = str(mineCount)

        return board