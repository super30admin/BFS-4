# Time Complexity : O(MN)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using BFS Approach

from collections import deque


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return None

        queue = deque([])
        r = len(board)
        moves = [-1] * (r * r)
        i = r - 1
        j = 0
        direction = 1
        index = 0
        while i >= 0 and j < len(board):
            if board[i][j] != -1:
                moves[index] = board[i][j] - 1
            index += 1
            if direction == 1:
                j += 1
            else:
                j -= 1
            if j >= len(board):
                i -= 1
                j -= 1
                direction = -1
            if j < 0:
                i -= 1
                j += 1
                direction = 1

        queue.append(0)
        moves[0] = -2
        minMoves = 0
        while queue:
            for i in range(len(queue)):
                curr = queue.popleft()
                if curr == len(moves) - 1:
                    return minMoves
                for j in range(1, 7):
                    child = curr + j
                    if child < len(moves) and moves[child] != -2:
                        if moves[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(moves[child])
                        moves[child] = -2

            minMoves += 1
        return -1
