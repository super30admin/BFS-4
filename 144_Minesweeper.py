'''
Accepted on leetcode(529)
time -O(M*N)
space - O(1), recursive stack
Approach: use dfs to traverse the whole matrix using recursive stack to fill/change values in matrix.
'''


class Solution:
    def __init__(self):
        self.dirs = [[0, 1], [1, 0], [1, 1], [-1, -1], [-1, 0], [0, -1], [1, -1], [-1, 1]]

    def updateBoard(self, board, click):
        n = len(board)
        m = len(board[0])

        i = click[0]
        j = click[1]

        # condition 1:
        if board[i][j] == 'M':
            board[i][j] = 'X'
            return board

        # call dfs here
        self.dfs(board, i, j)
        return board

    def dfs(self, board, i, j):
        n = len(board)
        m = len(board[0])

        # base case
        if i < 0 or i >= n or j < 0 or j >= m or board[i][j] != 'E':
            return

            # get the count of mines
        mines = self.getMines(board, i, j)

        # condition 3:
        if mines == 0:
            board[i][j] = 'B'
            for x in range(len(self.dirs)):
                row = self.dirs[x][0] + i
                col = self.dirs[x][1] + j

                self.dfs(board, row, col)
        # condition 2:
        else:
            board[i][j] = str(mines)  # convert int to string

    def getMines(self, board, i, j):
        count = 0
        n = len(board)
        m = len(board[0])

        for x in range(len(self.dirs)):
            row = self.dirs[x][0] + i
            col = self.dirs[x][1] + j
            if row < 0 or row >= n or col < 0 or col >= m or board[row][col] != 'M':
                continue;
            count += 1

        return count
