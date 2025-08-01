"""
Use DFS to recursively reveal cells starting from the clicked position
Count adjacent mines — if > 0, stop and write the number
If 0 adjacent mines, mark 'B' and continue revealing all 8 neighbors
"""
"""	
Time Complexity: O(m * n) in worst case (full board traversal)
Space Complexity: O(m * n) for recursion stack in DFS
"""
class mineSweep:
    def updateBoard(self, board: list[list[str]], click: list[int]) -> list[list[str]]:
        rows, cols = len(board), len(board[0])
        r, c = click

        if board[r][c] == 'M':
            board[r][c] = 'X'
            return board

        def dfs(i, j):
            if not (0 <= i < rows and 0 <= j < cols) or board[i][j] != 'E':
                return

            directions = [(-1,-1), (-1,0), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)]
            mine_count = 0
            for dx, dy in directions:
                ni, nj = i + dx, j + dy
                if 0 <= ni < rows and 0 <= nj < cols and board[ni][nj] == 'M':
                    mine_count += 1

            if mine_count > 0:
                board[i][j] = str(mine_count)
            else:
                board[i][j] = 'B'
                for dx, dy in directions:
                    dfs(i + dx, j + dy)

        dfs(r, c)
        return board

if __name__ == "__main__":
    board = [
        ["E","E","E","E","E"],
        ["E","E","M","E","E"],
        ["E","E","E","E","E"],
        ["E","E","E","E","E"]
    ]
    click = [3, 0]

    obj = mineSweep()
    updated = obj.updateBoard(board, click)
    for row in updated:
        print(row)
