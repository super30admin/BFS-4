# Time Complexity: O(n2)
# Space Complexity: O(n2)
from collections import deque
from typing import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        n = len(board)
        moves = [0] * (n * n)
        i = n - 1
        j = 0

        even = 0
        index = 0

        while (i >= 0 and j >= 0):

            if board[i][j] == -1:
                moves[index] = -1
            else:
                moves[index] = board[i][j] - 1

            index += 1

            if even % 2 == 0:
                j += 1
                #                 if we reach to end
                if j == n:
                    i -= 1
                    even += 1
                    j -= 1
            else:
                j -= 1
                if j < 0:
                    i -= 1
                    even += 1
                    j += 1

        queue = deque()
        queue.append(0)
        moves[0] = -2
        minMoves = 0

        while queue:
            size = len(queue)

            for i in range(size):
                curr = queue.popleft()

                if curr == (n * n) - 1:
                    return minMoves

                for j in range(1, 7):
                    child = curr + j

                    if child > (n * n) - 1:
                        break

                    if moves[child] != -2:
                        if moves[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(moves[child])

                        moves[child] = -2

            minMoves += 1

        return -1


