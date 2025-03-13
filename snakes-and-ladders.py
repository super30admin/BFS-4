class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # tc O(n^2), sc O(n^2).
        length = len(board)
        last_square = length * length
        
        board.reverse()
        def int_to_pos(square):
            row = (square - 1) // length
            column = (square - 1) % length
            if row % 2 != 0:
                column = length - 1 - column
            return row, column
        
        queue = deque([1])
        moves = 0
        visited = set()
        visited.add(1)

        while queue:
            print(queue)
            for _ in range(len(queue)):
                curr_square = queue.popleft()
                for next_square_diff in range(1, 7):
                    next_square = curr_square + next_square_diff
                    if 0 < next_square <= last_square:
                        row, column = int_to_pos(next_square)
                        print(curr_square, next_square, row, column)
                        next_square_value = board[row][column]
                        if next_square_value != -1:
                            next_square = next_square_value
                
                        if next_square == last_square:
                            return moves + 1
                        if next_square not in visited:
                            visited.add(next_square)
                            queue.append(next_square)
            moves += 1
        return -1