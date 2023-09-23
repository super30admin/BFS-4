# Time Complexity: O(m * n)
# Space Complexity: O(m * n)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        dirs = [(-1, -1), (-1, 0), (-1, 1), (0, -1),
            (0, 1), (1, -1), (1, 0), (1, 1)]
        def getMinesCount(i, j):
            minesCount = 0
            for dx, dy in dirs:
                x = i + dx
                y = j + dy
                if x < 0 or x == len(board) or y < 0 or y == len(board[0]):
                    continue
                if board[x][y] == 'M':
                    minesCount += 1
            return minesCount

        def dfs(i, j):
            if i < 0 or i == len(board) or j < 0 or j == len(board[0]):
                return
            if board[i][j] != 'E':
                return
            minesCount = getMinesCount(i, j)
            board[i][j] = 'B' if minesCount == 0 else str(minesCount)
            if minesCount == 0:
                for dx, dy in dirs:
                    dfs(i + dx, j + dy)
        dfs(click[0], click[1])
        return board