#Time Complexity :O(N^2)
#Space Complexity :O(N^2)
#Did this code successfully run on Leetcode :Yes
#Any problem you faced while coding this :No

from ast import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        visited = set()
        queue = [(1, 0)]  

        while queue:
            position, moves = queue.pop(0)
            if position == n * n:
                return moves

            for i in range(1, 7):
                next_pos = position + i
                if next_pos > n * n:
                    break
                row, col = self.get_coordinates(next_pos, n)
                if board[row][col] != -1:
                    next_pos = board[row][col]
                if next_pos not in visited:
                    visited.add(next_pos)
                    queue.append((next_pos, moves + 1))

        return -1

    def get_coordinates(self, pos, n):
        row = n - 1 - (pos - 1) // n
        col = (pos - 1) % n
        if (n - 1 - row) % 2 == 1:
            col = n - 1 - col
        return row, col