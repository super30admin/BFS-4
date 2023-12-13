# Time complexity: O(n^2)
# Space complexity: O(n)
# Did it run on Leetcode: yes

# first flatten the array - then do bfs - whenever we reach the goal at any level - stop
from Queue import Queue

class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n = len(board)
        arr = [-1] * (n * n)
        idx = 0
        r = n - 1
        c = 0
        even = True  # Use a boolean variable to track even/odd rows
        while idx < n * n:
            if 0 <= r < n and 0 <= c < n:
                if board[r][c] != -1:
                    arr[idx] = board[r][c] - 1
                idx += 1
            if even:
                c += 1
            else:
                c -= 1
            if c == n or c == -1:
                r -= 1
                even = not even
                c = n - 1 if even else 0

        q = Queue()
        q.put(0)
        arr[0] = -2
        moves = 0

        while not q.empty():
            size = q.qsize()
            for i in range(size):
                curr = q.get()
                for k in range(1, 7):
                    baby = curr + k
                    if baby == n * n - 1:
                        return moves + 1
                    if baby < n * n and arr[baby] != -2:
                        if arr[baby] == -1:
                            q.put(baby)
                        else:
                            q.put(arr[baby])
                        arr[baby] = -2
            moves += 1

        return -1
        