# 529. Minesweeper
# https://leetcode.com/problems/minesweeper/

# Logic: We start BFS from the click cell given to us. We add all the 
# neighbours if the current mine count is 0 and neighbour is not explored.

# Time Complexity: O(n*m)
# Space Complexity: O(min(n,m))

from collections import deque
class Solution:
    def countBombs(self, board, row, col, dirs):
        n = len(board)
        m = len(board[0])
        count = 0
        
        for _dir in dirs:
            newRow = row + _dir[0]
            newCol = col + _dir[1]

            if 0 <= newRow < n and 0 <= newCol < m and board[newRow][newCol] == 'M':
                count += 1
        return count

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        n = len(board)
        m = len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        q = deque()
        dirs = [(1,0), (-1,0), (0,1), (0,-1), (1,1), (1,-1), (-1,-1), (-1,1)]

        q.append(click)
        board[click[0]][click[1]] = 'B'

        while q:
            row, col = q.popleft()

            mines = self.countBombs(board, row, col, dirs)
            if mines != 0:
                board[row][col] = str(mines)
            else:
                for _dir in dirs:
                    newRow = row + _dir[0]
                    newCol = col + _dir[1]

                    if 0 <= newRow < n and 0 <= newCol < m and board[newRow][newCol] in ['E', 'M']:
                        board[newRow][newCol] = 'B'
                        q.append([newRow, newCol])
            
        return board
