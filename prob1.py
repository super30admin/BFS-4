# Time Complexity : O(n)
# Space Complexity :O(n)
# Passed on Leetcode: yes

from collections import deque

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m, n = len(board), len(board[0])
        directions = [(1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (-1, -1), (1, -1), (-1, 1)]

        def count_adjacent_mines(row, col):
            count = 0
            for dr, dc in directions:
                nr, nc = row + dr, col + dc
                if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'M':
                    count += 1
            return count

        def reveal_blank_cells(start_row, start_col):
            queue = deque([(start_row, start_col)])
            while queue:
                row, col = queue.popleft()
                count = count_adjacent_mines(row, col)
                if count > 0:
                    board[row][col] = str(count)
                else:
                    board[row][col] = 'B'
                    for dr, dc in directions:
                        nr, nc = row + dr, col + dc
                        if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'E':
                            queue.append((nr, nc))
                            board[nr][nc] = 'B'

        click_row, click_col = click

        if board[click_row][click_col] == 'M':
            board[click_row][click_col] = 'X'
        else:
            reveal_blank_cells(click_row, click_col)

        return board
