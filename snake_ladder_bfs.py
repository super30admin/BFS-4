#Time complexity     : O(n^2) n is size of board
# Space complexity   : O(n^2)
from collections import deque
class Solution:
    def __init__(self,n=None):
        self.n = None
        
    def flattenboard(self,board):
        flat = [0]*(self.n*self.n+1)
        idx = 1
        even = True
        i = (self.n)-1
        j = 0
        while(i>=0 and j<(self.n)):
            flat[idx] = board[i][j]
            idx+=1
            if even:
                j+=1
                if j==(self.n):
                    i-=1
                    even = False
                    j-=1
            else:
                j-=1
                if j==-1:
                    i-=1
                    even = True
                    j+=1
        return flat
    
        
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        self.n = len(board)

        moves = self.flattenboard(board)
        level = 0
        queue = deque()
        queue.append(1) #since first element will alaways be -1 becoz no snake or ladder we can add 1 to queue
        moves[1]=-2
        while(queue):
            sz = len(queue)
            print("Before each level",queue)
            for item in range(sz) :
                popped = queue.popleft()
                if popped == (self.n) *(self.n):
                    return level
                for k in range(1,7):
                    child = popped+k       
                    if child > (self.n) * (self.n):break
                    if moves[child] != -2:
                        if moves[child]==-1:
                            queue.append(child)
                        else:
                            queue.append(moves[child])
                        
                        moves[child]=-2
            level+=1
          
        return -1
        
        
        