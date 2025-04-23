#Time Complexity: O(m × n)
#Space Complexity: O(m × n)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        m, n = len(board), len(board[0])
        dirs = [(-1, -1), (-1, 0), (-1, 1),(0, -1),(0, 1),
                (1, -1),  (1, 0), (1, 1)]
        def countMines(x, y):
            count = 0
            for dx, dy in dirs:
                nx, ny = x + dx, y + dy
                if 0 <= nx < m and 0 <= ny < n and board[nx][ny] == 'M':
                    count += 1
            return count

        def dfs(x, y):
            if board[x][y] != 'E':
                return

            mine_count = countMines(x, y)
            if mine_count > 0:
                board[x][y] = str(mine_count)
            else:
                board[x][y] = 'B'
                for dx, dy in dirs:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < m and 0 <= ny < n:
                        dfs(nx, ny)    
        cx, cy = click
        if board[cx][cy] == 'M':
            board[cx][cy] = 'X'
        else:
            dfs(cx, cy)

        return board 