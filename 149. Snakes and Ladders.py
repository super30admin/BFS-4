from collections import deque


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0

        n = len(board)
        moves = [0] * (n * n)
        r = n - 1
        c = 0

        # flatten the matrix

        even = 0
        idx = 0  # index for moves
        while r >= 0 and c >= 0:
            if board[r][c] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[r][c] - 1
            idx += 1

            if even % 2 == 0:
                c += 1
                if c == n:
                    r -= 1
                    even += 1
                    c -= 1
            else:
                c -= 1
                if c == -1:
                    r -= 1
                    c += 1
                    even += 1
        # print(moves)
        q = deque()
        q.append(0)
        count = 0
        moves[0] = -2
        while len(q) > 0:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == (n * n) - 1:
                    return count
                for j in range(1, 7):
                    child = curr + j
                    if child >= n * n:
                        continue
                    if moves[child] != -2:
                        if moves[child] == -1:
                            q.append(child)
                        else:
                            q.append(moves[child])

                        moves[child] = -2
            count += 1
        return -1

# BFS
# Time Complexity :O(n^2)
# Space Complexity :O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
