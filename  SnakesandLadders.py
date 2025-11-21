# Time Complexity : O(N*M) where N and M are the dimensions of the board
# Space Complexity : O(N*M) where N and M are the dimensions of the board
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using BFS to traverse the board starting from cell 0.
# For each cell, I explore all possible moves (1 to 6).
# I calculate the new index and convert it to board coordinates.
# If the cell has a ladder or snake, I move to the destination cell.
# I keep track of visited cells by marking them on the board with -2.
# I increment the level after exploring all cells at the current level.
# If I reach the last cell, I return the level count as the minimum moves required.
# If I exhaust all possibilities without reaching the last cell, I return -1.

from typing import List
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        q = deque([0])
        lvl = 0
        def getId(idx):
            r = idx // n
            c = idx % n
            if r % 2 == 0:
                return n-r-1,c
            else:
                return n-r-1,n-c-1
        while q:
            for _ in range(len(q)):
                curr = q.popleft()
                for i in range(1,7):
                    nidx = curr + i
                    r,c = getId(nidx)
                    if nidx == n * n - 1 or board[r][c] == n*n:
                        return lvl + 1
                    if board[r][c] != -2:
                        if board[r][c] == -1:
                            q.append(nidx)
                        else:
                            q.append(board[r][c] - 1)
                        board[r][c] = -2
            lvl += 1
        return -1 