# Time Complexity : O(6*n^2) --> n^2
# Space Complexity : O(n^2)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#

from collections import deque
from typing import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        oneD = [-1] * (n * n)
        start = [n - 1, 0]
        flag = True
        ptr = 0
        while start[0] >= 0 and start[1] >= 0:
            oneD[ptr] = board[start[0]][start[1]] - 1 if board[start[0]][start[1]] != -1 else board[start[0]][start[1]]
            ptr += 1
            if not flag:
                start[1] -= 1
                if start[1] < 0:
                    start[0] -= 1
                    start[1] = 0
                    flag = not flag
            else:
                start[1] += 1
                if start[1] > n - 1:
                    start[0] -= 1
                    start[1] = n - 1
                    flag = not flag
        queue = deque()
        queue.append(0)
        moves = 0
        while queue:
            size = len(queue)
            for i in range(size):
                pop = queue.popleft()
                for count in range(1, 7):
                    if oneD[pop + count] != 'a':
                        if oneD[pop + count] != -1:
                            queue.append(oneD[pop + count])
                        else:
                            queue.append(pop + count)
                        if pop + count == (n * n - 1) or oneD[pop + count] == (n * n - 1):
                            return moves + 1
                        oneD[pop + count] = 'a'
            moves += 1
        return -1


print(Solution().snakesAndLadders(
    [[-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, 35, -1, -1, 13, -1],
     [-1, -1, -1, -1, -1, -1], [-1, 15, -1, -1, -1, -1]]))
