#BFS
""""// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0:
            return board
        m = len(board)
        n = len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        d = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, 1], [-1, -1], [1, -1], [1, 1]]
        q = deque()
        q.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'

        while q:
            size = len(q)
            for i in range(size):
                count = 0
                curr = q.popleft()
                for k in d:
                    nr = k[0] + curr[0]
                    nc = k[1] + curr[1]
                    if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'M':
                        count += 1
                if count > 0:
                    board[curr[0]][curr[1]] = str(count)
                else:
                    for k in d:
                        nr = k[0] + curr[0]
                        nc = k[1] + curr[1]

                        if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'E':
                            board[nr][nc] = 'B'
                            q.append((nr, nc))
        return board


#DFS
""""// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""


# class Solution:
#     def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
#         if board == None or len(board) == 0:
#             return board
#         m = len(board)
#         n = len(board[0])
#         if board[click[0]][click[1]] == 'M':
#             board[click[0]][click[1]] = 'X'
#             return board
#         self.d = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, 1], [-1, -1], [1, -1], [1, 1]]
#         self.dfs(click, board, m, n)
#         return board
#
#     def dfs(self, curr, board, m, n):
#         # base
#
#         # logic
#         count = 0
#         for k in self.d:
#             nr = k[0] + curr[0]
#             nc = k[1] + curr[1]
#             if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'M':
#                 count += 1
#         if count > 0:
#             board[curr[0]][curr[1]] = str(count)
#         else:
#             board[curr[0]][curr[1]] = 'B'
#             for k in self.d:
#                 nr = k[0] + curr[0]
#                 nc = k[1] + curr[1]
#
#                 if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'E':
#                     board[nr][nc] = 'B'
#                     self.dfs([nr, nc], board, m, n)




