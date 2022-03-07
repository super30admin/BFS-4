# TC:O(m*n)
# SC:O(m*n)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return -1

        n = len(board)

        moves = [-1] * (n * n)
        idx = 0
        r = n - 1
        c = 0

        while r >= 0 and c >= 0:
            moves[idx] = board[r][c]
            idx += 1
            if (n - 1 - r) % 2 == 0:
                c += 1

                if c == n:
                    c -= 1
                    r -= 1
            else:
                c -= 1

                if c == -1:
                    c += 1
                    r -= 1

        queue = []

        queue.append(0)
        moves[0] = -2
        count = 0
        while queue:
            size = len(queue)

            for i in range(0, size):
                elem = queue.pop(0)
                if elem == n * n - 1:
                    return count
                for curr in range(1, 7):
                    child = elem + curr
                    if child > n * n - 1:
                        continue
                    if moves[child] != -2:
                        if moves[child] == -1:
                            queue.append(child)
                            moves[child] = -2
                        else:
                            queue.append(moves[child] - 1)
                            moves[child] = -2

            count += 1

        return -1







