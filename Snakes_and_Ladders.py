# Time Complexity : O(n^2), where n is the number of rows (or columns) in the board
# Space Complexity : O(n^2)
from collections import deque
from typing import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        flat_board = [0]
        for i in range(n-1, -1, -1):
            row = board[i]
            if (n - i) % 2 == 0:
                row = row[::-1]
            flat_board += row
        
        queue = deque([(1, 0)])
        visited = set([1])
        
        while queue:
            curr, steps = queue.popleft()
            if curr == n*n:
                return steps
            for i in range(1, 7):
                next_pos = curr + i
                if next_pos > n*n:
                    break
                if next_pos in visited:
                    continue
                visited.add(next_pos)
                if flat_board[next_pos] != -1:
                    next_pos = flat_board[next_pos]
                queue.append((next_pos, steps+1))
        return -1