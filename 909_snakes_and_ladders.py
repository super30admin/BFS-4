"""
Leetcode: https://leetcode.com/problems/snakes-and-ladders/
Approach: Breadth-First Search (queue)
Time Complexity: O(N^2), where N is the length of the board.
Space Complexity: O(N^2).
"""

from collections import deque


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board or len(board) is 0:
            return 0

        min_moves = 0
        r = len(board)

        n = r * r

        moves = [0] * n # converting the grid to 1D array, for easy computation

        idx = 0
        even = 0
        row = r - 1 #starting point in the grid
        col = 0

        while (row >= 0 and col >= 0):

            if board[row][col] == -1:
                moves[idx] = -1

            else:
                moves[idx] = board[row][col] - 1 # if not 1, put the number - 1 (as index is 1 less than value)
            idx += 1

            if even % 2 == 0: # for iterating over the grid in zig-zag manner
                col += 1
            else:
                col -= 1

            if col == r:
                row -= 1
                even += 1
                col -= 1

            if col < 0:
                row -= 1
                even += 1
                col += 1

        q = deque() # bfs

        q.append(0)
        moves[0] = -2  # visited

        while q:
            size = len(q)

            for i in range(size):
                cur = q.popleft()

                if cur == n-1: # if the end is reached
                    return min_moves
                for k in range(1,7): # for every child for the current position
                    child = cur + k

                    if child < n and moves[child] != -2:
                        if moves[child] == -1:
                            q.append(child)
                        else:
                            q.append(moves[child])
                        moves[child] = -2
            min_moves += 1  # at each level increase the number of moves

        return -1