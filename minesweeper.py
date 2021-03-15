'''
Time Complexity - O( 8 * mn) ~ O(m*n) since every cell recursively checks 8 cells
Space Complexity - O(m * n)
Approach - Connected components type problem solved recursively   # Use BFS
'''
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.rows, self.cols = len(board), len(board[0])
        self.dirs = [[0,1], [1,0], [0,-1], [-1,0], [1,1], [-1,-1], [1,-1], [-1,1]]
        queue = collections.deque()
        queue.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'
        while len(queue) > 0:
            popped_row, popped_col = queue.popleft()
            mines = self.findmines(popped_row, popped_col, board)
            
            if mines == 0:
                for d in self.dirs:
                    new_row = popped_row + d[0]
                    new_col = popped_col + d[1]
                    if new_row >= 0 and new_row < self.rows and new_col >= 0 and new_col < self.cols and board[new_row][new_col] == 'E':
                        queue.append((new_row, new_col))
                        board[new_row][new_col] = 'B'
            else:
                board[popped_row][popped_col] = str(mines)
        return board
    
    def findmines(self, r, c, board):
        mine = 0
        for i in self.dirs:
            row = r + i[0]
            col = c + i[1]
            if row >= 0 and row < self.rows and col >= 0 and col < self.cols and board[row][col] == 'M':
                mine += 1
        return mine