#BFS
""""// Time Complexity : O(6*n^2)~O(n^2)
// Space Complexity :O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [0] * (n * n)
        r = n - 1
        c = 0
        idx = 0
        parity = 0
        while idx < n * n:
            if board[r][c] < 1:
                arr[idx] = board[r][c]
            else:
                arr[idx] = board[r][c] - 1
            idx += 1
            if parity % 2 == 0:
                c += 1
                if c == n:
                    c -= 1
                    r -= 1
                    parity += 1

            else:
                c -= 1
                if c == -1:
                    c += 1
                    r -= 1
                    parity += 1
        print(arr)

        q = deque()
        q.append(0)
        arr[0] = -2
        count = 0
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == n * n - 1:
                    return count
                for j in range(1, 7):
                    idx = curr + j
                    if idx > n * n - 1:
                        break
                    if arr[idx] != -2:
                        if arr[idx] == -1:
                            q.append(idx)
                        else:
                            q.append(arr[idx])
                        arr[idx] = -2
            count += 1
        return -1















