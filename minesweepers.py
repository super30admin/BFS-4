from collections import deque
class Solution:
    def countMines(self, board, x, y, directions):
        count = 0
        for i, j in directions:
            newX, newY = x + i, y + j
            if newX < 0 or newY < 0 or newX >= len(board) or newY >= len(board[0]):
                continue
            if board[newX][newY] == "M":
                count += 1
        return count

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        directions = [[0, 1], [1, 0], [-1, 0], [0, -1], [1, 1], [-1, 1], [1, -1], [-1, -1]]
        q = deque()
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        board[click[0]][click[1]] = "B"
        q.append(click[0])
        q.append(click[1])
        while q:
            x = q.popleft()
            y = q.popleft()

            neiMines = self.countMines(board, x, y, directions)
            if neiMines > 0:
                board[x][y] = str(neiMines)
            else:
                for i, j in directions:
                    newX, newY = x + i, y + j
                    if newX < 0 or newY < 0 or newX >= len(board) or newY >= len(board[0]) or board[newX][newY] != "E":
                        continue
                    board[newX][newY] = "B"
                    q.append(newX)
                    q.append(newY)
        return board