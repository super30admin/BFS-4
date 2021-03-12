# Approach - In line
# Time - O(N ^2)
# Space - O(N ^2) N is the board size by N * N
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        if not board or len(board) == 0:
            return -1

        n = len(board)

        # pre process data in board so it is easier to traverse instead of zig zag pattern
        flatten_board = [0] * (n*n)

        i = n - 1 # first row is at last position
        j = 0

        # For direction -- if even row go from left to right else from right to left
        even = 0 
        index = 0

        while i >= 0:

            if board[i][j] == -1:
                flatten_board[index] = -1

            else:
                flatten_board[index] = board[i][j] - 1

            index += 1

            if even % 2 == 0:
                j += 1

            else:
                j -= 1

            
            # boundaries
            if j == n:
                 # bring back j 
                i -= 1
                even += 1
                j -= 1

                
            if j < 0:
                # bring back j by inc 1
                i -= 1
                even += 1
                j += 1

        queue = deque()
        # add index to queue
        queue.append(0) # we start rolling from 0th position, mark it visited
        flatten_board[0] = -2 # to make it visited make it -2
        moves = 0

        while queue:

            size = len(queue)

            for i in range(size):

                current_index = queue.popleft()

                # check if we already are at n*n - 1 position return 
                if current_index == n*n -1:
                    return moves

                # roll possibilities are 1 to 6 
                for j in range(1, 7):
                    next_index = current_index + j

                    # as long as we dont exceed board's last val and not visted(could be -1 or other value)
                    if next_index < n*n and flatten_board[next_index] != -2:

                        if flatten_board[next_index] == -1:
                            queue.append(next_index)

                        else:
                            # if it is a value get the index from flatten board
                            queue.append(flatten_board[next_index])

                        # either way mark visited
                        flatten_board[next_index] = -2

            moves += 1

        return -1