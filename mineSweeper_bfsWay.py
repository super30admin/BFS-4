'''
Time Complexity: 0(m*n)
Space Complexity: 0(m*n) -- queue
Run on leetCode: Yes
'''

from collections import deque
class Solution:
    
    def __init__(self):
        
        self.__dirMatrix = [
            [0,1],
            [0,-1],
            [-1,0],
            [1,0],
            [-1,1],
            [-1,-1],
            [1,1],
            [1,-1]
        ]
        
        self.__queue = None
        self.__board = None
    
    def __neighborsTraversal(self,pair):
        
        for dir in self.__dirMatrix:
            tempR = pair[0] + dir[0]
            tempC = pair[1] + dir[1]
            
            # boundary check
            if ((tempR >=0 and tempR < len(self.__board)) and (tempC >=0 and tempC < len(self.__board[0]))) and (self.__board[tempR][tempC] == 'E'):
                self.__board[tempR][tempC] = 'B'
                self.__queue.append((tempR,tempC))
        '''end of dir iteration'''
        return
    
    def __minesCount(self,pair):
        
        minesCount = 0
        
        for dir in self.__dirMatrix:
            tempR = pair[0] + dir[0]
            tempC = pair[1] + dir[1]
            
            # boundary check
            if ((tempR >=0 and tempR < len(self.__board)) and (tempC >=0 and tempC < len(self.__board[0]))) and (self.__board[tempR][tempC] == 'M'):
                minesCount += 1
        '''end of dir iteration'''
        
        return minesCount
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        # initialize global
        self.__board = board
        
        # initialize queue
        click = (click[0],click[1])
        self.__queue = deque([click])
        
        # initialize lvl
        lvl = 0
        
        # iterate queue
        while len(self.__queue) != 0:
            
            # initialize size
            size = len(self.__queue)
            
            # iterate till count breeches size
            for count in range(0,size):
                
                # pop the pair from the queue
                pair = self.__queue.popleft()
                
                '''base-case --- ***GAME OVER*** Scenario'''
                if self.__board[pair[0]][pair[1]] == 'M':
                    self.__board[pair[0]][pair[1]] = 'X'
                    # print(f"\n***GAME OVER***:\t {self.__board}")
                    return self.__board
                
                # chk for mines
                minesCount = self.__minesCount(pair)
                
                if minesCount != 0:
                    self.__board[pair[0]][pair[1]] = str(minesCount)
                    continue
                
                # mark the current cell as blank and chk for neighbors
                self.__board[pair[0]][pair[1]] = 'B'
                self.__neighborsTraversal(pair)
            '''count breeches size'''
            
            # update lvl
            lvl +=1 
        '''end of queue iteration'''
        
        # update lvl
        lvl -= 1
        
        # return board game
        # print(f"THE END:\t {self.__board}")
        return self.__board