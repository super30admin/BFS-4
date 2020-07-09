'''
Solution:   
1.  First check if the click is on Mine.
2.  If not, calculate the count of the cell at which click happened and start exporing neighbors
    based on the count either in BFS or in DFS manner.

Time Complexity:    O(V + E)    |   Space Complexity:   O(V + E)
(or)
Time Complexity:    O(R x C)    |   Space Complexity:   O(R x C)
--- Passed all testcases successfully on leetcode for both the solutions.

'''


from collections import deque

class MineSweeperBFS:
    
    def __isValid(self, board: List[List[str]], r: int, c: int) -> bool:
        
        #   function to check whether a cell is valid or not
        rows = len(board)
        cols = len(board[0])
        
        if (r >= 0 and r < rows and c >= 0 and c < cols):
            return True
        
        return False
    
    def __countMines(self, board: List[List[str]], r: int, c: int, dirs: List[List[int]]) -> int:
        
        #   function to count mines around a cell
        count = 0
        
        for direction in dirs:
            newR = r + direction[0]
            newC = c + direction[1]
            if (self.__isValid(board, newR, newC) and board[newR][newC] == 'M'):
                count += 1
                
        return count        
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        #   edge case check
        if (board == None or len(board) == 0):
            return board
        
        #   initializations
        rows = len(board)
        cols = len(board[0])
        
        row = click[0]
        col = click[1]
        
        dirs = [[0, -1], [0, 1], [-1, 0], [1, 0],
               [-1, -1], [-1, 1], [1, -1], [1, 1]]
        
        #   if Mine => Game Over
        if (board[row][col] == 'M'):
            board[row][col] = 'X'
            return board
        
        #   initialize the Queue
        queue = deque([[row, col]])
        board[row][col] = 'B'
        
        #   until the Queue is empty
        while (len(queue) > 0):
            [r, c] = queue.popleft()
            
            #   count the mines for current cell
            count = self.__countMines(board, r, c, dirs)
            
            #   if count is zero => explore neighbors
            if (count == 0):

                #   for each direction,
                for direction in dirs:
                    newR = r + direction[0]
                    newC = c + direction[1]

                    #   add to the queue only of E and update the cell accordingly
                    if (self.__isValid(board, newR, newC) and board[newR][newC] == 'E'):
                        board[newR][newC] = 'B'
                        queue.append([newR, newC])

            #   else replace the cell with count of mines
            else:
                board[r][c] = str(count)           
            
        #   return updated board
        return board


class MineSweeperDFS:
    
    def __isValid(self, board: List[List[str]], r: int, c: int) -> bool:
        
        #   function to check whether a cell is valid or not
        rows = len(board)
        cols = len(board[0])
        
        if (r >= 0 and r < rows and c >= 0 and c < cols):
            return True
        
        return False
    
    def __countMines(self, board: List[List[str]], r: int, c: int, dirs: List[List[int]]) -> int:
        
        #   function to count mines around a cell
        count = 0
        
        for direction in dirs:
            newR = r + direction[0]
            newC = c + direction[1]
            if (self.__isValid(board, newR, newC) and board[newR][newC] == 'M'):
                count += 1
                
        return count        
    
    def __dfsVisit(self, board: List[List[str]], r: int, c: int, dirs: List[List[int]]) -> None:
        
        #   calculate count
        count = self.__countMines(board, r, c, dirs)
        
        #   if count is zero => explore neighbors
        if (count == 0):
            board[r][c] = 'B'
            for direction in dirs:
                newR = r + direction[0]
                newC = c + direction[1]
                if (self.__isValid(board, newR, newC) and board[newR][newC] == 'E'):
                    self.__dfsVisit(board, newR, newC, dirs)
        
        #   else replace the cell with count of mines
        else:
            board[r][c] = str(count)
            
        return
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        #   edge case check
        if (board == None or len(board) == 0):
            return board
        
        #   initializations
        rows = len(board)
        cols = len(board[0])
        
        r = click[0]
        c = click[1]
        
        dirs = [[0, -1], [0, 1], [-1, 0], [1, 0],
               [-1, -1], [-1, 1], [1, -1], [1, 1]]
        
        #   if Mine => Game Over
        if (board[r][c] == 'M'):
            board[r][c] = 'X'
            return board
        
        #   else DFS on current cell
        else:
            self.__dfsVisit(board, r, c, dirs)
         
        #   return updated Board





        return board