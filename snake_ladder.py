# The time complexity is O(n^2)  
# space complexity is O(n^2)

from collections import deque

def snakesAndLadders(board):
    n = len(board)
    def get_position(square):
        row = (square - 1) // n
        col = (square - 1) % n
        if row % 2 == 1:  # odd row, reverse column
            col = n - 1 - col
        return n - 1 - row, col

    queue = deque(¹)
    visited = {1}
    steps = 0

    while queue:
        for _ in range(len(queue)):
            curr = queue.popleft()
            if curr == n * n:
                return steps
            for next_square in range(curr + 1, min(curr + 6, n * n) + 1):
                r, c = get_position(next_square)
                if board[r][c] != -1:
                    next_square = board[r][c]
                if next_square not in visited:
                    visited.add(next_square)
                    queue.append(next_square)
        steps += 1

    return -1

