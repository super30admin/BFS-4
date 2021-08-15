# Time Complexity : O(m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

#use bfs to fill all the cells if mine is present then count of adjancent mines is considered
from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        q = collections.deque([click])
        dirs = [[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        while q:
            row, col = q.popleft()
            if board[row][col] == 'M':
                board[row][col] = 'X'
            else:
                count = 0
                for d in dirs:
                    r, c = d[0]+row,d[1]+col
                    if not (0 <= r < len(board)) or not (0 <= c < len(board[r])):
                            continue
                    if board[r][c] == 'M' or board[r][c] == 'X':
                        count += 1

                if count:
                    board[row][col] = chr(count + ord('0'))
                else:
                    board[row][col] = 'B'
                    for d in dirs:
                        r, c = d[0]+row,d[1]+col
                        if not (0 <= r < len(board)) or not (0 <= c < len(board[r])):
                                continue
                        if board[r][c] == 'E':
                                q.append((r, c))
                                board[r][c] = ' '

        return board
            
        
        
        
        