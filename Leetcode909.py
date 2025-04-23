#Time Complexity: O(n^2)
#Space Complexity: O(n^2)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
    
        def getCoords(num):
            row = (num - 1) // n
            col = (num - 1) % n
            if row % 2 == 1:
                col = n - 1 - col
            return n - 1 - row, col

        visited = set()
        queue = deque([(1, 0)])  # (square number, move count)

        while queue:
            curr, moves = queue.popleft()
            for i in range(1, 7):
                next_square = curr + i
                if next_square > n * n:
                    continue
                r, c = getCoords(next_square)
                if board[r][c] != -1:
                    next_square = board[r][c]
                if next_square == n * n:
                    return moves + 1
                if next_square not in visited:
                    visited.add(next_square)
                    queue.append((next_square, moves + 1))
        return -1
