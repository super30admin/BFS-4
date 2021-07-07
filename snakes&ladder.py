class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0
        n = len(board)
        grid = [0] * (n * n)
        idx = 0
        i = n - 1
        j = 0
        even = 0
        q = deque([])
        moves = 0
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                grid[idx] = -1
            else:
                grid[idx] = board[i][j] - 1
            if even % 2 == 0:
                j += 1
            else:
                j -= 1
            if j == n:
                i -= 1
                even += 1
                j -= 1
            if j < 0:
                i -= 1
                even += 1
                j += 1
            idx += 1
        q.append(0)
        grid[0] = -2
        while q:
            size = len(q)
            for k in range(size):
                curr = q.popleft()
                if curr == (n * n) - 1:
                    return moves
                for l in range(1, 7):
                    pos = curr + l
                    if pos < n * n and grid[pos] != -2:
                        if grid[pos] == -1:
                            q.append(pos)
                            grid[pos] = -2
                        else:
                            q.append(grid[pos])
                            grid[pos] = -2
            moves += 1
        return -1
