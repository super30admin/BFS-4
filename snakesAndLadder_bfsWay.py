'''
Time Complexity: 0(n*n)
Space Complexity: 0(n*n)
Run on leetCode: Yes
'''
from collections import deque
class Solution:
    
    def __init__(self):
        self.__visitSet = set()
        self.__memory1D = []
        self.__queue = deque([])
    
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
        
        
        # flatten the 2D board into a 1D list
        self.__memory1D = self.__createArray(board)
        
        '''perform bfsTraversal'''
        row = len(board)
        col = len(board[0])
        
        # 1. default add index 0-5 to the queue
        self.__queue.append(0)
        self.__visitSet.add(0)
        
        # 2. iterate queue
        lvl = 0
        
        print("Memory 1D is:\t",self.__memory1D)
        while len(self.__queue)!= 0:
            # initialize size 
            size = len(self.__queue)
            print(f"Lvl is:\t{lvl} and queue is:\t{self.__queue}")
        
            # iterate till count breeches size
            for count in range(0,size):
                
                # pop the index from the queue
                index = self.__queue.popleft()
                if index == row*col-1:
                    # I am at the last index, my game is over therfore return the lvl
                    return lvl
                
                # roll the dice and add the indexes to the queue that are no visited
                for roll in range(1,7):
                    tempIndex = index+roll
                    
                    # chk for breech
                    if tempIndex >= row*col:
                        continue
                    
                    elif self.__memory1D[tempIndex] == -1 and tempIndex not in self.__visitSet:
                        # add the tempIndex to both the queue and visitSet
                        self.__visitSet.add(tempIndex)
                        self.__queue.append(tempIndex)
                    
                    elif self.__memory1D[tempIndex] != -1 and self.__memory1D[tempIndex] not in self.__visitSet:
                        # add the value to both the queue and visitSet
                        self.__visitSet.add(tempIndex)
                        self.__visitSet.add(self.__memory1D[tempIndex])
                        self.__queue.append(self.__memory1D[tempIndex])
                '''end of dice roll'''
            '''count breeches size'''
            
            # update the lvl
            lvl += 1
        '''end of while loop'''
        
        # update lvl
        lvl -= 1
        
        # return default -1
        return -1
                        
                    