# Time Complexity: O(n^2), where n = rows = cols
# Space Complexity: O(n^2)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board or len(board) == 0:
            return 0

        n = len(board)

        # Convert into 1D array and fill the corresponding elements in grid
        grid = [0 for x in range(n * n)]
        i = n-1
        j = 0
        idx = 0    # grid index
        even = 0
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                grid[idx] = idx
            else:
                grid[idx] = board[i][j] - 1

            # Increment or decrement j based on direction
            if even % 2 == 0:
                j += 1
            else:
                j -= 1

            # j reaches boundary
            if j == n:
                i -= 1
                j -= 1
                even += 1

            if j < 0:
                i -= 1
                j += 1
                even += 1

            idx += 1

        q = deque()
        q.append(grid[0])
        moves = 0
        while q:
            size = len(q)
            for k in range(size):
                curr = q.popleft()
                if curr == n * n - 1:
                    return moves

                # Generate 6 possible destinations
                for l in range(1, 7):
                    pos = curr + l
                    # If not visited and pos doesn't go out of bounds
                    if pos < len(grid) and grid[pos] != -2:
                        q.append(grid[pos])
                        grid[pos] = -2

            moves += 1

        return -1