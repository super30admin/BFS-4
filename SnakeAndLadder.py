'''
Solution:
1.  First, for convenient purpose, create an array replicating the board's information.
2.  Perform BFS to find out the minimum steps taken to reach the goal state and mark each visited
    cell -2 so that not to visit that again.

Time Complexity:    O(R x C)    |   Space Complexity:   O(R x C)
--- Passed all testcases successfully on leetcode.
'''

from collections import deque

class Solution:
    
    def __createArray(self, board: List[List[int]]) -> List[int]:
        

        #   funtion to create an array from the given board input.
        N = len(board) * len(board[0])
        rows = len(board)
        cols = len(board[0])
        
        #   initialzations
        r = rows - 1
        c = 0
        array = []
        leftToRight = True      #   bool for direction movement
        
        #   iterate until you reach (0, 0)
        while (r >= 0 and c >= 0):
            
            #   put in the corresponding values at corresponding indices
            if (board[r][c] != -1):
                array.append(board[r][c] - 1)
            else:
                array.append(-1)
            
            #   move columns according to bool representing direction   
            if (leftToRight == True):
                c += 1
            else:
                c -= 1
            
            #   if edge of a row reached => update the direction and (row, col)   
            if (leftToRight == True and c >= cols):
                c -= 1
                r -= 1
                leftToRight = False
            
            elif (leftToRight == False and c < 0):
                c += 1
                r -= 1
                leftToRight = True
         
        #   return the array       
        return array
    
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        #   initializations
        N = len(board) * len(board[0])
        rows = len(board)
        cols = len(board[0])
        
        #   create array
        array = self.__createArray(board)
        
        minSteps = 0
        
        #   initialize the Queue
        queue = deque([0])
        array[0] = -2
        
        #   iterate until the Queue is empty
        while (len(queue) > 0):
            
            levelCount = len(queue)
            
            #   for current level
            for i in range(levelCount):
                currentCell = queue.popleft()

                #   goal condition
                if (currentCell == N - 1):
                    return minSteps
                
                #   for all children [1, 6],
                for diceNumber in range(1, 7):
                    newCell = currentCell + diceNumber
                    if (newCell < N and array[newCell] != -2):
                        
                        #   add the next jump into the queue if not visited
                        if (array[newCell] != -1):
                            queue.append(array[newCell])
                        else:
                            queue.append(newCell)
                        
                        #   mark visited
                        array[newCell] = -2
            
            #   update minimum steps (or level)            
            minSteps += 1
        
        #   return -1 if goal can't be reached
        return -1