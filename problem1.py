# TC:O(m*n)
# SC:O(m*n)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board

        self.m = len(board)
        self.n = len(board[0])

        self.dirs = [[0, 1], [1, 0], [-1, 0], [0, -1], [-1, -1], [1, 1], [-1, 1], [1, -1]]

        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        queue = []
        queue.append([click[0], click[1]])
        board[click[0]][click[1]] = "B"

        while queue:
            elem = queue.pop(0)
            count = self.isMine(board, elem[0], elem[1])
            if count > 0:
                board[elem[0]][elem[1]] = chr(count + ord("0"))

            else:
                for di in self.dirs:
                    nr = di[0] + elem[0]
                    nc = di[1] + elem[1]

                    if 0 <= nr < self.m and 0 <= nc < self.n and board[nr][nc] == "E":
                        board[nr][nc] = "B"
                        queue.append([nr, nc])

        return board

    def isMine(self, board, r, c):
        count = 0

        for di in self.dirs:
            nr = di[0] + r
            nc = di[1] + c
            if 0 <= nr < self.m and 0 <= nc < self.n and board[nr][nc] == "M":
                count += 1

        return count


