# Time Complexity : O(N) where N=m*n
# Space Complexity : O(N) for queue
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        m = len(board)
        n = len(board[0])
        N = m*n
        flattenBoard = [0]*N
        minimumMoves = 0
        i = m-1
        j = 0
        leftToRight = True
        index = 0
        
        while index < N:
               
            if j>=n and leftToRight:
                i -=1
                j -=1
                leftToRight = not leftToRight
            
            elif j<0 and not leftToRight:
                i -=1
                j +=1
                leftToRight = not leftToRight
                
            if board[i][j] != -1:
                flattenBoard[index] = board[i][j]-1
            else:
                flattenBoard[index] = board[i][j]
                
            if leftToRight:
                j +=1
            
            elif not leftToRight:
                j -=1
                
            index +=1
           
        queue = deque()
        queue.append(0)
        
        while queue:
            
            levelCount = len(queue)
            for i in range(levelCount):
                indexVal = queue.popleft()
                if indexVal == N-1:
                    return minimumMoves
                for j in range(1,7):
                    if indexVal+j<N and flattenBoard[indexVal+j] != -2:
                        if flattenBoard[indexVal+j]!=-1:
                            queue.append(flattenBoard[indexVal+j])
                        else:
                            queue.append(indexVal+j)
                        flattenBoard[indexVal+j] = -2
                        
            minimumMoves +=1
        
        return -1
                        
                        
                        
                    
            
            
        
        
                
                
                
                
                
                
                
            
            
        
        