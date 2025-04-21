"""
TC - O(n^2)
SC - O(n^2)
"""


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)

        arr = [0]
        for i in range(n):
            if i % 2 == 0:
                arr += board[n - i - 1]
            else:
                arr += board[n - i - 1][::-1]

        dist = [-1] * len(arr)
        dist[1] = 0

        q = deque([1])
        while q:
            square = q.popleft()

            l = square + 1
            r = min(square + 6, n ** 2)

            for i in range(l, r + 1):
                next_square = i if arr[i] == -1 else arr[i]
                if dist[next_square] == -1:
                    dist[next_square] = dist[square] + 1
                    q.append(next_square)

        return dist[n * n]