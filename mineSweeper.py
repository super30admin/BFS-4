class Solution:
    
    """
    Description: play minesweeper game, starting with a given click on a board 
    
    Time Complexity: O(m*n)
    Space Complexity: O(m*n)
    where, m and n are dimensions of the board
    
    Approach: BFS
    - if click is on mine, convert the value to "M" and return board
    - start with a queue having the indices of the click, convert to "B" (edge case)
    - pop the queue and check number of mines in all 8 directions:
      + if there are no mines around, search for "E" in all directions, revert the values to "B"
      + else, revert values to number of mines (use a separate function to count number of mines)
    """

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        from collections import deque
        self.m = len(board); self.n = len(board[0])
        ir, ic = click

        if board[ir][ic] == "M": 
            board[ir][ic] = "X"
            return board
        
        # directions to search, diagonal included
        self.dirs = [(-1,-1), (0,-1), (1,-1), (1,0), (1,1), (0,1), (-1,1), (-1,0)]
        
        queue = deque()
        queue.append((ir, ic))
        board[ir][ic] = "B"
        
        while queue:
            
            curr = queue.popleft()
            n_mines = self.count_mines(board, curr[0], curr[1])
            if n_mines == 0:
                for d in self.dirs:
                    row = curr[0] + d[0]
                    col = curr[1] + d[1]
                    if row >= 0 and col >= 0 and row < self.m and col < self.n and board[row][col] == "E":
                        board[row][col] = "B"
                        queue.append((row, col))
            else:
                board[curr[0]][curr[1]] = str(n_mines)              
                
        return board

    def count_mines(self, board, ir, ic):
        n_mines = 0
        for d in self.dirs:
            row = ir + d[0]
            col = ic + d[1]
            if row >= 0 and col >= 0 and row < self.m and col < self.n and board[row][col] == "M": 
                n_mines += 1

        return n_mines  
