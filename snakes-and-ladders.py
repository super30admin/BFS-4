# Time Complexity: O(n*n)
# Space Complexity: O(n*n)
class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n = len(board)

        # Edge case
        if not board:
            return 0

        # Flatten the board to a 1D array
        moves = [-1] * (n*n)
        i, j = n-1, 0  # i, j keep track of board index, start at bottom left
        even = 0  # Keep track of direction of movement
        idx = 0  # Index in moves array

        while idx < n*n:
            if board[i][j] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[i][j] - 1
            # if the row is even, move left to right
            if even % 2 == 0:
                j += 1
            else:  # move right to left
                j -= 1

            # Move to the next row
            if j >= n:
                even += 1
                i -= 1
                j -= 1
            elif j < 0:
                even += 1
                i -= 1
                j += 1
            idx += 1

        # Queue for BFS
        q = collections.deque()
        min_level = 0

        # Start from first index
        q.append(0)
        # Mark index as visited
        moves[0] = -2

        while q:
            # Maintain size, we have to process all nodes of a level at once
            size = len(q)
            for _ in range(size):
                curr = q.popleft()
                # Reached destination, return level
                if curr == (n*n) - 1:
                    return min_level

                # From each index, we can advance 1 to 6 spaces forward
                for v in range(1, 7):
                    next_move = curr + v
                    if next_move < n*n and moves[next_move] != -2:
                        if moves[next_move] == -1:
                            q.append(next_move)
                        else:  # Snake or ladder, next move index will be the value stored at index
                            q.append(moves[next_move])
                        # Mark visited
                        moves[next_move] = -2
            # Advance level
            min_level += 1

        return -1
