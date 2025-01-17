"""
TC: O(n*n)
SP: O(n*n)
traverse through board and convert to 1d array and perform BFS with all 6 possible dice outcomes.

"""
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        board_1d = [0]
        n = len(board)
        columns = [c for c in range(0, n)]
        for i in range(n - 1, -1, -1):
            for j in columns:
                board_1d.append(board[i][j])
            columns.reverse()
        q = deque([1])
        level = 0
        while q:
            for i in range(len(q)):
                curr = q.popleft()
                if curr == n * n:
                    return level
                for j in range(curr + 1, min(curr + 6, n * n) + 1):
                    if board_1d[j] != -2:
                        if board_1d[j] != -1:
                            q.append(board_1d[j])
                        else:
                            q.append(j)
                        board_1d[j] = -2

            level += 1
        return -1
