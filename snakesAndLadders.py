# Time Complexity : O(v+e) where v is the vertices of the graph and e is the edges
# Space Complexity : O(v+e)
# Did this code successfully run on Leetcode : Yes
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        m, n = len(board), len(board[0])
        grid = {}
        visited = set()

        counter = 1
        p = 1
        for i in range(m-1, -1, -1):
            if p == 1:
                for j in range(n):
                    grid[counter] = (i, j)
                    counter+=1
                    p = -1
            elif p == -1:
                for j in range(n-1, -1, -1):
                    grid[counter] = (i, j)
                    counter+=1
                    p = 1

        from collections import deque
        q = deque([(1, n-1, 0, 0)])
        visited.add((n-1,0))

        while q:
            cur_val, new_r, new_c, steps = q.popleft()

            if cur_val == n**2:
                return steps

            for dice in range(1, min(7, n**2+1)):
                val = cur_val + dice

                if val > n**2:
                    val = n**2

                r, c = grid[val]

                if board[r][c] != -1:
                    val = board[r][c]
                    r, c = grid[val]

                if (r, c) not in visited:
                    q.append((val, r, c, steps+1))
                    visited.add((r, c))

        return -1
