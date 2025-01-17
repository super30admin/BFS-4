"""
TC: O(m *n) 
SP: O(m*n)
start reveling cells if there is mine in the neighbor don't explore the neighbors
"""

class Solution:

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        def countMines(r, c):
            num_mines = 0
            for dr, dc in neighbors:
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == "M":
                    num_mines += 1
            return num_mines

        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        neighbors = [
            (0, 1),
            (1, 0),
            (0, -1),
            (-1, 0),
            (-1, -1),
            (-1, 1),
            (1, -1),
            (1, 1),
        ]
        m, n = len(board), len(board[0])
        q = deque([click])
        board[click[0]][click[1]] = "B"
        while q:
            for _ in range(len(q)):
                r, c = q.popleft()
                num_mines = countMines(r, c)
                if num_mines:
                    board[r][c] = str(num_mines)
                else:
                    for dr, dc in neighbors:
                        nr, nc = r + dr, c + dc
                        if 0 <= nr < m and 0 <= nc < n and board[nr][nc] != "B":
                            q.append((nr, nc))
                            board[nr][nc] = "B"
        return board
