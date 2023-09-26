class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        i, j = click
        if board[i][j] == 'M':
            board[i][j] = 'X'
            return board

        M, N = len(board), len(board[0])
        offsets = [(0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
        queue = deque([(i, j)])
        C = 0
        while True:
            while queue:
                new_queue = deque()
                i, j = queue.popleft()

                if board[i][j] == 'M':
                    continue

                if board[i][j] != 'E':
                    continue

                adj_mines = 0
                for di, dj in offsets:
                    i2, j2 = i + di, j + dj

                    if i2 < 0 or i2 == M or j2 < 0 or j2 == N:
                        continue

                    if board[i2][j2] == 'M':
                        adj_mines += 1
                    
                    new_queue.append((i2, j2))
    
                if adj_mines > 0:
                    board[i][j] = str(adj_mines)
                    continue
                else:
                    queue += new_queue

                board[i][j] = 'B'
            return board