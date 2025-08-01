"""
Treat the board as a graph, where each square connects to the next 1–6 positions (as in dice rolls)
Use BFS to explore shortest paths, applying any snakes or ladders when landing on them
Keep track of visited squares to prevent cycles and redundant processing
"""
"""

Time Complexity: O(n^2)	Each square visited once
Space Complexity: O(n^2) BFS queue and visited set
"""

from collections import deque

class snakeLadder:
    def snakesAndLadders(self, board: list[list[int]]) -> int:
        n = len(board)

        def get_pos(sq):
            quot, rem = divmod(sq - 1, n)
            row = n - 1 - quot
            col = rem if quot % 2 == 0 else n - 1 - rem
            return row, col

        visited = set()
        queue = deque([(1, 0)])  # (square, moves)

        while queue:
            curr, moves = queue.popleft()
            for i in range(1, 7):
                next_sq = curr + i
                if next_sq > n * n:
                    continue
                r, c = get_pos(next_sq)
                if board[r][c] != -1:
                    next_sq = board[r][c]
                if next_sq == n * n:
                    return moves + 1
                if next_sq not in visited:
                    visited.add(next_sq)
                    queue.append((next_sq, moves + 1))

        return -1

if __name__ == "__main__":
    obj = snakeLadder()
    board = [
        [-1,-1,-1,-1,-1,-1],
        [-1,-1,-1,-1,-1,-1],
        [-1,-1,-1,-1,-1,-1],
        [-1,35,-1,-1,13,-1],
        [-1,-1,-1,-1,-1,-1],
        [-1,15,-1,-1,-1,-1]
    ]
    print(obj.snakesAndLadders(board))
