"""
I implemented the solution using BFS to reveal cells on the Minesweeper board after a click. Starting from the clicked cell, the algorithm uses a queue to explore neighbors level by level. For each cell, it counts adjacent mines and updates the cell accordingly—marking it with the mine count or 'B' if no mines are nearby. When no adjacent mines are found, it adds all unrevealed neighbors to the queue to continue the reveal.
Time Complexity: O(mn)
Space Complexity: O(mn)
"""
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        directions = [(-1,-1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
        m = len(board)
        n = len(board[0])
        row, col = click
        
        if board[row][col] == 'M':
            board[row][col] = 'X'
            return board
        
        queue = deque([(row, col)])
        board[row][col] = 'B'
        while queue:
            r, c = queue.popleft()
            mineCount = 0
            neighbors = []

            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n:
                    if board[nr][nc] == 'M':
                        mineCount += 1
                    elif board[nr][nc] == 'E':
                        neighbors.append((nr, nc))
            
            if mineCount == 0:
                for nr, nc in neighbors:
                    board[nr][nc] = 'B'
                    queue.append((nr, nc))
            else:
                board[r][c] = str(mineCount)
        
        return board