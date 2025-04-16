'''
    Time Complexity: O(m*n)
    Space Complexity: O(m*n)
'''
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        m = len(board)
        n = len(board[0])
        directions = [(-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1)]
        queue = deque()
        board[click[0]][click[1]] = "B"
        queue.append((click[0], click[1]))

        while len(queue):
            cellx, celly = queue.popleft()
            neighborMines = 0
            emptyNeighbors = []

            for dir in directions:
                row = cellx + dir[0]
                col = celly + dir[1]

                if 0 <= row < m and 0 <= col < n:
                    if board[row][col] == "M":
                        neighborMines += 1
                    elif board[row][col] == "E" and not neighborMines:
                        emptyNeighbors.append((row, col))

            if neighborMines:
                board[cellx][celly] = str(neighborMines)
            else:
                for neighbor in emptyNeighbors:
                    board[neighbor[0]][neighbor[1]] = "B"
                    queue.append((neighbor[0], neighbor[1]))

        return board

