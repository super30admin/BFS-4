# Time Complexity: O(m*n)
# Space Complexity: O(m*n)
# BFS Approacch
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        dirs = [(-1,0), (-1,-1), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)]
        m, n = len(board), len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        q = deque()
        q.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'

        while q:
            i, j = q.popleft()
            count = sum(1 for dr, dc in dirs if 0 <= i+dr < m and 0 <= j+dc < n and board[i+dr][j+dc] == 'M')
            if count == 0:
                for dr, dc in dirs:
                    r, c = i + dr, j + dc
                    if 0 <= r < m and 0 <= c < n and board[r][c] == 'E':
                        q.append((r, c))
                        board[r][c] = 'B'
            else:
                board[i][j] = str(count)

        return board

# DFS Approacch
# class Solution:
#     def updateBoard(self, board: list[list[str]], click: list[int]) -> list[list[str]]:
#         dirs = [(-1,0), (-1,-1), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)]
#         m, n = len(board), len(board[0])

#         def countMines(i, j):
#             return sum(1 for dr, dc in dirs if 0 <= i+dr < m and 0 <= j+dc < n and board[i+dr][j+dc] == 'M')

#         def dfs(i, j):
#             if i < 0 or j < 0 or i >= m or j >= n or board[i][j] != 'E':
#                 return
#             count = countMines(i, j)
#             if count == 0:
#                 board[i][j] = 'B'
#                 for dr, dc in dirs:
#                     dfs(i + dr, j + dc)
#             else:
#                 board[i][j] = str(count)

#         if board[click[0]][click[1]] == 'M':
#             board[click[0]][click[1]] = 'X'
#         else:
#             dfs(click[0], click[1])

#         return board