"""
TC: O(N * M) {In the worst case, we may need to visit every cell of the board once.  
               For each visited cell, we check all 8 neighbors to count adjacent mines, 
               which is constant time. Hence, the overall time complexity is linear 
               in the number of cells.}
SC: O(N * M) {We use a queue to perform BFS. In the worst case, all cells could be 
               added to the queue, resulting in linear space usage relative to the board size.}

Approach:
We are tasked with updating a Minesweeper board after a user click, following standard rules:
    - If a mine ("M") is clicked, mark it as "X" and stop.
    - If an empty cell ("E") is clicked, reveal it:
        - If it has no adjacent mines, mark it as "B" and recursively reveal all neighboring cells.
        - If it has adjacent mines, mark it with the count of nearby mines.

To implement this, we use BFS starting from the clicked cell:
    1. Initialize a queue with the click position and mark it as "B".
    2. While the queue is not empty:
        - Pop the current cell.
        - Count the number of adjacent mines using `countMines`.
        - If there are no adjacent mines, mark all unvisited neighbors as "B" and add them to the queue.
        - Otherwise, mark the current cell with the mines count.

Helper functions:
    - `inBounds(row, col)`: Checks if a cell is inside the board boundaries.
    - `countMines(row, col)`: Counts mines in the 8 neighboring cells.

This BFS approach ensures each cell is processed at most once, efficiently revealing the board.

This problem ran successfully on Leetcode.
"""

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        def countMines(row, col):
            minesCount = 0

            for dr, dc in neighbors:
                nR, nC = row + dr, col + dc
                if inBounds(nR, nC) and board[nR][nC] == "M":
                    minesCount += 1

            return minesCount 
        
        def inBounds(row, col):
            return 0<=row<n and 0<=col<m

        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        n,m = len(board), len(board[0])
        neighbors = [[0,1], [1,0], [1,1], [-1,-1], [1,-1], [-1, 1], [-1, 0], [0, -1]]
        q = deque([click])
        board[click[0]][click[1]] = "B"

        while q:
            cRow, cCol = q.popleft()
            #count mines around the current cell
            minesCount = countMines(cRow, cCol)
            if minesCount == 0:
                for dr, dc in neighbors:
                    nRow, nCol = cRow + dr, cCol + dc
                    if inBounds(nRow, nCol) and board[nRow][nCol] == "E":
                        board[nRow][nCol] = "B"
                        q.append([nRow, nCol])
            else:
                board[cRow][cCol] = str(minesCount)
        
        return board
