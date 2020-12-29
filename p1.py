#Time: O(mn)
#Space: O(mn)
class Solution:
    dirs = [[-1,0],[1,0],[0,-1],[0,1],[-1,-1],[1,-1],[-1,1],[1,1]]
    def updateBoard(self, board, click):
        if len(board) == 0 :
            return board 

        m , n = len(board), len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board 

        board[click[0]][click[1]] = 'B'

        q = [click]

        while len(q) != 0:
            curr = q.pop(0)
            res = self.getMines(board, curr[0], curr[1])

            if res == 0:
                for dir in self.dirs:
                    r = dir[0] + curr[0]
                    c = dir[1] + curr[1]

                    if r >= 0 and c >= 0 and r < m and c < n and board[r][c] == 'E':
                        q.append([r,c])
                        board[r][c] = 'B'
            else:
                board[curr[0]][curr[1]] = str(res)

        return board 

    def getMines(self, board, r, c):
        mines = 0 
        for dir in self.dirs:
            i = r + dir[0]
            j = c + dir[1]
            if i >= 0 and j >= 0 and i < len(board) and j < len(board[0]) and board[i][j] == 'M':
                mines += 1 

        return mines  
