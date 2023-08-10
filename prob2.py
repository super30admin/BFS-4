# Time Complexity : O(n)
# Space Complexity :O(n)
# Passed on Leetcode: yes

from collections import deque

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        target = n * n

        def get_coordinates(cell):
            # Given a cell number, return its coordinates on the board
            row = n - 1 - (cell - 1) // n
            col = (cell - 1) % n if row % 2 == (n - 1) % 2 else n - 1 - (cell - 1) % n
            return row, col

        def flatten_board(board):
            flattened = [-1]  # Indexing starts from 1
            for i in range(n - 1, -1, -1):
                if (n - 1 - i) % 2 == 0:
                    flattened.extend(board[i])
                else:
                    flattened.extend(reversed(board[i]))
            return flattened

        flattened_board = flatten_board(board)

        visited = set()
        queue = deque([(1, 0)])  # (current cell, moves taken)

        while queue:
            curr_cell, moves = queue.popleft()

            if curr_cell == target:
                return moves

            if curr_cell in visited:
                continue
            visited.add(curr_cell)

            for i in range(1, 7):
                next_cell = curr_cell + i
                if next_cell <= target:
                    row, col = get_coordinates(next_cell)
                    if flattened_board[next_cell] != -1:
                        next_cell = flattened_board[next_cell]
                    queue.append((next_cell, moves + 1))

        return -1  # If no path is found

