# Time Complexity : O(N*M) where N and M are the dimensions of the board
# Space Complexity : O(N*M) where N and M are the dimensions of the board
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using BFS to traverse the board starting from the clicked cell.
# If the clicked cell is a mine, we change it to 'X' and return the board.
# If it's an empty cell, we explore its neighbors. We count the number of adjacent mines.
# If there are adjacent mines, we update the cell with the count.
# If there are no adjacent mines, we mark it as 'B' and add its neighbors to the queue for further exploration. 


from typing import List
from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        n = len(board)
        m = len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        q = deque([(click[0],click[1])])
        board[click[0]][click[1]] = 'B'
        directions = [(0,1),(1,0),(-1,0),(0,-1),(-1,-1),(1,1),(1,-1),(-1,1)]
        while q:
            for _ in range(len(q)):
                r,c = q.popleft()
                count = 0
                for dr,dc in directions:
                    nr = dr + r
                    nc = c + dc
                    if 0 <= nr < n and 0 <= nc < m and board[nr][nc] == 'M':
                        count += 1
                if count > 0:
                    board[r][c] = str(count)
                else:
                    for dr,dc in directions:
                        nr = dr + r
                        nc = c + dc
                        if 0 <= nr < n and 0 <= nc < m and board[nr][nc] == 'E':
                            q.append((nr,nc))
                            board[nr][nc] = 'B'
        return board
                    



