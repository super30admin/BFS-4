class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board == None or len(board) == 0:
            return 0
        moves = self.flattenBoard(board)
        n = len(board)
        mini = 0
        queue = []
        queue.append(1)
        moves[1] = -2  # visited
        while queue:
            sz = len(queue)
            for i in range(sz):
                curr = queue.pop(0)
                if curr == n * n:
                    return mini
                for k in range(1, 7):
                    child = curr + k
                    if child > n * n:
                        break
                    if moves[child] != -2:
                        if moves[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(moves[child])
                        moves[child] = -2
            mini += 1
        return -1

    def flattenBoard(self, board):
        n = len(board)
        moves = [0 for i in range(n * n + 1)]
        even = 0
        i = n - 1
        j = 0
        idx = 1
        while i >= 0 and j >= 0:
            moves[idx] = board[i][j]
            idx += 1
            if even % 2 == 0:
                j += 1
                if j == n:
                    i -= 1
                    even += 1
                    j -= 1
            else:
                j -= 1
                if j == -1:
                    i -= 1
                    even += 1
                    j += 1
        return moves
