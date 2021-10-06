"""
Time Complexity : O(n ^ 2) where n is the length of the board
Space Complexity : O(n ^ 2) where n is the length of the board
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if len(board) == 0:
            return -1
        n = len(board)
        moves = [0]*(n*n)
        i = n - 1
        j = 0
        index = 0
        even = 0
        # Flattening out the board and decrementing the numerical values given as we
        # start our index from 0
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                moves[index] = board[i][j]
            else:
                moves[index] = board[i][j] - 1
            index += 1
            if even % 2 == 0:
                j += 1
                if j == n:
                    j -= 1
                    even += 1
                    i -= 1
            else:
                j -= 1
                if j < 0:
                    j += 1
                    even += 1
                    i -= 1
        q = deque([])
        moveCount = 0
        q.append(0)
        moves[0] = -2
        # Performing a BFS and recording all the visited places in the moves array 
        # itself for simplicity
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == n * n - 1: return moveCount
                for k in range(1, 7):
                    pos = curr + k
                    if pos > n * n - 1: break
                    if moves[pos] != -2:
                        if moves[pos] == -1:
                            moves[pos] = -2
                            q.append(pos)
                        else:
                            q.append(moves[pos])
                            moves[pos] = -2
            moveCount += 1
        return -1