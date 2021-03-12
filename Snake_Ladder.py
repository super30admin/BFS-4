"""
Time Complexity : O(N^2)- where N is length of a row on the board
Space Complexity : O(N^2)- as we are flattening the 2D matrix to a 1D matrix of same size
Did this code successfully run on Leetcode : I don't have leetcode Premium. I saw the code online and just
wrote down my algo
Any problem you faced while coding this : no
To follow through the rows and columns in a proper fashion, we flatten out the 2D matrix to form a 1D array, so that we have all
the values continuously. After that, we can perform BFS. So to start with, we can put the first position in the queue. Now,
its children would be the next 6 positions (as we are rolling a die). Now, either these 6 positions have value -1, (not leading
anywhere), or they have value of the cell where they are leading through via a snake or a ladder. So, while going through its
children, if the value is -1, we just append the index to the queue, but if not, we add the new index to the queue. Simultaneously,
we mark the position visited by making it -2. Also, for this BFS question, we need to maintain size as we can check the next level only
at once and increment our moves by 1. Whenever we reach the last index, we just return the number of moves.
"""
from collections import deque


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        N = len(board)
        r = N-1
        c = 0
        boardArray = [-1] * (N*N)
        even = 0
        index = 0
        while index < N*N:
            if board[r][c] == -1:
                boardArray[index] = -1
            else:
                boardArray[index] = board[r][c] - 1
            if even % 2 == 0:
                c += 1
            else:
                c -= 1
            if c >= N:
                even += 1
                r -= 1
                c -= 1
            elif c < 0:
                even += 1
                r -= 1
                c += 1
            index += 1
        minMoves = 0
        q = deque()
        q.append(0)
        boardArray[0] = -2
        finalIndex = len(boardArray)-1
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == len(boardArray)-1:
                    return minMoves
                for j in range(1, 7):
                    value = curr+j
                    if value <= len(boardArray)-1 and boardArray[value] != -2:
                        if boardArray[value] == -1:
                            q.append(value)
                        else:
                            q.append(boardArray[value])
                        boardArray[value] = -2
            minMoves += 1
        return -1
