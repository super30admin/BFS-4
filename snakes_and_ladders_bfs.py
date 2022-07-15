# Time Complexity : O(N*N) where N is dimension of the board
# Space Complexity : O(N*N) where N is dimension of the board
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

from collections import deque

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        ROWS, COLS = len(board), len(board[0])
        if ROWS == 0: return 0
        
        oneDArray = [-1 for _ in range(ROWS * COLS)]
        even = 0
        r = ROWS - 1
        c = 0
        for i in range(ROWS * COLS):
            if board[r][c] != -1:
                oneDArray[i] = board[r][c] - 1
            if even % 2 == 0:
                c += 1
            else:
                c -= 1
                
            if c >= COLS:
                c = COLS - 1
                r -= 1
                even += 1
            elif c < 0:
                c = 0
                r -= 1
                even += 1
            
        q = deque()
        oneDArray[0] = -2
        q.appendleft(0)
        minMoves = 0
        while len(q) > 0:
            size = len(q)
            
            for _ in range(size):
                pos = q.pop()
                if pos == (ROWS * COLS) - 1:
                    return minMoves
                for m in range(1,7):
                    nextPos = m + pos
                    if nextPos < ROWS * COLS and oneDArray[nextPos] != -2:
                        if oneDArray[nextPos] < 0:
                            q.appendleft(nextPos)
                        else:
                            q.appendleft(oneDArray[nextPos])
                            
                        oneDArray[nextPos] = -2
                        
            minMoves += 1

        return -1